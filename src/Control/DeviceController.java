package Control;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.DeviceDAO;
import DAO.RentDAO;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Device;
import model.Rent;


public class DeviceController implements Initializable {
	@FXML
	private TableView<Device> table;
	
	@FXML
	private TableColumn<Device,String> deviceid;
	
	@FXML
	private TableColumn<Device,String> devicename;
	
	@FXML
	private TableColumn<Device,String> amount;
	
	@FXML
	private TableColumn<Device,String> price;
	
	@FXML
	private TableColumn<Device,String> status;
	
	@FXML
	private TableColumn<Device,String> note;
	
	@FXML
	private TableColumn<Device,String> roomid;
	
	@FXML
	private TextField newDevicename;
	
	@FXML
	private TextField newAmount;
	
	@FXML
	private TextField newPrice;
	
	@FXML
	private TextField newStatus;
	
	@FXML
	private TextField newRoomid;
	
	@FXML
	private TextField newNote;
	

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfStatus;

	@FXML
	private TextField tfRoomid;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	private ObservableList<Device> accountlist=FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Device> a = DeviceDAO.getInstance().selectAll();
        accountlist.addAll(a);
        deviceid.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceId"));
        roomid.setCellValueFactory(new PropertyValueFactory<Device, String>("roomId"));
        devicename.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        status.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
        note.setCellValueFactory(new PropertyValueFactory<Device, String>("note"));
        price.setCellValueFactory(new PropertyValueFactory<Device, String>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<Device, String>("amount"));
        table.setItems(accountlist);
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Lấy dữ liệu của hàng được chọn
                Device selectedDevice = table.getSelectionModel().getSelectedItem();
                if (selectedDevice != null) {
                	newDevicename.setText(selectedDevice.getName());
                	newAmount.setText(String.valueOf(selectedDevice.getAmount()));
                	newPrice.setText(String.valueOf(selectedDevice.getPrice()));
                	newStatus.setText(selectedDevice.getStatus());
                	newRoomid.setText(String.valueOf(selectedDevice.getRoomId()));
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
	public void insertDevice(ActionEvent event) throws IOException{
		Device x= new Device(newDevicename.getText(),Integer.parseInt(newAmount.getText()), Integer.parseInt(newPrice.getText()),newStatus.getText(),Integer.parseInt(newRoomid.getText()),newNote.getText());
		ArrayList<Device> a = DeviceDAO.getInstance().selectAll();
		int k=0;
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getName().compareTo(newDevicename.getText())==0 && a.get(i).getStatus().compareTo(newStatus.getText())==0) {
				k=1;
				break;
			}
		}
		if(k==0) {
			DeviceDAO.getInstance().insert(x);
			showAlert(AlertType.INFORMATION,"thông báo", "Nhập thành công");
			accountlist.clear();
			ArrayList<Device> b = DeviceDAO.getInstance().selectAll();
	        accountlist.addAll (b);
	        deviceid.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceId"));
	        roomid.setCellValueFactory(new PropertyValueFactory<Device, String>("roomId"));
	        devicename.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
	        status.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
	        note.setCellValueFactory(new PropertyValueFactory<Device, String>("note"));
	        price.setCellValueFactory(new PropertyValueFactory<Device, String>("price"));
	        amount.setCellValueFactory(new PropertyValueFactory<Device, String>("amount"));
	        table.setItems(accountlist);
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Thiết bị đã tồn tại trong cơ sở dữ liệu");
		}
	}
	
	private static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // HeaderText set null để không hiển thị tiêu đề phụ
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	public void deleteDevice(ActionEvent event){
		Device Selected = table.getSelectionModel().getSelectedItem();
		if(Selected != null) {
		DeviceDAO.getInstance().delete(Selected);
		accountlist.remove(Selected);
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi"," Bạn chưa chọn thiết bị");
		}
	}
	public void updateDevice(ActionEvent event) {
		Device x= new Device(newDevicename.getText(),Integer.parseInt(newAmount.getText()), Integer.parseInt(newPrice.getText()),newStatus.getText(),Integer.parseInt(newRoomid.getText()),newNote.getText());
		Device a=DeviceDAO.getInstance().searchByNameStatus(x.getName(), x.getStatus());
		x.setDeviceId(a.getDeviceId());
		DeviceDAO.getInstance().update(x);
		System.out.println(x.toString());
		accountlist.clear();
		ArrayList<Device> b = DeviceDAO.getInstance().selectAll();
		accountlist.clear();
        accountlist.addAll (b);
        table.setItems(accountlist);
        newDevicename.setText(null);
    	newAmount.setText(null);
    	newPrice.setText(null);
    	newStatus.setText(null);
    	newRoomid.setText(null);
    	newNote.setText(null);	
	}
	public void clearDevice(ActionEvent event) {
		ArrayList<Device> a = DeviceDAO.getInstance().selectAll();
		accountlist.clear();
        accountlist.addAll(a);
		newDevicename.setText(null);
    	newAmount.setText(null);
    	newPrice.setText(null);
    	newStatus.setText(null);
    	newRoomid.setText(null);
    	newNote.setText(null);
	}
	public void searchByName(ActionEvent event) throws IOException {
		
		if (tfName.getText() != "") {
			ArrayList<Device> arraylist = new ArrayList<>();
			for (int i = 0; i< accountlist.size(); i++) {
				if (accountlist.get(i).getName().compareTo(tfName.getText()) == 0) {
					arraylist.add(accountlist.get(i));
				}
			}
			accountlist.clear();
			accountlist.addAll(arraylist);
		}
	}
public void searchByStatus(ActionEvent event) throws IOException {
		
		if (tfStatus.getText() != "") {
			ArrayList<Device> arraylist = new ArrayList<>();
			for (int i = 0; i< accountlist.size(); i++) {
				if (accountlist.get(i).getStatus().compareTo(tfStatus.getText()) == 0) {
					arraylist.add(accountlist.get(i));
				}
			}
			accountlist.clear();
			accountlist.addAll(arraylist);
		}
	}
public void searchByRoomid(ActionEvent event) throws IOException {
	
	if (tfRoomid.getText() != "") {
		ArrayList<Device> arraylist = new ArrayList<>();
		for (int i = 0; i< accountlist.size(); i++) {
			if (String.valueOf(accountlist.get(i).getRoomId()).compareTo(tfRoomid.getText()) == 0) {
				arraylist.add(accountlist.get(i));
			}
		}
		accountlist.clear();
		accountlist.addAll(arraylist);
	}

}
}
	
	

