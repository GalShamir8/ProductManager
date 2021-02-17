package models;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import common.OrderByInsert;
import common.OrderDESC;
import common.SortType;
import common.StatusType;
import controller.ModelListener;
import memento.ManagerMemento;
import modelCommands.ModelCommandable;
import observers.CustomerObserverable;
import program.Main;

public class Manager implements ModelCommandable, Iterable<Product> {

	private static Manager manager = new Manager();

	private ModelListener listener;
	private TreeMap<String, Product> allProducts;
	private ArrayList<CustomerObserverable> observers;

	private ManagerMemento lastInsert;
	//last Insert product
	private Product productToRemove = null;

	private Manager() {
		this.observers = new ArrayList<>();
		this.lastInsert = new ManagerMemento(this);
	}// singleton

	public static Manager getInstance() {
		return manager;
	}

	public void addProduct(String pId, String pName, String pPriceCost, String pPriceSell, String cName, String cPhone,
			boolean cPromotion) {
		try {

			Customer customer = new Customer(cName, cPhone, cPromotion);
			Product product = new Product(pId, pName, pPriceCost, pPriceSell, customer);

			// Backup last insert
			this.lastInsert = lastInsert.save(this);
			productToRemove = product;

			allProducts.put(pId, product);

			if (customer.isPromoted())
				this.observers.add(customer);

			saveToFile(product);

			listener.modelUpdateStatus("Product added", StatusType.eSuccess);

		} catch (Exception e) {
			listener.modelUpdateStatus(e.getMessage(), StatusType.eError);
		}
	}

	public String showCustomers() {
		StringBuilder sb = new StringBuilder("All Customers:\n");
		allProducts.forEach(new BiConsumer<String, Product>() {

			@Override
			public void accept(String key, Product value) {
				sb.append(value.getCustomer() + "\n");
			}
		});

		return sb.toString();
	}

	public void undo() {
		lastInsert.load();
		this.lastInsert = lastInsert.save(this);
		try {
			if(productToRemove != null)
				removeFromFile(productToRemove.getId());
			listener.modelUpdateStatus("restored to last insertion", StatusType.eSuccess);

		}catch(Exception e) {
			listener.modelUpdateStatus(e.getMessage(), StatusType.eError);
		}
	}

	public void registerListener(ModelListener listener) {
		this.listener = listener;
	}

	@Override
	public String showProducts() {
		StringBuilder sb = new StringBuilder("All Products:\nID\tProduct\n");
		allProducts.forEach(new BiConsumer<String, Product>() {

			@Override
			public void accept(String key, Product value) {
				sb.append(key + "\t" + value + "\n");
			}
		});

		return sb.toString();
	}

	@Override
	public void updateSortType(SortType sortType) {
		switch (sortType) {
		case eLexASC:
			this.allProducts = new TreeMap<>();
			break;

		case eLexDESC:
			this.allProducts = new TreeMap<>(new OrderDESC());
			break;

		case eInsert:
			this.allProducts = new TreeMap<>(new OrderByInsert());
			break;
		}
	}

	@Override
	public String SendPromotion() {
		StringBuilder sb = new StringBuilder();
		for(CustomerObserverable o : observers) {
			sb.append(o.update() + "\n");
		}

		return sb.toString();
	}

	@Override
	public int getNumOfPromoted() {
		return observers.size();
	}

	@Override
	public boolean searchProduct(String ID) {
		Product temp = null;
		if (allProducts != null)
			temp = allProducts.get(ID);

		if (temp == null)
			listener.modelUpdateStatus("Product not found", StatusType.eError);
		else
			listener.modelUpdateStatus("Product found", StatusType.eSuccess);

		return temp != null;
	}

	@Override
	public String showProduct(String ID) {
		Product temp = null;
		if (allProducts != null) {
			temp = allProducts.get(ID);
			return temp.toString();
		}
		return null;
	}

	@Override
	public String showProfit() {
		int profit = 0;
		if (allProducts != null) {
			Set<Entry<String, Product>> allEntries = allProducts.entrySet();

			for (Entry<String, Product> e : allEntries) {
				profit += (e.getValue().getSellPrice() - e.getValue().getCostPrice());
			}
		}

		return "All products profit: " + Integer.toString(profit);
	}

	@Override
	public void deleteProduct(String ID) {
		this.lastInsert = lastInsert.save(this);
		Product temp = allProducts.remove(ID);
		if (temp == null)
			listener.modelUpdateStatus("Product not found", StatusType.eError);
		else {
			if(temp.getCustomer().isPromoted())
				observers.remove(temp.getCustomer());

			try {
				removeFromFile(ID);
				listener.modelUpdateStatus("Product removed", StatusType.eSuccess);
			} catch (Exception e) {
				listener.modelUpdateStatus(e.getMessage(), StatusType.eError);
			}	
		}
	}

	@Override
	public void deleteAll() {
		if (allProducts != null) {
			try {
				removeFromFile(null);
				allProducts.clear();
			} catch (Exception e) {
				listener.modelUpdateStatus(e.getMessage(), StatusType.eError);
			}
		}

		if (observers != null)
			observers.clear();
	}

	public TreeMap<String, Product> getAllProducts() {
		return allProducts;
	}

	public ArrayList<CustomerObserverable> getObservers() {
		return observers;
	}

	public void setAllProduct(TreeMap<String, Product> allProducts) {
		this.allProducts = allProducts;
	}

	public void setObservers(ArrayList<CustomerObserverable> observers) {
		this.observers = observers;
	}

	public void saveToFile(Product p) throws Exception {
		FileIterator it = (FileIterator) iterator();
		it.save(p);
	}

	/**
	 * 
	 * @param productID if productId == null -> delete all
	 * @throws Exception
	 */
	private void removeFromFile(String productID) throws Exception {
		FileIterator it = (FileIterator) iterator();

		if (productID == null) {
			allProducts.forEach(new BiConsumer<String, Product>() {

				@Override
				public void accept(String key, Product value) {
					try {
						it.remove(key);
					} catch (Exception e) {
						listener.modelUpdateStatus(e.getMessage(), StatusType.eError);
					}
				}
			});
			it.clean();

		} else {
			it.remove(productID);
		}
	}

	public void readFromFile() {
		FileIterator it = (FileIterator)iterator();
		while(it.hasNext()) {
			Product p = it.next();
			allProducts.put(p.getId(), p);

			if(p.getCustomer().isPromoted()) 
				observers.add(p.getCustomer());
		}
	}

	@Override
	public Iterator<Product> iterator() {
		try {
			return new FileIterator();
		} catch (Exception e) {
			listener.modelUpdateStatus("Error in file", StatusType.eError);
		}
		return null;
	}


	public class FileIterator implements Iterator<Product> {
		private Exception fileError = new Exception("Error in file");

		private File file;

		private RandomAccessFile raf;

		int numOfProducts;
		int currentIndex = 0;

		public FileIterator() throws Exception {
			this.file = new File(Main.FILE_NAME);

			this.raf = new RandomAccessFile(file, "rw");

			setNumOfProducts();

		}

		private void setNumOfProducts() throws Exception {
			if (raf.length() > 0) {
				try {
					numOfProducts = raf.readInt();
				} catch (Exception e) {
					throw fileError;
				}
			} else
				this.numOfProducts = 0;

		}

		@Override
		public boolean hasNext() {
			return currentIndex < numOfProducts;
		}

		@Override
		public Product next(){
			if(hasNext()){
				try {
					try {
						//Product fields
						String pid = raf.readUTF();
						String pName = raf.readUTF();
						String costPrice = raf.readUTF();
						String sellPrice = raf.readUTF();

						//Customer fields
						String cName = raf.readUTF();
						String cPhone = raf.readUTF();
						boolean isPromoted = raf.readBoolean();

						Customer customer = new Customer(cName, cPhone, isPromoted);
						Product product = new Product(pid, pName, costPrice, sellPrice, customer);

						currentIndex++;

						return product;
					} catch (Exception e) {
						listener.modelUpdateStatus(e.getMessage(), StatusType.eError);
					}

				}catch (NoSuchElementException e) {
					throw new NoSuchElementException();
				}
			}
			return null;
		}

		public Product remove(String productId) throws Exception {
			while(hasNext()) {
				try {
					//pointer of start product
					long startIndex = raf.getFilePointer();
					//the next product in file
					Product p = next();

					if(p.getId().equals(productId)) {
						//bytes array in the size of all the rest data
						byte [] data = new byte[(int)(raf.length()-raf.getFilePointer())]; 

						raf.read(data);
						raf.seek(startIndex);

						//override the product we want to delete
						raf.write(data);

						//trim the file
						raf.setLength(raf.getFilePointer());

						//update the new numOfProducts
						raf.seek(0);
						raf.writeInt(allProducts.size());
						numOfProducts = allProducts.size();

						return p;
					}

				}catch(Exception e) {
					throw fileError;
				}
			}
			return null;
		}

		public void save(Product p) throws Exception {
			//write new size
			raf.seek(0);
			raf.writeInt(allProducts.size());
			raf.seek(raf.length());

			//Product fields
			raf.writeUTF(p.getId());
			raf.writeUTF(p.getName());
			raf.writeUTF(Integer.toString(p.getCostPrice()));
			raf.writeUTF(Integer.toString(p.getSellPrice()));

			//Customer fields
			Customer c = p.getCustomer();
			raf.writeUTF(c.getName());
			raf.writeUTF(c.getPhone());
			raf.writeBoolean(c.isPromoted());
		}

		/**
		 * clean all the fields in the file
		 * @throws IOException 
		 */
		public void clean() throws IOException {
			raf.setLength(0);
		}
	}
}
