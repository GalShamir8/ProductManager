package memento;

import java.util.ArrayList;
import java.util.TreeMap;

import models.Manager;
import models.Product;
import observers.CustomerObserverable;

public class ManagerMemento {

	private Manager manager;
	private TreeMap<String, Product> allProducts = new TreeMap<>();
	private ArrayList<CustomerObserverable> observers = new ArrayList<>();

	public ManagerMemento(Manager manager) {
		this.manager = manager;
	}

	public ManagerMemento save() {
		return new ManagerMemento(this.manager);
	}

	public void load(ManagerMemento lastInsert) {
		this.manager.setAllProduct(lastInsert.allProducts);
		this.manager.setObservers(lastInsert.observers);
		//manager.saveToFile();
	}

	public Manager getManager() {
		return manager;
	}
}
