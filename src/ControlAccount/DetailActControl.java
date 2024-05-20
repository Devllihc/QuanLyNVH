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
import DAO.RoomDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.Room;

public class DetailActControl implements Initializable {
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
	@FXML
	private Label idlabel;
	
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

	public void getInfor(int activityid) {
		Activity act=ActivityDAO.getInstance().selectByID(activityid);
		textaname.setText(act.getActivityname());
		Room r=RoomDAO.getInstance().selectByID(act.getRoomid());
		textrname.setText(r.getName());
		textstart.setText(act.getTimestart().toString());
		textfinish.setText(act.getTimefinish().toString());
		textnote.setText(act.getNote());
		ArrayList<DeviceActivity> a=DeviceActivityDAO.getInstance().selectByActivityId(activityid);
		for(int i=0;i<a.size();i++) {
			System.out.println(a.get(i).toString());
		}
		idlabel.setText(String.valueOf(activityid));
		accountlist.addAll(a);
	}
	public void update(ActionEvent event) throws IOException{
		if (RoomDAO.getInstance().findRoomByName(textrname.getText())) {
			DeviceActivityDAO.getInstance().deleteByActivityId(Integer.parseInt(idlabel.getText()));
			Room a=RoomDAO.getInstance().selectByName(textrname.getText());
			Activity newact=new Activity(textaname.getText(),a.getRoomId(),Timestamp.valueOf(textstart.getText()),Timestamp.valueOf(textfinish.getText()),textnote.getText());
			newact.setActivityid(Integer.parseInt(idlabel.getText()));showAlert(AlertType.INFORMATION,"Thông báo", "Sửa đơn thuê thành công ");
			
			ActivityDAO.getInstance().update(newact);
			for(int i=0;i<accountlist.size();i++) {
				accountlist.get(i).setActivityid(Integer.parseInt(idlabel.getText()));
				DeviceActivityDAO.getInstance().insert(accountlist.get(i));
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
			showAlert(AlertType.ERROR,"Lỗi", "Thiết bị không đủ");
		}}
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
