package models;

import java.io.File;
import java.util.ArrayList;
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

public class Manager implements ModelCommandable {

	private static Manager manager = new Manager();

	private ModelListener listener;
	private TreeMap<String, Product> allProducts;
	private ArrayList<CustomerObserverable> observers;

	private ManagerMemento lastInsert;

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
			this.lastInsert = lastInsert.save();
			Customer customer = new Customer(cName, cPhone, cPromotion);
			Product product = new Product(pName, pPriceCost, pPriceSell, customer);

			allProducts.put(pId, product);

			if (customer.isPromoted())
				this.observers.add(customer);
			// Backup last insert

			saveToFile();

			listener.modelUpdateStatus("Product added", StatusType.eSuccess, false);

		} catch (Exception e) {
			listener.modelUpdateStatus(e.getMessage(), StatusType.eError, false);
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
		lastInsert.load(lastInsert);
		listener.modelUpdateStatus("restored to last insertion", StatusType.eSuccess, false);
		this.lastInsert = lastInsert.save();
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
	public void SendPromotion() {

	}

	@Override
	public boolean searchProduct(String ID) {
		Product temp = null;
		if (allProducts != null)
			temp = allProducts.get(ID);

		if (temp == null)
			listener.modelUpdateStatus("Product not found", StatusType.eError, true);
		else
			listener.modelUpdateStatus("Product found", StatusType.eSuccess, true);

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
	public void deleteProduct(String ID) {
		this.lastInsert = lastInsert.save();
		Product temp = allProducts.remove(ID);
		// removeFromFile(ID);

		if (temp == null)
			listener.modelUpdateStatus("Product not found", StatusType.eError, false);
		else
			listener.modelUpdateStatus("Product removed", StatusType.eSuccess, false);
	}

	@Override
	public void deleteAll() {
		if (allProducts != null) {
			allProducts.clear();
			// removeFromFile(); id == null remove all
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

	public void saveToFile() {
		File file = new File(Main.FILE_NAME);

	}

}
