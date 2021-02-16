package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/*
 * An extend GridPane for adding product form fill
 */
public class GpAddProduct extends GridPane {

	// Labels
	private Label lblProductId = new Label("Enter product id:");
	private Label lblPname = new Label("Enter product name:");
	private Label lblPpriceCost = new Label("Enter product cost:");
	private Label lblPpriceSell = new Label("Enter product sell price:");
	private Label lblCname = new Label("Enter customer name:");
	private Label lblCPhone = new Label("Enter customer phone:");
	private Label lblCpromtion = new Label("Would you like to get promotion?");

	// Text field
	TextField tfPId = new TextField();
	TextField tfPname = new TextField();
	TextField tfPpriceCost = new TextField();
	TextField tfPpriceSell = new TextField();
	TextField tfCname = new TextField();
	TextField tfCPhone = new TextField();

	// check box
	CheckBox cbCpromtion = new CheckBox();

	// button
	private Button btnSendDetails = new Button("Send");

	// Variables
	boolean pressedFlag = false;

	public GpAddProduct() {
		initGrid();
		setPadding(new Insets(15));
		setAlignment(Pos.CENTER_LEFT);
		setVgap(20);
		setHgap(20);
	}

	public void initGrid() {
		add(lblProductId, 0, 0);
		add(lblPname, 0, 1);
		add(lblPpriceCost, 0, 2);
		add(lblPpriceSell, 0, 3);
		add(lblCname, 0, 4);
		add(lblCPhone, 0, 5);
		add(lblCpromtion, 0, 6);
		add(tfPId, 1, 0);
		add(tfPname, 1, 1);
		add(tfPpriceCost, 1, 2);
		add(tfPpriceSell, 1, 3);
		add(tfCname, 1, 4);
		add(tfCPhone, 1, 5);
		add(cbCpromtion, 1, 6);
		add(btnSendDetails, 0, 7);
	}

	public String getProductId() {
		return tfPId.getText();
	}

	public String getProductName() {
		return tfPname.getText();
	}

	public String getProductPriceCost() {
		return tfPpriceCost.getText();
	}

	public String getProductPriceSell() {
		return tfPpriceSell.getText();
	}

	public String getCustomerName() {
		return tfCname.getText();
	}

	public String getCustomerPhone() {
		return tfCPhone.getText();
	}

	public boolean getCustomerPromotion() {
		return cbCpromtion.isSelected();
	}

	public Button getBtnSendDetails() {
		return btnSendDetails;
	}

	/**
	 * Clean all the text fields
	 */
	public void cleanFields() {
		tfPId.clear();
		tfPname.clear();
		tfPpriceCost.clear();
		tfPpriceSell.clear();
		tfCname.clear();
		tfCPhone.clear();
		cbCpromtion.setSelected(false);
	}

}
