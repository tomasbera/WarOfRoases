package edu.ntnu.idatt2001.Wargames.battlesimulation.maintnance;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * class used for alerting messages for user quality
 *
 * @author Tomas Beranek
 */
public class PopupBoxes {
    private static boolean answer;

    /**
     * method used for displaying errors made by the user in the program
     *
     * @param message messaged displayed based on type of error
     * @website https://gitlab.stud.idi.ntnu.no/G1-06/idatt-1002-2022-1-06/-/blob/final-prod/finalprod/src/main/java/edu/ntnu/idatt1002/g106/handballapp/finalprod/backend/PopupBoxes.java
     * <p>
     * method written by me in a earlier project
     * </p>
     */
    public static void alertError(String message) {
        Alert dateAlert = new Alert(Alert.AlertType.ERROR);
        dateAlert.setTitle("Error");
        dateAlert.setContentText(message);
        dateAlert.showAndWait();
    }

    /**
     * method used for displaying confirmations made by the user in the program
     *
     * @param message information based on action
     * @website https://gitlab.stud.idi.ntnu.no/G1-06/idatt-1002-2022-1-06/-/blob/final-prod/finalprod/src/main/java/edu/ntnu/idatt1002/g106/handballapp/finalprod/backend/PopupBoxes.java
     * <p>
     * method written by me in a earlier project
     * </p>
     */
    public static void confirmBox(String message) {
        Alert dataAlert = new Alert(Alert.AlertType.CONFIRMATION);
        dataAlert.setContentText("Confirm");
        dataAlert.setContentText(message);
        dataAlert.showAndWait();
    }

    /**
     * yes no box made for confirmation of needed methods where yes and no is a possibility
     *
     * @param title   title of the box
     * @param message messaged to be displayed
     * @return boolean based on user reaction
     * @website https://gitlab.stud.idi.ntnu.no/G1-06/idatt-1002-2022-1-06/-/blob/final-prod/finalprod/src/main/java/edu/ntnu/idatt1002/g106/handballapp/finalprod/backend/PopupBoxes.java
     * <p>
     * method created by me in a earlier project, but it's inspired from:
     * @website https://www.youtube.com/watch?v=HFAsMWkiLvg&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=6&ab_channel=thenewboston
     * </p>
     */
    public static boolean yesNoBox(String title, String message) {
        //creation of box and declaration of Modality type
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        //creation of two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //how the box is going to look
        Label label = new Label();
        label.setText(message);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);

        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}

