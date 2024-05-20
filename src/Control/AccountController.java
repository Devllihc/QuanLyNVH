package Control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.AccountDAO;
import DAO.LocalPersonDAO;
import DAO.ManagerAccountDAO;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Account;
import model.ManagerAccount;
import model.Person;

public class AccountController implements Initializable {
	@FXML
	private TableView<Account> table;
	
	@FXML
	private TableColumn<Account,String> userId;
	
	@FXML
	private TableColumn<Account,String> accountName;
	
	@FXML
	private TableColumn<Account,String> password;
	
	@FXML
	private TableColumn<Account,String> note;
	
	@FXML
	private TableView<ManagerAccount> table1;
	
	@FXML
	private TableColumn<ManagerAccount,String> manageruserid;
	
	@FXML
	private TableColumn<ManagerAccount,String> manageraccountname;
	
	@FXML
	private TableColumn<ManagerAccount,String> managerpassword;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	private ObservableList<Account> accountlist=FXCollections.observableArrayList();
	private ObservableList<ManagerAccount> manageraccountlist=FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

        ArrayList<Account> a = AccountDAO.getInstance().selectAll();
        accountlist.addAll(a);
		userId.setCellValueFactory(new PropertyValueFactory<Account, String>("userId"));
		password.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
		accountName.setCellValueFactory(new PropertyValueFactory<Account, String>("accountName"));
		note.setCellValueFactory(new PropertyValueFactory<Account, String>("note"));
		ArrayList<ManagerAccount> ma = ManagerAccountDAO.getInstance().selectAll();
        manageraccountlist.addAll(ma);
        manageruserid.setCellValueFactory(new PropertyValueFactory<ManagerAccount, String>("userId"));
        managerpassword.setCellValueFactory(new PropertyValueFactory<ManagerAccount, String>("password"));
		manageraccountname.setCellValueFactory(new PropertyValueFactory<ManagerAccount, String>("accountName"));
		table1.setItems(manageraccountlist);
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
	
	public void makeNewAccount(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/MakeNewAccount.fxml"));
		stage=new Stage();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
		
	}
	public void deleteAccount(ActionEvent event){
		Account Selected = table.getSelectionModel().getSelectedItem();
		if(Selected != null) {
		AccountDAO.getInstance().delete(Selected);
		accountlist.remove(Selected);
		}
	}
	public void ChangePassword(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/ChangePassword.fxml"));
		stage=new Stage();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void makeNewManagerAcount(ActionEvent event)throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/MakeNewManagerAccount2.fxml"));
		stage=new Stage();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void deleteManagerAccount(ActionEvent event){
		ManagerAccount Selected = table1.getSelectionModel().getSelectedItem();
		if(Selected != null) {
		ManagerAccountDAO.getInstance().delete(Selected);
		manageraccountlist.remove(Selected);
		}
	}
	public void ChangeManagerPassword(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/ChangeManagerPassword.fxml"));
		stage=new Stage();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
}
