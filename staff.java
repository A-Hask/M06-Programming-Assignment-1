import java.sql.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class staff {
    private Statement statement;
    private TextField tfLName;
    private TextField tfFName;
    private TextField tfMI;
    private TextField tfAddress;
    private TextField tfCity;
    private TextField tfState;
    private TextField tfPhone;
    private Label lblStatus = new Label();

    @Override
    public void start(Stage primaryStage){
        initializeDB();

        Button btView = new Button("View all staff");
        Button btInsert = new Button("Insert");
        Button btClear = new Button("Clear");

        VBox vBox = new VBox();
        vBox.getChildren.addAll(
            new Label("Last Name"), tfLName, 
            new Label("First Name"), tfFName, 
            new Label("Middle Initial"), tfMI, 
            new Label("Address"), tfAddress, 
            new Label("City"), tfCity,
            new Label("State"), tfState,
            new Label("Phone Number"), tfPhone,
            (btView), (btInsert), (btUpdate), (btClear)
        ); 

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(vBox, lblStatus);

        tfLName.setPrefColumnCount(20);
        tfFName.setPrefColumnCount(20);
        tfMI.setPrefColumnCount(1);
        tfAddress.setPrefColumnCount(50);
        tfCity.setPrefColumnCount(20);
        tfState.setPrefColumnCount(2);
        tfPhone.setPrefColumnCount(10);
        btView.setOnAction( e -> viewStaff());
        btInsert.setOnAction(e -> addToStaff());
        btUpdate.setOnAction(e -> editStaff());
        btClear.setOnAction(e -> clearTextBoxes());

        Scene scene = new Scene(vBox2, 420, 80);
        primaryStage.setTitle("View Staff");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            Connection connection = DriverManager.getConnection
            ("jdbs:mysql://localhost/javabook", null, null);
            System.out.println("Database connected");

            Statement statement = connection.createStatement();

            statement.executeUpdate
            ("create table staff (id char(9), lastName varchar(15), firstName varchar(15), mI char(1), address varchar(20), city varchar(20), state char(2), telephone char(10), email varchar(40), primary key (id))");

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showStaff(MouseEvent event){
        String lName = tfLName.getText();
        String fName = tfFName.getText();
        String mI = tfMI.getText();
        String address = tfAddress.getText();
        String city = tfCity.getText();
        String state = tfState.getText();
        String phone = tfPhone.getText();
        try {
            String queryString = "SELECT * FROM staff;";
            
            ResultSet resultSet = statement.executeQuery
            (queryString);

            if (resultSet.next()){
                lName = resultSet.getString(1);
                fName = resultSet.getString(2);
                mI = resultSet.getString(3);
                address = resultSet.getString(4);
                city = resultSet.getString(5);
                state = resultSet.getString(6);
                phone = resultSet.getString(7);

                lblStatus.setText(fName + " " + mI + " " + lName + ": " + address + " " + city + ", " + state + "; " + phone);
            } else {
                lblStatus.setText("Not found");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addToStaff(MouseEvent event){
        String lName = tfLName.getText();
        String fName = tfFName.getText();
        String mI = tfMI.getText();
        String address = tfAddress.getText();
        String city = tfCity.getText();
        String state = tfState.getText();
        String phone = tfPhone.getText();
        try {
            String queryString = "INSERT INTO staff VALUES(null, " + lname + ", " + fname + ", " + mI + ", " + address + ", " + city + ", " + state + ", " + phone + ");";
            
            ResultSet resultSet = statement.executeQuery
            (queryString);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearTextBoxes(MouseEvent event){
        tfLName.clear();
        tfFName.clear();
        tfMI.clear();
        tfAddress.clear();
        tfCity.clear();
        tfState.clear();
        tfPhone.clear();
    }
}
    
