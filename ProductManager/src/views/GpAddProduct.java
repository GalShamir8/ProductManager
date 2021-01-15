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
	private String productName;
	private String productPriceCost;
	private String productPriceSell;
	//Customer
	private String customerName;
	private String customerPhone;
	private String customerPromotion;
	//Labels
	private Label lblPname = new Label("Enter product name:");
	private Label lblPpriceCost = new Label("Enter product cost:");
	private Label lblPpriceSell = new Label("Enter product sell price:");
	private Label lblCname= new Label("Enter customer name:");
	private Label lblCPhone = new Label("Enter customer phone");
	private Label lblCpromtion = new Label("Would you like to get promotion?");
	//Text feild
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
		add(lblPname, 0, 0);
		add(lblPpriceCost, 0, 1);
		add(lblPpriceSell, 0, 2);
		add(lblCname, 0, 3);
		add(lblCPhone, 0, 4);
		add(lblCpromtion, 0, 5);
		add(tfPname, 1, 0);
		add(tfPpriceCost, 1, 1);
		add(tfPpriceSell, 1, 2);
		add(tfCname, 1, 3);
		add(tfCPhone, 1, 4);
		add(cbCpromtion, 1, 5);
		add(btnSendDetails, 0, 6);
		add(btnUndoLastAction, 5, 6);

	}
}
