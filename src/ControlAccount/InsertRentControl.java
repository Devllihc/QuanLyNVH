package ControlAccount;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.ActivityDAO;
import DAO.DeviceActivityDAO;
import DAO.DeviceDAO;
import DAO.DeviceRentDAO;
import DAO.RentDAO;
import DAO.RenterDAO;
import DAO.RoomDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.DeviceRent;
import model.Rent;
import model.Renter;
import model.Room;

public class InsertRentControl implements Initializable {
	@FXML
	private TextField textaname;
	@FXML
	private TextField textrname;
	@FXML
	private TextField textstart;
	@FXML
	private TextField textfinish;
	@FXML
	private TextField textnote;
	@FXML
	private TextField textdname;
	@FXML
	private TextField textamount;
	@FXML
	private TextField textrentid;
	@FXML
	private TextField textrentname;
	@FXML
	private TextField textsdt;
	@FXML
	private TextField textaddress;
	@FXML
	private TextField textrentnote;
	@FXML
	private TableView<DeviceRent> table;
	
	@FXML
	private TableColumn<DeviceRent,String> devicename;
	@FXML
	private TableColumn<DeviceRent,String> deviceid;
	
	@FXML
	private TableColumn<DeviceRent,String> amount;
	@FXML
	private Label idlabel;
	
	private ObservableList<DeviceRent> accountlist=FXCollections.observableArrayList();
	
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			devicename.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("name"));
			deviceid.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("deviceid"));
			amount.setCellValueFactory(new PropertyValueFactory<DeviceRent, String>("amount"));
			table.setItems(accountlist);
			table.setOnMouseClicked(event -> {
	            if (event.getClickCount() == 1) {
	                // Lấy dữ liệu của hàng được chọn
	                DeviceRent selectedDevice = table.getSelectionModel().getSelectedItem();
	                if (selectedDevice != null) {
	                	textdname.setText(selectedDevice.getName());
	                	textamount.setText(String.valueOf(selectedDevice.getAmount()));   
	                }
	               
	            }
	        });
		
	}
	public void submit(ActionEvent event) throws IOException{
		if (RoomDAO.getInstance().findRoomByName(textrname.getText())) {
			if(RoomDAO.getInstance().checkRoomFree(RoomDAO.getInstance().selectByName(textrname.getText()).getRoomId(), Timestamp.valueOf(textstart.getText()), Timestamp.valueOf(textstart.getText()))) {
				Renter renter=new Renter(textrentid.getText(),textrentname.getText(),textsdt.getText(),textaddress.getText(),textrentnote.getText());
				Room a=RoomDAO.getInstance().selectByName(textrname.getText());
				Rent newrent=new Rent(textaname.getText(),a.getRoomId(),Timestamp.valueOf(textstart.getText()),Timestamp.valueOf(textfinish.getText()),textrentid.getText(),textnote.getText());
				RentDAO.getInstance().insert(newrent);
				showAlert(AlertType.INFORMATION,"Thông báo", "Tạo đơn thuê thành công ");
				int rentid=RentDAO.getInstance().SearchID(newrent);
				for(int i=0;i<accountlist.size();i++) {
					accountlist.get(i).setRentid(rentid);
					DeviceRentDAO.getInstance().insert(accountlist.get(i));
				if(!RenterDAO.getInstance().checkexistedrenter(renter)) {
					RenterDAO.getInstance().insert(renter);
					}
				}
				
				
			}
			else {
				showAlert(AlertType.ERROR,"Lỗi", "Phòng không khả dụng tại thời điểm này ");
			}
		}
		
		
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Phòng không tồn tại");
		}
}
	public void insertDeviceRent(ActionEvent event) throws IOException{
		if (DeviceDAO.getInstance().checkexistByName(textdname.getText())) {
			if(DeviceDAO.getInstance().selectByNameUse(textdname.getText()).getAmount() - DeviceDAO.getInstance().totalDeviceInUse(DeviceDAO.getInstance().selectByName(textdname.getText()).getDeviceId(), Timestamp.valueOf(textstart.getText()), Timestamp.valueOf(textstart.getText()))> Integer.parseInt(textamount.getText())) {
			Device dd=DeviceDAO.getInstance().selectByNameUse(textdname.getText());
			DeviceRent d= new DeviceRent(dd.getDeviceId(),Integer.parseInt(textamount.getText()),textdname.getText());
			accountlist.add(d);
			}
			else {
				showAlert(AlertType.ERROR,"Lỗi", "Không đủ số lượng thiết bị");
			}
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Thiết bị không tồn tại");
		}
	}
	public void deleteDeviceRent(ActionEvent event) throws IOException{
		DeviceRent Selected = table.getSelectionModel().getSelectedItem();
		if(Selected != null) {
			DeviceRentDAO.getInstance().delete(Selected);
			accountlist.remove(Selected);
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi"," Bạn chưa chọn thiết bị");
		}
	}
	private static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // HeaderText set null để không hiển thị tiêu đề phụ
        alert.setContentText(content);
        alert.showAndWait();
    }
	
}

