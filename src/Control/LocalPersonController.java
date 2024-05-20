package Control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.AccountDAO;
import DAO.LocalPersonDAO;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Account;
import model.Person;
import model.Room;

public class LocalPersonController implements Initializable {
	@FXML
	private TableView<Person> table;
	
	@FXML
	private TableColumn<Person,String> id;
	
	@FXML
	private TableColumn<Person,String> name;
	
	@FXML
	private TableColumn<Person,String> hostid;
	
	@FXML
	private TableColumn<Person,String> sdt;
	
	@FXML
	private TableColumn<Person,String> address;
	
	@FXML
	private TableColumn<Person,String> status;
	
	@FXML
	private TableColumn<Person,String> note;
	@FXML
	private TextField textid;
	@FXML
	private TextField textname;
	@FXML
	private TextField texthostid;
	@FXML
	private TextField textsdt;
	@FXML
	private TextField textaddress;
	@FXML
	private TextField textstatus;
	@FXML
	private TextField textnote;
	
	@FXML
	private TextField textsname;
	
	@FXML
	private TextField textsid;
	@FXML
	private TextField textshostid;
	@FXML
	private TextField textsstatus;
	
	
	private ObservableList<Person> accountlist=FXCollections.observableArrayList();
	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

        ArrayList<Person> a = LocalPersonDAO.getInstance().selectAll();
        accountlist.addAll(a);
		id.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		hostid.setCellValueFactory(new PropertyValueFactory<Person, String>("hostId"));
		sdt.setCellValueFactory(new PropertyValueFactory<Person, String>("sdt"));
		address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
		status.setCellValueFactory(new PropertyValueFactory<Person, String>("status"));
		note.setCellValueFactory(new PropertyValueFactory<Person, String>("note"));
		table.setItems(accountlist);
		table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Lấy dữ liệu của hàng được chọn
                Person selectedPerson = table.getSelectionModel().getSelectedItem();
                if (selectedPerson != null) {
                	textid.setText(selectedPerson.getId());
                	textname.setText(selectedPerson.getName());
                	texthostid.setText(selectedPerson.getHostId());
                	textsdt.setText(selectedPerson.getSdt());
                	textstatus.setText(selectedPerson.getStatus());
                	textaddress.setText(selectedPerson.getAddress());
                	textnote.setText(selectedPerson.getNote());
                	
                }
          
            }
	});
	}
	public void searchByName (ActionEvent event) throws IOException{
		System.out.println(textsname.getText());
		if(textsname.getText()!="") {
		ArrayList<Person> accountlist1= new ArrayList<>();
		for (int i=0;i<accountlist.size();i++) {
			if(accountlist.get(i).getName().equals(textsname.getText())) {
				accountlist1.add(accountlist.get(i));
			}
		}
		accountlist.clear();
		accountlist.addAll(accountlist1);		}
	}
	
	public void searchById (ActionEvent event) throws IOException{
		if(textsid.getText()!="") {
		ArrayList<Person> accountlist1= new ArrayList<>();
		for (int i=0;i<accountlist.size();i++) {
			if(accountlist.get(i).getId().equals(textsid.getText())) {
				accountlist1.add(accountlist.get(i));
			}
		}
		accountlist.clear();
		accountlist.addAll(accountlist1);
		}
	}
	
	public void searchByHostId (ActionEvent event) throws IOException{
		if(textshostid.getText()!="") {
		ArrayList<Person> accountlist1= new ArrayList<>();
		for (int i=0;i<accountlist.size();i++) {
			if(accountlist.get(i).getHostId().equals(textshostid.getText())) {
				accountlist1.add(accountlist.get(i));
			}
		}
		accountlist.clear();
		accountlist.addAll(accountlist1);
		}
	}
	public void searchByStatus (ActionEvent event) throws IOException{
		if(textsstatus.getText()!="") {
		ArrayList<Person> accountlist1= new ArrayList<>();
		for (int i=0;i<accountlist.size();i++) {
			if(accountlist.get(i).getStatus().equals(textsstatus.getText())) {
				accountlist1.add(accountlist.get(i));
			}
		}
		accountlist.clear();
		accountlist.addAll(accountlist1);
		}
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
	public void insertLocalPerson(ActionEvent event) throws IOException{
		Person x= new Person(textid.getText(),textname.getText(),texthostid.getText(),textsdt.getText(),textstatus.getText(),textaddress.getText(),textnote.getText());
		ArrayList<Person> a = LocalPersonDAO.getInstance().selectAll();
		int k=0;
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getId().compareTo(textid.getText())==0) {
				k=1;
				break;
			}
		}
		if(k==0) {
			LocalPersonDAO.getInstance().insert(x);
			showAlert(AlertType.INFORMATION,"thông báo", "Nhập thành công");
			accountlist.add(x);
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "id đã tồn tại trong cơ sở dữ liệu");
		}
	}
	public void updateLocalPerson(ActionEvent event) throws IOException{
		Person selectedPerson = table.getSelectionModel().getSelectedItem();
		accountlist.remove(selectedPerson);
		Person x= new Person(textid.getText(),textname.getText(),texthostid.getText(),textsdt.getText(),textstatus.getText(),textaddress.getText(),textnote.getText());
		LocalPersonDAO.getInstance().update(x);
		accountlist.add(x);
		textid.setText(null);
    	textname.setText(null);
    	texthostid.setText(null);
    	textsdt.setText(null);
    	textstatus.setText(null);
    	textaddress.setText(null);
    	textnote.setText(null);
		
	}	
	public void deleteLocalPerson(ActionEvent event){
		Person Selected = table.getSelectionModel().getSelectedItem();
		if(Selected != null) {
		LocalPersonDAO.getInstance().delete(Selected);
		accountlist.remove(Selected);
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "ko thành công");
		}
	}
	public void clearInfor(ActionEvent event){
		textid.setText(null);
    	textname.setText(null);
    	texthostid.setText(null);
    	textsdt.setText(null);
    	textstatus.setText(null);
    	textaddress.setText(null);
    	textnote.setText(null);
    	accountlist.clear();
    	ArrayList<Person> a = LocalPersonDAO.getInstance().selectAll();
        accountlist.addAll(a);
	}
	
	private static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // HeaderText set null để không hiển thị tiêu đề phụ
        alert.setContentText(content);
        alert.showAndWait();
    }
}
