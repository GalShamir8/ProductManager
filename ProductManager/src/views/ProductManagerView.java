package views;

/**
 * @author galsh
 */

import java.time.LocalDate;

import common.*;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProductManagerView implements ProductManagerViewable {
	// view Listener
	private ViewListener listener;

	// root
	private BorderPane bpRoot = new BorderPane();

	// children
	private GpAddProduct gpProduct = new GpAddProduct();
	private ScrollPane scpProduct = new ScrollPane();
	private ScrollPane scpCustomer = new ScrollPane();
	private VBox vbPromotion = new VBox();
	private VBox vbSearchProduct = new VBox();

	// Buttons
	private Button btnExit = new Button("Exit");
	private Button btnAddProduct = new Button("Add product");
	private Button btnShowAllProducts = new Button("Show all products");
	private Button btnShowAllCustomers = new Button("Show all customers");
	private Button btnSendPromotion = new Button("Send promotion");
	private Button btnSearchProduct = new Button("Search product");
	private Button btnDeleteAll = new Button("Delete all");
	private Button btnUndo = new Button("Undo last insert");

	// Labels
	private Label lblStatus = new Label("Status:");
	private Label lblStatusDescription = new Label();

	/*
	 * Constructor sortFlag = true: if there is data in file false: if the file is
	 * empty
	 */
	public ProductManagerView(Stage mainStage, boolean sortFlag) {
		mainStage.setTitle("Product Manager Program");// Design
		mainStage.getIcons().add(new Image("file:icon.jpg"));

		btnAddProduct.setPrefSize(120, 10);
		btnShowAllCustomers.setPrefSize(120, 10);
		btnShowAllProducts.setPrefSize(120, 10);
		btnSendPromotion.setPrefSize(120, 10);
		btnSearchProduct.setPrefSize(120, 10);
		btnDeleteAll.setPrefSize(120, 10);
		btnUndo.setPrefSize(120, 10);;

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

		// Design
		bpRoot.setPadding(new Insets(10));
	}

	// init Top root border
	private void initTop() {
		// Labels
		Label lblTitle = new Label("Product Manager Program");
		Label lblDate = new Label(LocalDate.now().toString());

		// Design
		lblTitle.setFont(new Font("Tahoma", 24));
		lblTitle.setTextFill(Color.CORAL);
		HBox hbTop = new HBox();
		hbTop.setPadding(new Insets(10));
		hbTop.setSpacing(300);
		hbTop.setAlignment(Pos.CENTER_LEFT);

		// Adding nodes
		hbTop.getChildren().addAll(lblTitle, lblDate);

		bpRoot.setTop(hbTop);
	}

	// init Left root border
	private void initLeft() {
		VBox vbLeft = new VBox();
		// Design
		vbLeft.setPadding(new Insets(10));
		vbLeft.setSpacing(15);
		vbLeft.setAlignment(Pos.CENTER_LEFT);

		// Adding nodes
		vbLeft.getChildren().addAll(btnAddProduct, btnShowAllProducts, btnShowAllCustomers, btnSendPromotion,
				btnSearchProduct, btnUndo, lblStatus, lblStatusDescription, btnDeleteAll);

		// At first status is empty
		setStatusDescriptionVisible(false);

		// Buttons actions
		btnAddProduct.setOnAction(e -> addProductToModel());
		btnShowAllProducts.setOnAction(e -> showAllProducts());
		btnShowAllCustomers.setOnAction(e -> showAllCustomers());
		btnSendPromotion.setOnAction(e -> customerPromotion());
		btnSearchProduct.setOnAction(e -> searchProduct());
		btnDeleteAll.setOnAction(e -> deleteAll());
		btnUndo.setOnAction(e->undoLastInsertion());

		bpRoot.setLeft(vbLeft);
	}

	// init Bottom root border
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

	// init Middle root border
	private void initMiddle(boolean sortFlag) {
		StackPane spMiddleRoot = new StackPane();
		HBox hbSort = new HBox();

		// add all nodes
		spMiddleRoot.getChildren().addAll(hbSort, gpProduct, scpProduct, scpCustomer, vbPromotion, vbSearchProduct);

		// Visibility children
		setMiddleVisibile(false);

		// open only if file that holds data is empty
		if (!sortFlag)
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

		String[] opArr = { "Lexicographic A-Z", "Lexicographic Z-A", "By insertion order" };

		hbSort.setPadding(new Insets(10));
		hbSort.setSpacing(25);

		cbSort.getItems().addAll(opArr[0], opArr[1], opArr[2]);
		// default choice
		cbSort.setValue(opArr[0]);

		btnEnter.setOnAction(e -> {
			listener.viewUpdateSortType(SortType.values()[cbSort.getSelectionModel().getSelectedIndex()]);

			hbSort.setVisible(false);
			setLeftDisable(false);
		});
	}

	@Override
	public void addProductToModel() {
		setMiddleVisibile(false);

		setGpProductVisible(true);

		gpProduct.getBtnSendDetails().setOnAction(e -> {
			// Product fields
			String pId = gpProduct.getProductId();
			String pName = gpProduct.getProductName();
			String pPriceCost = gpProduct.getProductPriceCost();
			String pPriceSell = gpProduct.getProductPriceSell();
			// Customer fields
			String cName = gpProduct.getCustomerName();
			String cPhone = gpProduct.getCustomerPhone();
			boolean cPromotion = gpProduct.getCustomerPromotion();

			listener.viewAskToAddProduct(pId, pName, pPriceCost, pPriceSell, cName, cPhone, cPromotion);			
			gpProduct.cleanFields();
		});
	}

	@Override
	public void showAllProducts() {
		setMiddleVisibile(false);

		setScpProductVisible(true);

		Label output = new Label(listener.viewAskForShowProducts());
		scpProduct.setContent(output);
	}

	@Override
	public void showAllCustomers() {
		setMiddleVisibile(false);

		setScpCustomerVisible(true);
		Label output = new Label(listener.viewAskForShowCustomers());
		scpCustomer.setContent(output);

	}

	@Override
	public void undoLastInsertion() {
		setMiddleVisibile(false);
		
		listener.viewAskForUndo();
	}

	@Override
	public void customerPromotion() {
		setMiddleVisibile(false);
		vbPromotion.getChildren().clear();
		setVbPromotionVisible(true);

		HBox hbPromotion = new HBox();
		ScrollPane scpPromotion = new ScrollPane();
		TextField tfSendPromotion = new TextField();
		Label lblSendPromotion = new Label("Enter new promotion:");
		Button btnEnterPromotion = new Button("Send");

		hbPromotion.getChildren().addAll(lblSendPromotion, tfSendPromotion, btnEnterPromotion);
		vbPromotion.getChildren().addAll(hbPromotion, scpPromotion);

		// Design
		hbPromotion.setPadding(new Insets(10));
		hbPromotion.setSpacing(30);
		hbPromotion.setAlignment(Pos.CENTER_LEFT);

		btnEnterPromotion.setOnAction(e -> {
			listener.viewAskForUpdatePromotion(tfSendPromotion.getText());
		});

	}

	@Override
	public void searchProduct() {
		setMiddleVisibile(false);
		vbSearchProduct.getChildren().clear();
		setVbSearchProductVisible(true);

		HBox hbSearchProduct = new HBox();
		Label lblSearchProduct = new Label("Enter product ID");
		TextField tfSearchProduct = new TextField();
		Button btnSearch = new Button("Search");
		Button btnDeleteProduct = new Button("Delete product");
		Label lblShowProduct = new Label();

		hbSearchProduct.getChildren().addAll(lblSearchProduct, tfSearchProduct, btnSearch);
		vbSearchProduct.getChildren().addAll(hbSearchProduct, lblShowProduct, btnDeleteProduct);

		// Design
		vbSearchProduct.setPadding(new Insets(10));
		vbSearchProduct.setSpacing(20);
		vbSearchProduct.setAlignment(Pos.CENTER_LEFT);

		hbSearchProduct.setPadding(new Insets(10));
		hbSearchProduct.setSpacing(30);
		hbSearchProduct.setAlignment(Pos.CENTER_LEFT);

		lblShowProduct.setVisible(false);
		btnDeleteProduct.setVisible(false);

		btnSearch.setOnAction(e -> {
			String id = tfSearchProduct.getText();
			if (listener.viewAskForSearchProduct(id)) {
				lblShowProduct.setVisible(true);
				btnDeleteProduct.setVisible(true);

				lblShowProduct.setText(listener.viewAskForShowProduct(id));

				btnDeleteProduct.setOnAction(d -> deleteProduct(id));
			}
		});
	}

	private void deleteProduct(String id) {
		listener.viewAskToDeleteProduct(id);
	}

	@Override
	public void deleteAll() {
		listener.viewAskToDeleteAll();
		initMiddle(false);
	}

	@Override
	public void registerListener(ViewListener listner) {
		this.listener = listner;
	}

	@Override
	public void updateStatus(String msg, StatusType type, boolean visibleFlag) {
		switch (type) {
		case eSuccess:
			setMiddleVisibile(false);
			vbBoxVisibility(visibleFlag);
			setStatusDescriptionVisible(true);
			lblStatusDescription.setText(msg);
			lblStatusDescription.setFont(new Font("Tahoma", 13));
			lblStatusDescription.setTextFill(Color.GREEN);
			break;

		case eError:
			setMiddleVisibile(false);
			vbBoxVisibility(visibleFlag);
			setStatusDescriptionVisible(true);
			lblStatusDescription.setText(msg);
			lblStatusDescription.setTextFill(Color.RED);
			break;
		}
	}

	/**
	 * Aim to show only the current vbBox visibility
	 * 
	 * @param visibleFlag
	 */
	private void vbBoxVisibility(boolean visibleFlag) {
		if (vbPromotion.isVisible()) {
			setVbSearchProductVisible(!visibleFlag);
			setVbPromotionVisible(visibleFlag);
		} else {
			setVbSearchProductVisible(visibleFlag);
			setVbPromotionVisible(!visibleFlag);
		}
	}

	/**
	 * cleaning all last action windows
	 * 
	 * @param visible
	 */
	private void setMiddleVisibile(boolean visible) {
		setGpProductVisible(visible);
		setScpProductVisible(visible);
		setScpCustomerVisible(visible);
		setStatusDescriptionVisible(visible);
		setVbPromotionVisible(visible);
		setVbSearchProductVisible(visible);
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

	private void setVbPromotionVisible(boolean visible) {
		vbPromotion.setVisible(visible);
	}

	private void setVbSearchProductVisible(boolean visible) {
		vbSearchProduct.setVisible(visible);
	}

	private void setStatusDescriptionVisible(boolean visible) {
		lblStatusDescription.setVisible(visible);
	}

}
