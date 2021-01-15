package views;
/**
 * @author galsh
 */

import java.time.LocalDate;

import controller.Controller;
import controller.ViewListener;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.eNums;

public class ProductManagerView implements ProductManagerViewable, eNums{
	//view Listener
	private ViewListener listener;

	//root
	private BorderPane bpRoot = new BorderPane();
	
	//children
	private GpAddProduct gpProduct = new GpAddProduct();
	private ScrollPane scpProduct = new ScrollPane();
	private ScrollPane scpCustomer = new ScrollPane();

	//Buttons
	private Button btnExit = new Button("Exit");
	private Button btnAddProduct = new Button("Add product");
	private Button btnShowAllProducts = new Button("Show all products");
	private Button btnShowAllCustomers = new Button("Show all customers");

	//Labels 
	private Label lblStatus = new Label("Status:");
	private Label lblStatusDescription = new Label();

	//c'tor - sort flag: if there is already data or not
	public ProductManagerView(Stage mainStage, boolean sortFlag) {
		mainStage.setTitle("Product Manager Program");//design
		mainStage.getIcons().add(new Image("file:icon.jpg"));
		
		btnAddProduct.setPrefSize(120, 10);
		btnShowAllCustomers.setPrefSize(120, 10);
		btnShowAllProducts.setPrefSize(120, 10);
		
		
		initRoot(sortFlag);
		
		Scene mainScene = new Scene(bpRoot, 700, 500);
		mainStage.setScene(mainScene);
		mainStage.show();
	}

	private void initRoot(boolean sortFlag) {
		initTop();
		initLeft();
		initBottom();
		initMiddle(sortFlag);

		//Design
		bpRoot.setPadding(new Insets(10));
	}

	//init Top root border
	private void initTop() {
		//Labels
		Label lblTitle = new Label("Product Manager Program");
		Label lblDate = new Label(LocalDate.now().toString());

		//Design
		lblTitle.setFont(new Font("Tahoma",24));
		lblTitle.setTextFill(Color.CORAL);
		HBox hbTop = new HBox();
		hbTop.setPadding(new Insets(10));
		hbTop.setSpacing(300);
		hbTop.setAlignment(Pos.CENTER_LEFT);

		//Adding nodes
		hbTop.getChildren().addAll(lblTitle, lblDate);

		bpRoot.setTop(hbTop);
	}

	//init Left root border
	private void initLeft() {
		VBox vbLeft = new VBox();
		//Design
		vbLeft.setPadding(new Insets(10));
		vbLeft.setSpacing(15);
		vbLeft.setAlignment(Pos.CENTER_LEFT);

		//Adding nodes
		vbLeft.getChildren().addAll(btnAddProduct,btnShowAllProducts,
				btnShowAllCustomers, lblStatus, lblStatusDescription);

		//At first status is empty
		setStatusDescriptionVisible(false);

		//Buttons actions
		btnAddProduct.setOnAction(e->addProductToModel());
		btnShowAllProducts.setOnAction(e->showAllProducts());
		btnShowAllCustomers.setOnAction(e->showAllCustomers());

		bpRoot.setLeft(vbLeft);
	}

	//init Bottom root border
	private void initBottom() {
		Label lblAuthors = new Label("Created By Amit Levy & Gal Shamir");

		HBox hbBottom = new HBox();
		hbBottom.setPadding(new Insets(10));
		hbBottom.setSpacing(430);
		hbBottom.setAlignment(Pos.CENTER_LEFT);

		hbBottom.getChildren().addAll(lblAuthors, btnExit);

		btnExit.setOnAction(e -> Platform.exit());

		bpRoot.setBottom(hbBottom);

	}

	//init Middle root border
	private void initMiddle(boolean sortFlag) {
		StackPane spMiddleRoot = new StackPane();
		HBox hbSort = new HBox();

		//add all nodes
		spMiddleRoot.getChildren().addAll(hbSort, gpProduct, scpProduct, scpCustomer);
		
		//Visibility children
		hbSort.setVisible(false);
		setGpProductVisible(false);
		setScpProductVisible(false);
		setScpCustomerVisible(false);

		//open only if file that holds data is empty
		if(!sortFlag) 
			checkSort(hbSort);

		bpRoot.setCenter(spMiddleRoot);
	}

	public void checkSort(HBox hbSort) {
		setLeftDisable(true);
		hbSort.setVisible(true);

		Label lblSortOp = new Label("Choose sort option from the following:");
		ComboBox<String> cbSort = new ComboBox<>();
		Button btnEnter = new Button("Enter");

		hbSort.getChildren().addAll(lblSortOp, cbSort, btnEnter);

		String [] opArr = {"Lexicographic A-Z", "Lexicographic Z-A", "By insertion order"};

		hbSort.setPadding(new Insets(10));
		hbSort.setSpacing(25);

		cbSort.getItems().addAll(opArr[0], opArr[1], opArr[2]);
		//default choice
		cbSort.setValue(opArr[0]);
		
		btnEnter.setOnAction(e->{
			
			switch(cbSort.getSelectionModel().getSelectedIndex()) {
			case 0:
				//eSortType = eLexASC
				listener.viewUpdateSortType(eSortType.values()[0]);
				break;
			case 1:
				//eSortType = eLexDESC
				listener.viewUpdateSortType(eSortType.values()[1]);
				break;				
			case 2:
				//eSortType = eInsert
				listener.viewUpdateSortType(eSortType.values()[2]);
				break;
			}
			
			hbSort.setVisible(false);
			setLeftDisable(false);
		});
	}

	@Override
	public void addProductToModel() {
		setMiddleVisibile(false);
		
		setGpProductVisible(true);
		
		gpProduct.getBtnSendDetails().setOnAction(e->gpProduct.operateSendBtnPressed());
		
		//Product fields
		String pId = gpProduct.getProductId();
		String pName = gpProduct.getProductName();
		String pPriceCost = gpProduct.getProductPriceCost();
		String pPriceSell = gpProduct.getProductPriceSell();
		//Customer fields
		String cName = gpProduct.getCustomerName();
		String cPhone = gpProduct.getCustomerPhone();
		boolean cPromotion = gpProduct.getCustomerPromotion();

		listener.viewAskToAddProduct(pId, pName, pPriceCost, pPriceSell, cName, cPhone, cPromotion);
	}

	@Override
	public void showAllProducts() {
		setMiddleVisibile(false);
		
		setScpProductVisible(true);
		
		TextArea output = new TextArea(listener.viewAskForShowProducts());
		scpProduct.setContent(output);
	}

	@Override
	public void showAllCustomers() {
		setMiddleVisibile(false);
		
		setScpCustomerVisible(true);
		TextArea output = new TextArea(listener.viewAskForShowCustomers());
		scpCustomer.setContent(output);
	}

	@Override
	public void undoLastInsertion() {
		setMiddleVisibile(false);
		
		gpProduct.getBtnUndoLastAction().setOnAction(e->{
			listener.viewAskForUndo();
		});
	}

	@Override
	public void registerListener(ViewListener listner) {
		this.listener = listner;
	}

	@Override
	public void updateStatus(String msg, eStatusType type) {
		switch(type) {
		case eSuccess:
			setMiddleVisibile(false);
			setStatusDescriptionVisible(true);
			lblStatusDescription.setText(msg);
			lblStatusDescription.setFont(new Font("Tahoma", 13));
			lblStatusDescription.setTextFill(Color.GREEN);
			break;
			
		case eError:
			setMiddleVisibile(false);
			setStatusDescriptionVisible(true);
			lblStatusDescription.setText(msg);
			lblStatusDescription.setTextFill(Color.RED);
			break;
		}
	}

	/**
	 * cleaning all last action windows
	 * @param visible
	 */
	private void setMiddleVisibile(boolean visible) {
		setGpProductVisible(visible);
		setScpProductVisible(visible);
		setScpCustomerVisible(visible);
		setStatusDescriptionVisible(visible);
	}
	
	private void setLeftDisable(boolean disable) {
		bpRoot.getLeft().setDisable(disable);
	}
	
	private void setGpProductVisible(boolean visible) {
		gpProduct.setVisible(visible);
	}
	
	private void setScpCustomerVisible(boolean visible) {
		scpCustomer.setVisible(visible);
	}

	private void setScpProductVisible(boolean visible) {
		scpProduct.setVisible(visible);	
	}
	
	private void setStatusDescriptionVisible(boolean visible) {
		lblStatusDescription.setVisible(visible);
	}


}
