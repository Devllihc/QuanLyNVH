package application;
	
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import DAO.ActivityDAO;
import DAO.DeviceActivityDAO;
import DAO.DeviceDAO;
import DAO.ManagerAccountDAO;
import DAO.RentDAO;
import DAO.RoomDAO;
import DAO.WorkDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Activity;
import model.Device;
import model.DeviceActivity;
import model.ManagerAccount;
import model.Rent;
import model.Work;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class test{
	public static void main(String[] args) {
		 ArrayList<Timestamp> a = ActivityDAO.getInstance().CheckFreeActivity(2, Timestamp.valueOf("2020-01-01 10:00:00.0"),  Timestamp.valueOf("2024-05-01 18:00:00.0"));
        }		
}
	
