package tms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;


/*
Schedule viser ting som ikke er lastet
Kan schedule deles i to?? inn og ut?! eller skal den bare vise utg�ende trafikk fra varehuset
Endre "load" i gui til "loaded" for � vise alle som har status "lastet".
Kan sikkert fjerne unload siden den ikke vil ha noen funksjon, eller flyttes til en plass som gir mer mening.
- eventuelt kan unload vise alle som er lastet og kan inneholde mulighet til � "mark as unloaded" for 
  endre status til en leveranse.
  	- unload kan ha en "sign" funksjon
  	
Eksportdocument kan/m� kanskje flyttes????
 
 
 */
public class databaseOperations1 extends Pane {

    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    List<Label> labelList = new ArrayList<Label>();
    List<Button> buttonList = new ArrayList<Button>();
    HBox hBox = new HBox(10);
    VBox vBox = new VBox(10);
    private ObservableList<ObservableList> data;
    TableView<Schedule> table;
    TableView<Goods> goodsTable;
    Pane schedulePane = new Pane();

    int width;
    int height;
    
    public databaseOperations1(int width, int height) {
        this.width = width;
        this.height = height;
        
    }
    
    public databaseOperations1() {
    }
    
    public Connection getConnection()
    {
        Connection connection = null;
        try
        {
            String connectionURL = "jdbc:sqlserver://158.38.101.103;"
                    + "databaseName=Konsulent1;user=admin123;password=admin123;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionURL);

        } catch (Exception e)
        {
        }

        return connection;
    }
    
    public boolean checkPasswordAndUsername(String username, String password)
    {
        try
        {
            Connection connection = getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM dbo.UserLogin WHERE Username='"+ username +"'");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
            
        } catch (Exception e)
        {
            
        }
        
        return false;
    }

    /*
    public Connection getConnection() {
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/testbase?autoReconnect=true&useSSL=false";
            connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "admin", "admin");
            return connection;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return connection;
    }
    */

    public Pane getSchedule() {
        System.out.println("GetSchedule");
        schedulePane.getChildren().clear();

        //Id column
        TableColumn<Schedule, String> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(width/7);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        //Type column
        TableColumn<Schedule, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(width/7);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        //StartTime column
        TableColumn<Schedule, String> startTimeColumn = new TableColumn<>("StartTime");
        startTimeColumn.setMinWidth(width/7);
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        //Origin column
        TableColumn<Schedule, String> originColumn = new TableColumn<>("Origin");
        originColumn.setMinWidth(width/7);
        originColumn.setCellValueFactory(new PropertyValueFactory<>("origin"));
        //Destination column
        TableColumn<Schedule, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setMinWidth(width/7);
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        //Delay column
        TableColumn<Schedule, String> delayColumn = new TableColumn<>("Delay");
        delayColumn.setMinWidth(width/7);
        delayColumn.setCellValueFactory(new PropertyValueFactory<>("delay"));
        //DelayReason column
        TableColumn<Schedule, String> delayReasonColumn = new TableColumn<>("DelayReason");
        delayReasonColumn.setMinWidth(width/7);
        delayReasonColumn.setCellValueFactory(new PropertyValueFactory<>("delayReason"));

        table = new TableView<>();
        table.setItems(getScheduleFromDatabase());
        table.getColumns().addAll(idColumn, typeColumn, startTimeColumn, originColumn, destinationColumn, delayColumn, delayReasonColumn);
        //System.out.println(height);
        table.setPrefHeight(height);
        table.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                System.out.println(table.getSelectionModel().getSelectedItem().getId());

                //schedulePane.getChildren().clear();
                getGoods();
                //schedulePane.getChildren().clear();
                //vBox.getChildren().clear();

                // 
                //schedulePane.getChildren().add(getGoodsFromDatabase());
            }
        });
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        schedulePane.getChildren().add(vBox);
        return schedulePane;
    }

    public Pane getGoods(/*  Schedule ID as parameter  */) {
        schedulePane.getChildren().clear();

        //Id column
        TableColumn<Goods, String> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(width/5);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        //Product column
        TableColumn<Goods, String> productColumn = new TableColumn<>("Product");
        productColumn.setMinWidth(width/5);
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        //Weight column
        TableColumn<Goods, String> weightColumn = new TableColumn<>("Weight");
        weightColumn.setMinWidth(width/5);
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        //Destination column
        TableColumn<Goods, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setMinWidth(width/5);
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        //Schedule column
        TableColumn<Goods, String> scheduleColumn = new TableColumn<>("Schedule");
        scheduleColumn.setMinWidth(width/5);
        scheduleColumn.setCellValueFactory(new PropertyValueFactory<>("schedule"));

        goodsTable = new TableView<>();
        goodsTable.setItems(getGoodsFromDatabase());
        goodsTable.getColumns().addAll(idColumn, productColumn, weightColumn, destinationColumn, scheduleColumn);
        goodsTable.setPrefHeight(height - 26);
        goodsTable.setTranslateY(0);

        Button back = new Button("Back");
        back.setOnAction(e -> {
            getSchedule();
        });
        Button load = new Button("Mark as loaded");
        load.setOnAction(e -> {
            System.out.println("The status is now set to: Loaded");
            getSchedule();
        });

        HBox hBox = new HBox();
        load.setTranslateX(10);

        hBox.getChildren().addAll(back, load);
        //back.setTranslateX(10);
        //back.setTranslateY(5);
        //
        //load.setTranslateY(5);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, goodsTable);
        schedulePane.getChildren().add(vBox);

        return schedulePane;
    }

    public ObservableList<Schedule> getScheduleFromDatabase() {
        ObservableList<Schedule> schedule = FXCollections.observableArrayList();
        schedule.add(new Schedule("0000001", "Goods", "12:00", "�lesund", "Molde", "5 minutes", "Traffic"));
        schedule.add(new Schedule("0000002", "Goods", "13:00", "�lesund", "Trondheim", "0 minutes", "-"));
        schedule.add(new Schedule("0000003", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000004", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000005", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000006", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000007", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000008", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000009", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000010", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000011", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000012", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000013", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000014", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000015", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000016", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000017", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000018", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000019", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000020", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000021", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));
        schedule.add(new Schedule("0000022", "Goods", "14:00", "�lesund", "Ulsteinvik", "50 minutes", "Snow"));

        /*
			try{
				connection = getConnection();
				pst = connection.prepareStatement("SELECT * FROM `Schedule`");
				rs = pst.executeQuery();
				while(rs.next()){			
					schedule.add(new Schedule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));					
				}			
			}catch(Exception e){			
			}
         */
        return schedule;
    }

    public ObservableList<Goods> getGoodsFromDatabase(/*  Schedule ID as parameter  */) {
        ObservableList<Goods> goods = FXCollections.observableArrayList();
        goods.add(new Goods("0001", "0000002", "100 kg", "Trondheim", "Melk"));
        goods.add(new Goods("0002", "0000002", "57 kg", "Trondheim", "Sm�r"));
        goods.add(new Goods("0003", "0000002", "87 kg", "Trondheim", "R�mme"));
        goods.add(new Goods("0004", "0000002", "45 kg", "Trondheim", "Biola"));

        /*
			try{
				connection = getConnection();
				pst = connection.prepareStatement("SELECT * FROM `Pallet` WHERE schedule='schedule'");
				rs = pst.executeQuery();
				while(rs.next()){			
					goods.add(new Schedule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));					
				}			
			}catch(Exception e){			
			}
         */
        return goods;
    }
}
