package Control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.DeviceDAO;
import DAO.RoomDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Device;
import model.Room;

public class RoomController implements Initializable {
	@FXML
	private TableView<Room> table;
	
	@FXML
	private TableColumn<Room,String> roomid;
	
	@FXML
	private TableColumn<Room,String> name;
	
	@FXML
	private TableColumn<Room,String> capacity;
	
	@FXML
	private TableColumn<Room,String> price;
	@FXML
	private TableColumn<Room,String> status;
	@FXML
	private TableColumn<Room,String> note;
	
	@FXML
	private TextField newRoomname;

	@FXML
	private TextField newCapacity;

	@FXML
	private TextField newPrice;

	@FXML
	private TextField newStatus;
	

	@FXML
	private TextField newNote;
	
	@FXML
	private TextField tfRoomname;

	@FXML
	private TextField tfStatus;

	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	private ObservableList<Room> accountlist=FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Room> a = RoomDAO.getInstance().selectAll();
        accountlist.addAll(a);
        
        roomid.setCellValueFactory(new PropertyValueFactory<Room, String>("roomId"));
        name.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
        status.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));
        note.setCellValueFactory(new PropertyValueFactory<Room, String>("note"));
        price.setCellValueFactory(new PropertyValueFactory<Room, String>("price"));
        capacity.setCellValueFactory(new PropertyValueFactory<Room, String>("capacity"));
        table.setItems(accountlist);
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Lấy dữ liệu của hàng được chọn
                Room selectedDevice = table.getSelectionModel().getSelectedItem();
                if (selectedDevice != null) {
                	newRoomname.setText(selectedDevice.getName());
                	newCapacity.setText(String.valueOf(selectedDevice.getCapacity()));
                	newPrice.setText(String.valueOf(selectedDevice.getPrice()));
                	newStatus.setText(selectedDevice.getStatus());
                	newNote.setText(selectedDevice.getNote());
                }
               
            }
        });
	}
	
	public void switchToAccountManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Account.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToWorkManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Work.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToLocalPersonManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/LocalPerson.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToRoomManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Room.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToDeviceManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Device.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToActivityManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Activity.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToRentManagement(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Rent.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void logout(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/Loggin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void insertRoom(ActionEvent event) throws IOException{
		Room x= new Room(newRoomname.getText(),Integer.parseInt(newCapacity.getText()), Integer.parseInt(newPrice.getText()),newStatus.getText(),newNote.getText());
		ArrayList<Room> a = RoomDAO.getInstance().selectAll();
		int k=0;
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getName().compareTo(newRoomname.getText())==0) {
				k=1;
				break;
			}
		}
		if(k==0) {
			RoomDAO.getInstance().insert(x);
			showAlert(AlertType.INFORMATION,"thông báo", "Nhập thành công");
			accountlist.clear();
			ArrayList<Room> b = RoomDAO.getInstance().selectAll();
	        accountlist.addAll (b);
	
	        roomid.setCellValueFactory(new PropertyValueFactory<Room, String>("roomId"));
	        name.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        status.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));
	        note.setCellValueFactory(new PropertyValueFactory<Room, String>("note"));
	        price.setCellValueFactory(new PropertyValueFactory<Room, String>("price"));
	        capacity.setCellValueFactory(new PropertyValueFactory<Room, String>("capacity"));
	        table.setItems(accountlist);
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Phòng đã tồn tại trong cơ sở dữ liệu");
		}
	}
	
	private static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // HeaderText set null để không hiển thị tiêu đề phụ
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	public void deleteRoom(ActionEvent event){
		Room Selected = table.getSelectionModel().getSelectedItem();
		if(Selected != null) {
		RoomDAO.getInstance().delete(Selected);
		accountlist.remove(Selected);
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi"," Bạn chưa chọn phòng");
		}
	}
	public void updateRoom(ActionEvent event) {
		Room x= new Room(newRoomname.getText(),Integer.parseInt(newCapacity.getText()), Integer.parseInt(newPrice.getText()),newStatus.getText(),newNote.getText());
		Room a = RoomDAO.getInstance().selectByName(x.getName());
		x.setRoomId(a.getRoomId());
		RoomDAO.getInstance().update(x);
		accountlist.clear();
		ArrayList<Room> b = RoomDAO.getInstance().selectAll();
        accountlist.addAll (b);
        roomid.setCellValueFactory(new PropertyValueFactory<Room, String>("roomId"));
        name.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
        status.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));
        note.setCellValueFactory(new PropertyValueFactory<Room, String>("note"));
        price.setCellValueFactory(new PropertyValueFactory<Room, String>("price"));
        capacity.setCellValueFactory(new PropertyValueFactory<Room, String>("capacity"));
        table.setItems(accountlist);
        newRoomname.setText(null);
    	newCapacity.setText(null);
    	newPrice.setText(null);
    	newStatus.setText(null);
    	newNote.setText(null);	
	}
	public void clearRoom(ActionEvent event) {
		ArrayList<Room> a = RoomDAO.getInstance().selectAll();
		accountlist.clear();
        accountlist.addAll(a);
		newRoomname.setText(null);
    	newCapacity.setText(null);
    	newPrice.setText(null);
    	newStatus.setText(null);
    	newNote.setText(null);	
	}
public void searchByName(ActionEvent event) throws IOException {
		
		if (tfRoomname.getText() != "") {
			ArrayList<Room> arraylist = new ArrayList<>();
			for (int i = 0; i< accountlist.size(); i++) {
				if (accountlist.get(i).getName().compareTo(tfRoomname.getText()) == 0) {
					arraylist.add(accountlist.get(i));
				}
			}
			accountlist.clear();
			accountlist.addAll(arraylist);
		}
	}
	
public void searchByStatus(ActionEvent event) throws IOException {
	
	if (tfStatus.getText() != "") {
		ArrayList<Room> arraylist = new ArrayList<>();
		for (int i = 0; i< accountlist.size(); i++) {
			if (accountlist.get(i).getStatus().compareTo(tfStatus.getText()) == 0) {
				arraylist.add(accountlist.get(i));
			}
		}
		accountlist.clear();
		accountlist.addAll(arraylist);
	}
}
}
