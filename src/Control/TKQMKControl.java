package Control;

import java.io.IOException;

import DAO.ManagerAccountDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.ManagerAccount;

public class TKQMKControl {
	@FXML
	private TextField accountname;
	@FXML
	private TextField answer;
	@FXML
	private TextField newpassword;
	@FXML
	private Label secques;
	public void secQues(ActionEvent event) throws IOException{
		ManagerAccount a= ManagerAccountDAO.getInstance().selectByName(accountname.getText());
		secques.setText(a.getSecurityQuestion());
	}
	public void takePassword(ActionEvent event) throws IOException{
		if(ManagerAccountDAO.getInstance().forgetPasswork(accountname.getText(),answer.getText(),newpassword.getText())>0) {
			showAlert(AlertType.INFORMATION,"Thông báo", "thay đổi mật khẩu thành công");
		}
		else {
			showAlert(AlertType.ERROR,"Lỗi", "Câu trả lời không chính xác");
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
