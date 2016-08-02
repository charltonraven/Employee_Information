package Notification;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Notification extends Application implements EventHandler<ActionEvent> {

	Stage window;
	Button btnSubmit;
	Button btnCancel;

	Label lblAsk;

	Label lblColon;
	Label lblComma;
	Label lblWarning;

	static ChoiceBox<String> day, hours, minutes, AMorPM;

	public static void main(String[] args) throws UnknownHostException {

		launch(args);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage PrimaryStage) throws Exception {

		window = PrimaryStage;
		window.setTitle("Data Transfer Notification");

		// button
		btnSubmit = new Button("Submit");
		btnCancel = new Button("Cancel");

		// change button size
		btnSubmit.setStyle("-fx-font-size: 13pt;");
		btnCancel.setStyle("-fx-font-size: 13pt;");

		// Layout Gridview
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.setHgap(5);
		grid.setVgap(10);

		// Labels
		lblAsk = new Label("Schedule Your Data Transfer");
		// lblTime = new Label("Schedule a time for your Data Transfer: ");
		lblColon = new Label(":");
		lblComma = new Label(",");
		lblWarning = new Label(
				"**** Work days are Monday-Friday from 8AM to 5PM *****\n\nIf you are not ready at this time please give us a call at 843-383-7050");

		// label Font
		lblAsk.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		lblWarning.setFont(Font.font("Verdana", 9));
		lblWarning.setTextFill(Color.web("#ff0000"));

		// Drop down List INCLUDING A HBOX...THIS PUTS THEM ON THE SAME LINE.
		// AM or PM
		AMorPM = new ChoiceBox<>();
		AMorPM.getItems().addAll("A.M.", "P.M.");

		// Hours and Minutes
		day = new ChoiceBox<>();
		hours = new ChoiceBox<>();
		minutes = new ChoiceBox<>();
		hours.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
		day.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
		int by5 = 0;

		for (int i = 1; i <= 12; i++) {
			if (by5 == 0 || by5 == 5) {
				minutes.getItems().add("0" + by5);
				by5 = by5 + 5;
			} else {
				minutes.getItems().add(by5 + "");
				by5 = by5 + 5;
			}
		}

		// SEt hboxes for the time slots and submit button
		HBox btnsTimeChoice = new HBox(3);
		btnsTimeChoice.setMargin(lblColon, new Insets(0, 0, 3, 0));
		btnsTimeChoice.getChildren().addAll(day, lblComma, hours, lblColon, minutes, AMorPM);

		HBox btnsSubmitCancel = new HBox(8);
		btnsSubmitCancel.getChildren().addAll(btnSubmit, btnCancel);

		// Place on Grid

		GridPane.setConstraints(btnsTimeChoice, 0, 1);
		GridPane.setConstraints(btnsSubmitCancel, 0, 2);
		GridPane.setConstraints(lblWarning, 0, 3);

		// Buttons
		btnSubmit.setOnAction(this);

		btnCancel.setOnAction(this);

		// put on grid
		grid.getChildren().addAll(lblAsk, btnsTimeChoice, btnsSubmitCancel, lblWarning);
		Scene scene = new Scene(grid, 350, 200);

		window.setScene(scene);
		window.show();

	}

	public static void SendEmail() throws UnknownHostException {

		String Username = System.getProperty("user.name");
		InetAddress localMachine = InetAddress.getLocalHost();
		String PCname = localMachine.getHostName();
		String to = "copssupportws.hartsville@sonoco.com";
		// String to = "mark.gainey@sonoco.com";
		String from = "NoReplyDT@sonoco.com";
		String host = "localhost";
		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", "10.77.48.132");
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage message = new MimeMessage(session);

			// Set from: header field of the header
			message.setFrom(new InternetAddress(from));
			// set To: header of the head field
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set subject: header filed
			message.setSubject("Signed on user: " + Username + " " + day.getValue() + ", " + hours.getValue() + ":"
					+ minutes.getValue() + " " + AMorPM.getValue() + " PC Name: " + localMachine
					+ " (Data Transfer Notification)");

			// now set the actual message
			message.setText("Schedule Data Transfer:\nTime: " + day.getValue() + ", " + hours.getValue() + ":"
					+ minutes.getValue() + " " + AMorPM.getValue() + "\nSigned On User: " + Username + "\nPC Name: "
					+ localMachine);

			// send message
			Transport.send(message);
		} catch (MessagingException mex) {
			System.out.println(mex);
		}
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == btnSubmit) {
			try {
				SendEmail();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.consume();
			window.close();

		}
		if (event.getSource() == btnCancel) {
			event.consume();
			window.close();
		}

	}

}
