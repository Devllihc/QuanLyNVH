package Controlinsert;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Account;
import model.ManagerAccount;


public class CreateNewAccount {
	@FXML 
	TextField newuserid;
	@FXML
	TextField newaccountname;
	@FXML
	PasswordField newpassword1;
	@FXML
	PasswordField newpassword2;
	public void createNewAccount(ActionEvent event) throws IOException{
		if(newpassword1.getText().compareTo(newpassword2.getText())==0) {
		Account a = new Account(newuserid.getText(),newaccountname.getText(),newpassword1.getText());
		ManagerAccount b = new ManagerAccount(newuserid.getText(),newaccountname.getText(),newpassword1.getText());
		if(!b.checkManagerAccountName()) {
		if(ManagerAccount.makeNewAccount(a)>0) {
			showAlert(AlertType.INFORMATION,"thông báo", "Tạo tài khoảng thành công");
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Tạo tài khoản không thành công. Tài khoản đã tồn tại");
		}
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Tạo tài khoản không thành công. Tài khoản đã tồn tại");
		}
	}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Tạo tài khoản không thành công. Xác nhận mật khẩu ko chính xác");
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
