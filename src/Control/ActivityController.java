package Control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.AccountDAO;
import DAO.ActivityDAO;
import DAO.DeviceActivityDAO;
import DAO.DeviceDAO;
import DAO.RentDAO;
import DAO.WorkDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Account;
import model.Activity;
import model.Device;
import model.Rent;
import model.Work;

public class ActivityController implements Initializable {
	@FXML
	private TableView<Activity> table;
	
	@FXML
	private TableColumn<Activity,String> activityid;
	
	@FXML
	private TableColumn<Activity,String> activityname;

	@FXML
	private TableColumn<Activity,String> roomid;
	
	@FXML
	private TableColumn<Activity,String> note;
	
	@FXML
	private TableColumn<Activity,LocalDateTime> timestart;
	
	@FXML
	private TableColumn<Activity,LocalDateTime> timefinish;
	@FXML
	private TextField textname;
	
	private ObservableList<Activity> accountlist=FXCollections.observableArrayList();
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ArrayList<Activity> a = ActivityDAO.getInstance().selectAll();
        accountlist.addAll(a);
		activityid.setCellValueFactory(new PropertyValueFactory<Activity, String>("activityid"));
		activityname.setCellValueFactory(new PropertyValueFactory<Activity, String>("activityname"));
		roomid.setCellValueFactory(new PropertyValueFactory<Activity, String>("roomid"));
		timestart.setCellValueFactory(new PropertyValueFactory<Activity, LocalDateTime>("timestart"));		
		timefinish.setCellValueFactory(new PropertyValueFactory<Activity, LocalDateTime>("timefinish"));
		note.setCellValueFactory(new PropertyValueFactory<Activity, String>("note"));
		table.setItems(accountlist);
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
	public void insertAct(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/InsertActivity.fxml"));
		stage = new Stage();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void detailAct(ActionEvent event) throws IOException{
		Activity Selected = table.getSelectionModel().getSelectedItem();
		FXMLLoader f = new FXMLLoader(getClass().getResource("/view/DetailActivity.fxml"));
		root = f.load();
		DetailActControl dac=f.getController();
		dac.getInfor(Selected.getActivityid());
		stage = new Stage();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void deleteAct(ActionEvent event) throws IOException{
		Activity Selected = table.getSelectionModel().getSelectedItem();
		DeviceActivityDAO.getInstance().deleteByActivityId(Selected.getActivityid());
		ActivityDAO.getInstance().delete(Selected);
		accountlist.clear();
		ArrayList<Activity> a = ActivityDAO.getInstance().selectAll();
		accountlist.addAll(a);
	}
	public void searchByName(ActionEvent event) throws IOException {
		
		if (textname.getText() != "") {
			ArrayList<Activity> arraylist = new ArrayList<>();
			for (int i = 0; i< accountlist.size(); i++) {
				if (accountlist.get(i).getActivityname().compareTo(textname.getText()) == 0) {
					arraylist.add(accountlist.get(i));
				}
			}
			accountlist.clear();
			accountlist.addAll(arraylist);
		}
	}
	public void SearchNearOffDate(ActionEvent event) throws IOException{
		Activity a = ActivityDAO.getInstance().SearchNearOffDate();
		accountlist.clear();
		accountlist.add(a);
	}
	public void SearchOffDate(ActionEvent event) throws IOException{
		ArrayList<Activity> a = ActivityDAO.getInstance().searchOffDate();
		accountlist.clear();
		accountlist.addAll(a);
	}
	public void clear(ActionEvent event) {
		ArrayList<Activity> a =	ActivityDAO.getInstance().selectAll();
		accountlist.clear();
        accountlist.addAll(a);
}
}
