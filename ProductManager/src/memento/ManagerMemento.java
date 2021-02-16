package memento;

import java.util.ArrayList;
import java.util.TreeMap;

import models.Manager;
import models.Product;
import observers.CustomerObserverable;

public class ManagerMemento {

	private TreeMap<String, Product> allProducts;
	private ArrayList<CustomerObserverable> observers;

	@SuppressWarnings("unchecked")
	public ManagerMemento(Manager manager) {
		if(manager.getAllProducts() != null && manager.getObservers() != null) {
			this.allProducts = (TreeMap<String, Product>) manager.getAllProducts().clone();
			this.observers = (ArrayList<CustomerObserverable>) manager.getObservers().clone();
		}
	}

	public ManagerMemento save(Manager manager) {
		return new ManagerMemento(manager);
	}

	public void load() {
		Manager.getInstance().setAllProduct(allProducts);
		Manager.getInstance().setObservers(observers);
	}

}
