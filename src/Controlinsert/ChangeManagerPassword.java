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

public class ChangeManagerPassword {
	@FXML 
	TextField newuserid;
	@FXML
	TextField newaccountname;
	@FXML
	PasswordField newpassword1;
	@FXML
	PasswordField newpassword2;
	@FXML
	PasswordField oldpassword;
	public void ChangePasswords(ActionEvent event) throws IOException{
		if(newpassword1.getText().compareTo(newpassword2.getText())==0) {
		ManagerAccount a = new ManagerAccount(newuserid.getText(),newaccountname.getText(),oldpassword.getText());
		if(a.changePassword(newpassword1.getText())) {
			showAlert(AlertType.INFORMATION,"thông báo", "đổi mật khẩu thành công");
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "tên đăng nhập hoặc mật khẩu không chính xác");
		}
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Xác nhận mật khẩu ko chính xác");
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
