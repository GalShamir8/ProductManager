package views;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GpAddProduct extends GridPane{
	//Product
	private String productId;
	private String productName;
	private String productPriceCost;
	private String productPriceSell;
	//Customer
	private String customerName;
	private String customerPhone;
	private boolean customerPromotion;
	//Labels
	private Label lblProductId = new Label("Enter product id:");
	private Label lblPname = new Label("Enter product name:");
	private Label lblPpriceCost = new Label("Enter product cost:");
	private Label lblPpriceSell = new Label("Enter product sell price:");
	private Label lblCname= new Label("Enter customer name:");
	private Label lblCPhone = new Label("Enter customer phone");
	private Label lblCpromtion = new Label("Would you like to get promotion?");
	//Text field
	TextField tfPId = new TextField();
	TextField tfPname = new TextField();
	TextField tfPpriceCost = new TextField();
	TextField tfPpriceSell = new TextField();
	TextField tfCname = new TextField();
	TextField tfCPhone = new TextField();
	//check box
	CheckBox cbCpromtion = new CheckBox();
	//button
	private Button btnUndoLastAction = new Button("Undo");
	private Button btnSendDetails = new Button("Send");


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
		add(btnUndoLastAction, 5, 7);
		//Design need to added
	}
	/**
	 * Insert all text fields details to their matching variables
	 */
	public void operateSendBtnPressed() {
		//Product
		this.productId = tfPId.getText();
		this.productName = tfPname.getText();
		this.productPriceCost = tfPpriceCost.getText();
		this.productPriceSell = tfPpriceSell.getText();
		//Customer
		this.customerName = tfCname.getText();
		this.customerPhone = tfCPhone.getText();
		this.customerPromotion = cbCpromtion.isSelected();
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductPriceCost() {
		return productPriceCost;
	}

	public String getProductPriceSell() {
		return productPriceSell;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public boolean getCustomerPromotion() {
		return customerPromotion;
	}

	public Button getBtnUndoLastAction() {
		return btnUndoLastAction;
	}

	public Button getBtnSendDetails() {
		return btnSendDetails;
	}

}
