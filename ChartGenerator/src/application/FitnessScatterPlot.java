package application;
//	

//import javafx.application.Application;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//
//
//public class Main extends Application {
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class FitnessScatterPlot extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Fitness Overview");
		final NumberAxis xAxis = new NumberAxis(0, 52, 1);
		final NumberAxis yAxis = new NumberAxis(5.8,6.5,0.01);
		final LineChart<Number, Number> sc = new LineChart<>(xAxis, yAxis);

		xAxis.setLabel("Generations");
		yAxis.setLabel("Fitness");
		sc.setTitle("Fitness Comparision (Final Code)  | Scenario 1 | ");

		XYChart.Series series1 = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series3 = new XYChart.Series();
		XYChart.Series series4 = new XYChart.Series();
		series1.setName("Final Code");


		sc.setAnimated(false);
		series1 = FitnessScatterPlot.parseData();

		sc.getData().addAll(series1);
		Scene scene = new Scene(sc, 500, 400);
		scene.getStylesheets().add(getClass().getResource("fitness.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public static XYChart.Series parseData() {

		XYChart.Series series = new XYChart.Series();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("D:/HIS/SEM3/HIS_Project/"));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(true);
		
		File file1 = null;
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file1 = chooser.getSelectedFile();
		} else {
			System.out.println("No Selection ");
		}

		BufferedReader br = null;
		String line = "";
		try {

			br = new BufferedReader(new FileReader(file1));
			int start = 1;

			while ((line = br.readLine()) != null) {
				if (line.contains("FIT =>")) {
					int index = line.indexOf("=>");
					String data = line.substring(index + 3, line.length() - 2);
					System.out.println(start + " = " + data);
					series.getData().add(new XYChart.Data(start, Double.parseDouble(data)));
				} else if (line.contains("End")) {
					start++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return series;

	}

	public static void main(String[] args) {
		launch(args);
	}
}
