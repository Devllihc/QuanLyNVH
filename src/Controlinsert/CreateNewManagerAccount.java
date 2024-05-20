package Controlinsert;

import java.io.IOException;

import DAO.AccountDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Account;
import model.ManagerAccount;


public class CreateNewManagerAccount {
	@FXML 
	TextField newuserid;
	@FXML
	TextField newaccountname;
	@FXML
	PasswordField newpassword1;
	@FXML
	PasswordField newpassword2;
	@FXML
	TextField secques;
	@FXML
	TextField answer;
	public void createNewAccount(ActionEvent event) throws IOException{
		if(newpassword1.getText().compareTo(newpassword2.getText())==0) {
		ManagerAccount a = new ManagerAccount(newuserid.getText(),newaccountname.getText(),newpassword1.getText(),secques.getText(),answer.getText());
		Account b=new Account(newuserid.getText(),newaccountname.getText(),newpassword1.getText());
		if(!b.checkAccountName()) {
		if(ManagerAccount.makeNewManagerAccount(a)>0) {
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
