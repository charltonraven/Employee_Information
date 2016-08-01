package Sonoco;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MainWindow extends Application implements EventHandler<ActionEvent> {

    Stage window;
    TableView<Employee> EmployeeTable;

    TextField txtFirstName;
    TextField txtLastName;

    TextField txtOldPCName;
    TextField txtNewPCName;

    // TextAreas
    TextArea txtaPhoneNumber;
    TextArea txtaSerialNumbers;
    TextArea txtaNotes;

    //buttons
    Button btnAdd;
    Button btnEdit;
    Button btnDelete;
    Button btnSubmit;
    Button btnSearch;

    //used only for editing
    Employee employee;

    //HBox
    HBox hbSubmitCancel;
    HBox hbEditCancel;

    //Edit or Add Submit Button
    String EorA = "";

    Connection conn;

    public static void main(String[] args) {

        launch(args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage PrimaryStage) throws Exception {

        window = PrimaryStage;
        window.setTitle("Keep Track of Employee Information");
        window.getIcons().add(new Image("Graphics/images/Sonoco.ico"));
        // Buttons
        btnSubmit = new Button("Submit");
        Button btnCancel = new Button("Cancel");


        // Labels
        Label lblFirstName = new Label("First Name*");
        Label lblLastName = new Label("Last Name*");
        Label lblSerialNumbers = new Label("Serial Number(s)*");
        Label lblPhoneNumber = new Label("Phone Number(s)");
        Label lblOldPCName = new Label("Old PC Name");
        Label lblNewPCName = new Label("New PC Name*");
        Label lblNotes = new Label("Notes (if Any)");

        // Textbox
        txtFirstName = new TextField();
        txtLastName = new TextField();

        txtOldPCName = new TextField();
        txtNewPCName = new TextField();

        // TextAreas
        txtaPhoneNumber = new TextArea();
        txtaSerialNumbers = new TextArea();
        txtaNotes = new TextArea("Enter any thing that might be useful to know...");

        // setting sizes of textAreas
        txtaPhoneNumber.setMaxHeight(50);
        txtaPhoneNumber.setMaxWidth(150);
        txtaPhoneNumber.setMinHeight(50);
        txtaPhoneNumber.setMinWidth(150);
        txtaPhoneNumber.setWrapText(true);

        txtaSerialNumbers.setMaxHeight(50);
        txtaSerialNumbers.setMaxWidth(150);
        txtaSerialNumbers.setMinHeight(50);
        txtaSerialNumbers.setMinWidth(150);
        txtaSerialNumbers.setWrapText(true);

        txtaNotes.setMaxHeight(200);
        txtaNotes.setMaxWidth(200);
        txtaNotes.setMinHeight(200);
        txtaNotes.setMinWidth(200);
        txtaNotes.setWrapText(true);


        // HBox For Buttons
        hbSubmitCancel = new HBox(30);
        hbSubmitCancel.getChildren().addAll(btnSubmit, btnCancel);
        hbSubmitCancel.setAlignment(Pos.CENTER);
        hbSubmitCancel.setVisible(false);

        //For Right Side of Border
        Label lblSearch = new Label("Search");
        final TextField txtSearch = new TextField();

        //Table start--------------------------------------------------------------------------------------
        //FirstName Column
        TableColumn<Employee, String> colFirst = new TableColumn<Employee, String>("First Name");
        colFirst.setMinWidth(100);
        colFirst.setCellValueFactory(new PropertyValueFactory<Employee, String>("EmployeeFirstName"));
        //LastName Column
        TableColumn<Employee, String> colLast = new TableColumn<Employee, String>("Last Name");
        colLast.setMinWidth(100);
        colLast.setCellValueFactory(new PropertyValueFactory<Employee, String>("EmployeeLastName"));

        //Phone Number Column
        TableColumn<Employee, String> colPhone = new TableColumn<Employee, String>("Phone Number");
        colPhone.setMinWidth(100);
        colPhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("Phone"));

        //Serial Number Column
        TableColumn<Employee, String> colSerial = new TableColumn<Employee, String>("Serial Number");
        colSerial.setMinWidth(100);
        colSerial.setCellValueFactory(new PropertyValueFactory<Employee, String>("SerialNumbers"));

        //Old PC Column
        TableColumn<Employee, String> colOldPC = new TableColumn<Employee, String>("Old PC");
        colOldPC.setMinWidth(100);
        colOldPC.setCellValueFactory(new PropertyValueFactory<Employee, String>("OldPCName"));

        //New PC Column
        TableColumn<Employee, String> colNewPC = new TableColumn<Employee, String>("New PC");
        colNewPC.setMinWidth(100);
        colNewPC.setCellValueFactory(new PropertyValueFactory<Employee, String>("NewPCName"));

        //Notes Column
        TableColumn<Employee, String> colNotes = new TableColumn<Employee, String>("Notes");
        colNotes.setMinWidth(100);
        colNotes.setCellValueFactory(new PropertyValueFactory<Employee, String>("Notes"));

        //Date Column
        TableColumn<Employee, String> colDate = new TableColumn<Employee, String>("Date");
        colDate.setMinWidth(100);
        colDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("Date"));

        EmployeeTable = new TableView<Employee>();
        DBConnector getMainTable = new DBConnector();
        EmployeeTable.setItems(getMainTable.GenerateTable());
        EmployeeTable.getColumns().addAll(colFirst, colLast, colPhone, colSerial, colOldPC, colNewPC, colNotes, colDate);
        EmployeeTable.setMaxSize(400, 500);
        EmployeeTable.setMinSize(200, 500);


        //Submit Button
        btnSubmit.setOnAction(this);


        //ADD Edit Delete Image Buttons

        Image imgAdd = new Image("Graphics/Images/add.png");
        btnAdd = new Button();
        btnAdd.setGraphic(new ImageView(imgAdd));
        btnAdd.setId("TableButtons");
        btnAdd.setOnAction(this);

        //Edit Button
        Image imgEdit = new Image("Graphics/Images/edit.png");
        btnEdit = new Button();
        btnEdit.setGraphic(new ImageView(imgEdit));
        btnEdit.setId("TableButtons");
        btnEdit.setOnAction(this);
        btnEdit.setDisable(true);
        //Delete Button
        Image imgDelete = new Image("Graphics/Images/delete.png");
        btnDelete = new Button();
        btnDelete.setGraphic(new ImageView(imgDelete));
        btnDelete.setId("TableButtons");

        HBox hbAED = new HBox(30);
        hbAED.setAlignment(Pos.CENTER);
        hbAED.getChildren().addAll(btnAdd, btnEdit, btnDelete);
        btnEdit.setId("TableButtons");

        //Big Edit Button
        Button btnEditBig = new Button("Submit");


        Image imgSearch = new Image("Graphics/Images/search.png");
        btnSearch = new Button();
        btnSearch.setGraphic(new ImageView(imgSearch));

        EmployeeTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    EorA = "Edit";
                    System.out.println(EorA);
                    employee = EmployeeTable.getSelectionModel().getSelectedItem();
                    txtFirstName.setText(employee.getEmployeeFirstName());
                    txtFirstName.setDisable(true);
                    txtLastName.setText(employee.getEmployeeLastName());
                    txtLastName.setDisable(true);
                    txtaPhoneNumber.setText(employee.getPhone());
                    txtaSerialNumbers.setText(employee.getSerialNumbers());
                    txtaSerialNumbers.setDisable(true);
                    txtOldPCName.setText(employee.getOldPCName());
                    txtOldPCName.setDisable(true);
                    txtNewPCName.setText(employee.getNewPCName());
                    txtaNotes.setText(employee.getNotes());
                    btnEdit.setDisable(false);




                }

            }
        });


        //HBox for search column
        HBox hbSearch = new HBox(8);
        hbSearch.getChildren().addAll(lblSearch, txtSearch, btnSearch);

        // VBox for Right Side
        VBox vbRightSide = new VBox(10);
        vbRightSide.getChildren().addAll(hbSearch, EmployeeTable, hbAED);
        vbRightSide.setPadding(new Insets(10, 10, 10, 10));


        // GridPane for Labels and Text Boxes
        GridPane mainGrid = new GridPane();
        mainGrid.setVgap(15);
        // add Labels
        mainGrid.add(lblFirstName, 0, 0);
        mainGrid.add(lblLastName, 0, 1);
        mainGrid.add(lblPhoneNumber, 0, 2);
        mainGrid.add(lblSerialNumbers, 0, 3);
        mainGrid.add(lblOldPCName, 0, 4);
        mainGrid.add(lblNewPCName, 0, 5);
        mainGrid.add(lblNotes, 0, 6);
        // Add TextBoxes
        mainGrid.add(txtFirstName, 1, 0);
        mainGrid.add(txtLastName, 1, 1);
        mainGrid.add(txtaPhoneNumber, 1, 2);
        mainGrid.add(txtaSerialNumbers, 1, 3);
        mainGrid.add(txtOldPCName, 1, 4);
        mainGrid.add(txtNewPCName, 1, 5);
        mainGrid.add(txtaNotes, 1, 6);

        mainGrid.setAlignment(Pos.TOP_CENTER);


        VBox vbCenter = new VBox(10);
        vbCenter.getChildren().addAll(mainGrid, hbSubmitCancel);
        Label EmployeeInfo = new Label("Bill Information");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

        Text Banner = new Text();
        Banner.setEffect(dropShadow);
        Banner.setCache(true);
        Banner.setX(10.0);
        Banner.setY(70.0);
        Banner.setFill(Color.web("0x3b596d"));
        Banner.setText("Employee Bill Information");
        Banner.setFont(Font.font(null, FontWeight.BOLD, 40));

        //YESSir Masta
        // EmployeeTable.setOnMouseClicked();


        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 20, 20, 20));
        BorderPane.setAlignment(Banner, Pos.CENTER);


        border.setTop(Banner);
        border.setCenter(vbCenter);
        border.setLeft(vbRightSide);

        Scene MainScene = new Scene(border);
        MainScene.getStylesheets().add("Graphics/MainStyle.css");


        window.setScene(MainScene);
        window.show();

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            EorA = "Add";
            btnEdit.setDisable(true);
            enableFields();
            clearFields();
            System.out.println(EorA);

        }
        if (event.getSource() == btnEdit) {
            EorA = "Edit";
            Employee employee=new Employee(txtFirstName.getText(),txtLastName.getText(),txtaPhoneNumber.getText(),txtaSerialNumbers.getText(),txtOldPCName.getText(),txtNewPCName.getText(),txtaNotes.getText());

            DBConnector editEmployee=new DBConnector();
            try{
                editEmployee.Edit(employee);
                EmployeeTable.setItems(editEmployee.GenerateTable());

            }catch (SQLException ex){
                ex.printStackTrace();
            }

        }
        if (event.getSource() == btnSubmit) {
            if (EorA.equals("Add")) {
                //get the current date
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                Date today = new Date();
                String todayDate = formatDate.format(today);

                Employee newEmployee = new Employee(txtFirstName.getText(), txtLastName.getText(), txtaPhoneNumber.getText(), txtaSerialNumbers.getText(), txtOldPCName.getText(), txtNewPCName.getText(), txtaNotes.getText(), todayDate);
                DBConnector SendToAdd = new DBConnector();
                try {
                    SendToAdd.Add(newEmployee);
                    DBConnector getMainTable = new DBConnector();
                    EmployeeTable.setItems(getMainTable.GenerateTable());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                txtaNotes.clear();
                txtaSerialNumbers.clear();
                txtFirstName.clear();
                txtLastName.clear();
                txtOldPCName.clear();
                txtNewPCName.clear();
                txtaPhoneNumber.clear();
            }
            if (EorA.equals("Edit")) {

            }

        }
        if (event.getSource() == btnEdit) {


        }
    }

    public void clearFields() {
        txtFirstName.setText("Enter new information");
        txtOldPCName.clear();
        txtLastName.clear();
        txtNewPCName.clear();
        txtaNotes.clear();
        txtaSerialNumbers.clear();
        txtaNotes.clear();
        txtaPhoneNumber.clear();
        hbSubmitCancel.setVisible(true);
    }

    public void enableFields() {
        txtFirstName.setDisable(false);
        txtOldPCName.setDisable(false);
        txtLastName.setDisable(false);
        txtNewPCName.setDisable(false);
        txtaNotes.setDisable(false);
        txtaSerialNumbers.setDisable(false);
        txtaNotes.setDisable(false);
        txtaPhoneNumber.setDisable(false);
        hbSubmitCancel.setVisible(true);

    }
}
