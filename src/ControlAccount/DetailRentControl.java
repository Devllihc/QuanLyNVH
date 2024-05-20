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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.DeviceRent;
import model.Rent;
import model.Renter;
import model.Room;

public class DetailRentControl implements Initializable {
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
	private TableView<DeviceRent> table;
	
	@FXML
	private TableColumn<DeviceRent,String> devicename;
	@FXML
	private TableColumn<DeviceRent,String> deviceid;
	
	@FXML
	private TableColumn<DeviceRent,String> amount;
	@FXML
	private Label idlabel;
	@FXML
	private Label cost;
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

	public void getInfor(int rentid) {
		Rent act=RentDAO.getInstance().selectByID(rentid);
		textaname.setText(act.getRentname());
		Room r=RoomDAO.getInstance().selectByID(act.getRoomid());
		textrname.setText(r.getName());
		textstart.setText(act.getTimestart().toString());
		textfinish.setText(act.getTimefinish().toString());
		textnote.setText(act.getNote());
		Renter renter =RenterDAO.getInstance().selectByID(act.getRenterid());
		textrentname.setText(renter.getName());
		textrentid.setText(renter.getId());
		textsdt.setText(renter.getSdt());
		textaddress.setText(renter.getAddress());
		textrentnote.setText(renter.getNote());
		ArrayList<DeviceRent> a=DeviceRentDAO.getInstance().selectByRentId(rentid);
		for(int i=0;i<a.size();i++) {
			System.out.println(a.get(i).toString());
		}
		idlabel.setText(String.valueOf(rentid));
		cost.setText(String.valueOf(RentDAO.getInstance().cost(rentid))+"VNĐ");
		accountlist.addAll(a);
	}
	public void insertDeviceRent(ActionEvent event) throws IOException{
		if (DeviceDAO.getInstance().checkexistByName(textdname.getText())) {
			if(DeviceDAO.getInstance().selectByNameUse(textdname.getText()).getAmount() - DeviceDAO.getInstance().totalDeviceInUse(DeviceDAO.getInstance().selectByName(textdname.getText()).getDeviceId(), Timestamp.valueOf(textstart.getText()), Timestamp.valueOf(textstart.getText()))> Integer.parseInt(textamount.getText())) {
		Device dd=DeviceDAO.getInstance().selectByNameUse(textdname.getText());
		DeviceRent d= new DeviceRent(dd.getDeviceId(),Integer.parseInt(textamount.getText()),textdname.getText());
		accountlist.add(d);
	}
		else {
		showAlert(AlertType.ERROR,"Lỗi", "Thiết bị không đủ");
		}}
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
	public void submit(ActionEvent event) throws IOException{
		if (RoomDAO.getInstance().findRoomByName(textrname.getText())) {
			Renter renter=new Renter(textrentid.getText(),textrentname.getText(),textsdt.getText(),textaddress.getText(),textrentnote.getText());
			if(RenterDAO.getInstance().checkexistedrenter(renter)) {
				DeviceRentDAO.getInstance().deleteByRentId(Integer.parseInt(idlabel.getText()));
				Room a=RoomDAO.getInstance().selectByName(textrname.getText());
				Rent newrent=new Rent(textaname.getText(),a.getRoomId(),Timestamp.valueOf(textstart.getText()),Timestamp.valueOf(textfinish.getText()),textrentid.getText(),textnote.getText());
				newrent.setRentid(Integer.parseInt(idlabel.getText()));
				System.out.println(idlabel.getText());
				RentDAO.getInstance().update(newrent);
				showAlert(AlertType.INFORMATION,"Thông báo", "Sửa đơn thuê thành công ");
				int rentid=RentDAO.getInstance().SearchID(newrent);
				for(int i=0;i<accountlist.size();i++) {
					accountlist.get(i).setRentid(rentid);
					DeviceRentDAO.getInstance().insert(accountlist.get(i));
				}
		RenterDAO.getInstance().update(renter);
			}
			else {
				showAlert(AlertType.ERROR,"Lỗi", "Thông tin người thuê không đúng hoặc không tồn tại");
			}
		}
		
		
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Phòng không tồn tại");
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
