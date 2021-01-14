/*
 * Kavin Zhu
 * 20167040
 * Calculates your course average and provides further grade details.
 */

package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SampleCalculator.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Grade Calculator");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // calculate grade details
    @FXML Button btnCalculate;
    // 5 assignments (assignment points)
    @FXML TextField txtA1Points;
    @FXML TextField txtA2Points;
    @FXML TextField txtA3Points;
    @FXML TextField txtA4Points;
    @FXML TextField txtA5Points;
    // (total points for assignments)
    @FXML TextField txtA1TotalPoints;
    @FXML TextField txtA2TotalPoints;
    @FXML TextField txtA3TotalPoints;
    @FXML TextField txtA4TotalPoints;
    @FXML TextField txtA5TotalPoints;
    // (assignment weights)
    @FXML TextField txtA1Weights;
    @FXML TextField txtA2Weights;
    @FXML TextField txtA3Weights;
    @FXML TextField txtA4Weights;
    @FXML TextField txtA5Weights;
    // grade details
    @FXML Label lblAverage;

    // average printed out onto lblAverage & variables used to calculate average
    double average;
    double averageOf100 = 0;
    double currentGradeWeightings = 0;
    double mark;


    // handles the function of the calculations - checks for errors and presents grade details
    //When the calculate button is clicked...
    @FXML protected void handleCalculations(ActionEvent event) throws BadCalculation {

    	// text field inputs
        double points1;
        double totalPoints1;
        double weight1;
        double points2;
        double totalPoints2;
        double weight2;
        double points3;
        double totalPoints3;
        double weight3;
        double points4;
        double totalPoints4;
        double weight4;
        double points5;
        double totalPoints5;
        double weight5;

        // array of Strings from text fields for assignment points
        ArrayList < Double > assignmentPoints = new ArrayList < Double > ();
        // array of Strings from text fields for total assignment points
        ArrayList < Double > assignmentTotalPoints = new ArrayList < Double > ();
        // array of Strings for assignment weighting
        ArrayList < Double > assignmentWeighting = new ArrayList < Double > ();
        
        // if any of the 3 fields in each assignment is not null, add each text to their respective array
        if (txtA1Points.getText().isEmpty() == false && txtA1TotalPoints.getText().isEmpty() == false && txtA1Weights.getText().isEmpty() == false) {
            // try catch block for any non-numeric values
            try {
                points1 = Double.parseDouble(txtA1Points.getText());
                totalPoints1 = Double.parseDouble(txtA1TotalPoints.getText());
                weight1 = Double.parseDouble(txtA1Weights.getText());
                // ensures that the points are less than the total points
                if (points1 > totalPoints1) {
                    throw new BadCalculation("You cannot have over 100% on an assignment.");
                }
            } catch (NumberFormatException e) {
                throw new BadCalculation("Please enter numeric values only.");
            }
            assignmentPoints.add(points1);
            assignmentTotalPoints.add(totalPoints1);
            assignmentWeighting.add(weight1);
        }

        if (txtA2Points.getText().isEmpty() == false && txtA2TotalPoints.getText().isEmpty() == false && txtA2Weights.getText().isEmpty() == false) {
            try {
                points2 = Double.parseDouble(txtA2Points.getText());
                totalPoints2 = Double.parseDouble(txtA2TotalPoints.getText());
                weight2 = Double.parseDouble(txtA2Weights.getText());
                if (points2 > totalPoints2) {
                    throw new BadCalculation("You cannot have over 100% on an assignment.");
                }
            } catch (NumberFormatException e) {
                throw new BadCalculation("Please enter numeric values only.");
            }
            assignmentPoints.add(points2);
            assignmentTotalPoints.add(totalPoints2);
            assignmentWeighting.add(weight2);
        }

        if (txtA3Points.getText().isEmpty() == false && txtA3TotalPoints.getText().isEmpty() == false && txtA3Weights.getText().isEmpty() == false) {
            try {
                points3 = Double.parseDouble(txtA3Points.getText());
                totalPoints3 = Double.parseDouble(txtA3TotalPoints.getText());
                weight3 = Double.parseDouble(txtA3Weights.getText());
                if (points3 > totalPoints3) {
                    throw new BadCalculation("You cannot have over 100% on an assignment.");
                }
            } catch (NumberFormatException e) {
                throw new BadCalculation("Please enter numeric values only.");
            }
            assignmentPoints.add(points3);
            assignmentTotalPoints.add(totalPoints3);
            assignmentWeighting.add(weight3);
        }

        if (txtA4Points.getText().isEmpty() == false && txtA4TotalPoints.getText().isEmpty() == false && txtA4Weights.getText().isEmpty() == false) {
            try {
                points4 = Double.parseDouble(txtA4Points.getText());
                totalPoints4 = Double.parseDouble(txtA4TotalPoints.getText());
                weight4 = Double.parseDouble(txtA4Weights.getText());
                if (points4 > totalPoints4) {
                    throw new BadCalculation("You cannot have over 100% on an assignment.");
                }
            } catch (NumberFormatException e) {
                throw new BadCalculation("Please enter numeric values only.");
            }
            assignmentPoints.add(points4);
            assignmentTotalPoints.add(totalPoints4);
            assignmentWeighting.add(weight4);
        }

        if (txtA5Points.getText().isEmpty() == false && txtA5TotalPoints.getText().isEmpty() == false && txtA5Weights.getText().isEmpty() == false) {
            try {
                points5 = Double.parseDouble(txtA5Points.getText());
                totalPoints5 = Double.parseDouble(txtA5TotalPoints.getText());
                weight5 = Double.parseDouble(txtA5Weights.getText());
                if (points5 > totalPoints5) {
                    throw new BadCalculation("You cannot have over 100% on an assignment.");
                }
            } catch (NumberFormatException e) {
                throw new BadCalculation("Please enter numeric values only.");
            }
            assignmentPoints.add(points5);
            assignmentTotalPoints.add(totalPoints5);
            assignmentWeighting.add(weight5);
        }

        // for loop through array for assignment weighting to generate currentGradeWeightings
        // also used to generate total average out of 100 so far
        // calculate average by dividing total average out of 100 by currentGradeWeightings
        for (int i = 0; i < assignmentPoints.size(); i++) {
            // turn weightings into decimal form
            currentGradeWeightings += (assignmentWeighting.get(i) / 100);
            // the decimal form of the assignment mark multiplied by the decimal form of the assignment weighting
            averageOf100 += ((assignmentPoints.get(i) / assignmentTotalPoints.get(i)) * (assignmentWeighting.get(i) / 100));
        }

        // average is the averages of every assignment with weightings applied out of total weightings 
        average = averageOf100 / currentGradeWeightings;

        if (average > 1) {
            throw new BadCalculation("You can not have more than 100% in this course.");
        }
        if (currentGradeWeightings > 1) {
            throw new BadCalculation("Total weightings can not be above 100%.");
        }
        if(assignmentPoints.size() < 5 && currentGradeWeightings == 1) {
        	throw new BadCalculation("Total weightings can not be 100% without all the assignments complete.");
        }

        // format to 2 decimal places
        String strAverage = String.format("%.2f", (average * 100));
        lblAverage.setText("Your current average in this course is " + strAverage + "%.");

        System.out.println("Your total grade accounts for " + String.format("%.2f", (currentGradeWeightings * 100)) + "% of your final average.");
        System.out.println("Your current average in this course is " + strAverage + "%.");
        


        // the user will only be notified of marks they need to achieve to boost their average
        // if the currentGradeWeightings is below 1, that means there are still more opportunities to maintain or boost the average
        if (currentGradeWeightings < 1) {
            if (average < 0.90) {
                // average needed for remaining weightings
                // (target final mark out of 100% - current average out of 100%) / remainder grade weightings
                mark = (0.90 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get an A+.");
            }
            if (average < 0.84) {
                mark = (0.84 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get an A.");
            }
            if (average < 0.80) {
                mark = (0.80 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get an A-.");
            }
            if (average < 0.77) {
                mark = (0.77 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a B+.");
            }
            if (average < 0.73) {
                mark = (0.73 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a B.");
            }
            if (average < 0.70) {
                mark = (0.70 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a B-.");
            }
            if (average < 0.67) {
                mark = (0.67 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a C+.");
            }
            if (average < 0.63) {
                mark = (0.63 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a C.");
            }
            if (average < 0.60) {
                mark = (0.60 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a C-.");
            }
            if (average < 0.57) {
                mark = (0.57 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a D+.");
            }
            if (average < 0.53) {
                mark = (0.53 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a D.");
            }
            if (average < 0.50) {
                mark = (0.50 - (average * currentGradeWeightings)) / (1 - currentGradeWeightings);
                System.out.println("You need to obtain: " + String.format("%.2f", (mark * 100)) + "% on the remaining assignments to get a D-.");
            }
        }
    } // end of handleCalculations


    public static void main(String[] args) {
        launch(args);
        
    } // end of main

} // end of class