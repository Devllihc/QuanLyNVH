package Control;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.AccountDAO;
import DAO.ActivityDAO;
import DAO.DeviceActivityDAO;
import DAO.DeviceDAO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.Room;

public class InsertActControl implements Initializable {

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
	private TableView<DeviceActivity> table;
	
	@FXML
	private TableColumn<DeviceActivity,String> devicename;
	@FXML
	private TableColumn<DeviceActivity,String> deviceid;
	
	@FXML
	private TableColumn<DeviceActivity,String> amount;
	
	private ObservableList<DeviceActivity> accountlist=FXCollections.observableArrayList();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			devicename.setCellValueFactory(new PropertyValueFactory<DeviceActivity, String>("name"));
			deviceid.setCellValueFactory(new PropertyValueFactory<DeviceActivity, String>("deviceid"));
			amount.setCellValueFactory(new PropertyValueFactory<DeviceActivity, String>("amount"));
			table.setItems(accountlist);
			table.setOnMouseClicked(event -> {
	            if (event.getClickCount() == 1) {
	                // Lấy dữ liệu của hàng được chọn
	                DeviceActivity selectedDevice = table.getSelectionModel().getSelectedItem();
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
				Room a=RoomDAO.getInstance().selectByName(textrname.getText());
				Activity newact=new Activity(textaname.getText(),a.getRoomId(),Timestamp.valueOf(textstart.getText()),Timestamp.valueOf(textfinish.getText()),textnote.getText());
				ActivityDAO.getInstance().insert(newact);
				showAlert(AlertType.INFORMATION,"Thông báo", "Tạo hoạt động thành công ");
				int activityid=ActivityDAO.getInstance().SearchID(newact);
				for(int i=0;i<accountlist.size();i++) {
					accountlist.get(i).setActivityid(activityid);
					DeviceActivityDAO.getInstance().insert(accountlist.get(i));
				}
			}
			else {
				showAlert(AlertType.ERROR,"Lỗi", "Phòng không khả dụng lúc này");
			}
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Phòng không tồn tại");
		}
}		
	public void insertDeviceActivity(ActionEvent event) throws IOException{
		if (DeviceDAO.getInstance().checkexistByName(textdname.getText())) {
			if(DeviceDAO.getInstance().selectByNameUse(textdname.getText()).getAmount() - DeviceDAO.getInstance().totalDeviceInUse(DeviceDAO.getInstance().selectByName(textdname.getText()).getDeviceId(), Timestamp.valueOf(textstart.getText()), Timestamp.valueOf(textstart.getText()))> Integer.parseInt(textamount.getText())) {
			Device dd=DeviceDAO.getInstance().selectByNameUse(textdname.getText());
			DeviceActivity d= new DeviceActivity(dd.getDeviceId(),Integer.parseInt(textamount.getText()),textdname.getText());
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
	public void deleteDeviceActivity(ActionEvent event) throws IOException{
		DeviceActivity Selected = table.getSelectionModel().getSelectedItem();
		if(Selected != null) {
			DeviceActivityDAO.getInstance().delete(Selected);
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
