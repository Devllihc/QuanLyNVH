package Control;


import java.io.IOException;
import java.time.LocalDateTime;

import DAO.AccountDAO;
import DAO.ManagerAccountDAO;
import DAO.WorkDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.ManagerAccount;
import model.Work;

public class LogginController {
	@FXML
	TextField accountname;
	@FXML
	PasswordField password;
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void login(ActionEvent event) throws IOException{
		String username = accountname.getText();
		String ps= password.getText();
		ManagerAccount a=new ManagerAccount(username,ps);
		if(a.checkManagerAccount()) {
			a=ManagerAccountDAO.getInstance().selectByName(username);
			Work t= new Work(a.getUserId(),a.getAccountName(),LocalDateTime.now());
			WorkDAO.getInstance().insert(t);
			root = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene= new Scene(root);
			String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Tài khoản hoặc mật khẩu không chính xác");
		}
		
	}
	public void loginAccount(ActionEvent event) throws IOException{
		String username = accountname.getText();
		String ps= password.getText();
		Account a=new Account(username,ps);
		if(a.checkAccount()) {
			a=AccountDAO.getInstance().selectByName(username);
			Work t= new Work(a.getUserId(),a.getAccountName(),LocalDateTime.now());
			WorkDAO.getInstance().insert(t);
			root = FXMLLoader.load(getClass().getResource("/viewAccount/Menu.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene= new Scene(root);
			String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Tài khoản hoặc mật khẩu không chính xác");
		}
		
	}
	private static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // HeaderText set null để không hiển thị tiêu đề phụ
        alert.setContentText(content);
        alert.showAndWait();
    }
	public void forgetPassword (ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/TKQMK.fxml"));
		stage = new Stage();
		scene= new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

}
