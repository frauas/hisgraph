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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/*
 * Server Scenerio :2 height 12320 width 6930
 */
public class ScatterPlot extends Application {

	private static int SCENERIO = 0;

	@Override
	public void start(Stage stage) {
		stage.setTitle("Farm Layout ");
		final NumberAxis xAxis = new NumberAxis(0,11,4);
		final NumberAxis yAxis = new NumberAxis(-1,40,4);
		// final sc = new ScatterChart(xAxis, yAxis);
		final LineChart<Number, Number> sc = new LineChart<>(xAxis, yAxis);
		xAxis.setLabel("Width");
		yAxis.setLabel("Height");
//		sc.setTitle("Farm Layout 4 | Turbines 862| Fitness 6.75568 | Xaxis 9.2009xR | Yaxis 8.001xR ");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Grid Point");

		File csvFile = ScatterPlot.getFile();

		if (csvFile.getAbsolutePath().contains("\\0\\")) {
			SCENERIO = 0;
		} else if (csvFile.getAbsolutePath().contains("\\1\\")) {
			SCENERIO = 1;
		} else if (csvFile.getAbsolutePath().contains("\\2\\")) {
			SCENERIO = 2;
		} else if (csvFile.getAbsolutePath().contains("\\3\\")) {
			SCENERIO = 3;
		} else {
			SCENERIO = 4;
		}

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] data = line.split(cvsSplitBy);
				double x = Double.parseDouble(data[0]);
				double y = Double.parseDouble(data[1]);
				series1.getData().add(new XYChart.Data(x, y));

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

		sc.setAnimated(false);
		sc.getData().add(series1);

//	updateObs(sc);
	updateServerObs(sc);

		Scene scene = new Scene(sc, 500, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public void updateObs(LineChart<Number, Number> sc) {
		// XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
//		series2.setName("Obstacle");
		series2.getData().add(new XYChart.Data(3000, 4000));
		series2.getData().add(new XYChart.Data(3000, 6500));
		series2.getData().add(new XYChart.Data(4000, 6500));
		series2.getData().add(new XYChart.Data(4000, 4000));
		// <obstacle xmin="6500" ymin="13500" xmax="7000" ymax="14000"/>
		XYChart.Series series3 = new XYChart.Series();
		series3.getData().add(new XYChart.Data(6500, 13500));
		series3.getData().add(new XYChart.Data(6500, 14000));
		series3.getData().add(new XYChart.Data(7000, 14000));
		series3.getData().add(new XYChart.Data(7000, 13500));

		XYChart.Series series4 = new XYChart.Series();
		series4.getData().add(new XYChart.Data(3000, 4000));
		series4.getData().add(new XYChart.Data(4000, 4000));
		XYChart.Series series5 = new XYChart.Series();
		series5.getData().add(new XYChart.Data(6500, 13500));
		series5.getData().add(new XYChart.Data(7000, 13500));

		sc.getData().addAll(series2, series3, series4, series5);
	}

	public void updateServerObs(LineChart<Number, Number> sc) {

		SCENERIO =4;
		if (SCENERIO == 0) {
			// XYChart.Series series2 = new XYChart.Series();
			XYChart.Series series2 = new XYChart.Series();
//			series2.setName("Obstacle");
			series2.getData().add(new XYChart.Data(1155, 3272));
			series2.getData().add(new XYChart.Data(1155, 4363));
			series2.getData().add(new XYChart.Data(2310, 4363));
			series2.getData().add(new XYChart.Data(2310, 3272));
			// <obstacle xmin="6500" ymin="13500" xmax="7000" ymax="14000"/>
			XYChart.Series series3 = new XYChart.Series();
			series3.getData().add(new XYChart.Data(2310, 0));
			series3.getData().add(new XYChart.Data(2310, 1090));
			series3.getData().add(new XYChart.Data(3465, 1090));
			series3.getData().add(new XYChart.Data(3465, 0));

			XYChart.Series series4 = new XYChart.Series();
			series4.getData().add(new XYChart.Data(2310, 1090));
			series4.getData().add(new XYChart.Data(2310, 2181));
			series4.getData().add(new XYChart.Data(3465, 2181));
			series4.getData().add(new XYChart.Data(3465, 190));

			XYChart.Series series5 = new XYChart.Series();
			series5.getData().add(new XYChart.Data(3465, 2181));
			series5.getData().add(new XYChart.Data(3465, 3272));
			series5.getData().add(new XYChart.Data(4620, 3272));
			series5.getData().add(new XYChart.Data(4620, 2181));

			XYChart.Series series6 = new XYChart.Series();
			series6.getData().add(new XYChart.Data(1155, 3272));
			series6.getData().add(new XYChart.Data(2310, 3272));
//
//			XYChart.Series series7 = new XYChart.Series();
//			series7.getData().add(new XYChart.Data(2310, 0));
//			series7.getData().add(new XYChart.Data(3465, 0));
//			XYChart.Series series8 = new XYChart.Series();
//			series8.getData().add(new XYChart.Data(2310, 1090));
//			series8.getData().add(new XYChart.Data(3465, 1090));
			XYChart.Series series9 = new XYChart.Series();
			series9.getData().add(new XYChart.Data(3465, 2181));
			series9.getData().add(new XYChart.Data(4620, 2181));

			sc.getData().addAll(series2, series3, series4, series5, series6, series9);
		} else if (SCENERIO == 1) {
			XYChart.Series series2 = new XYChart.Series();
//			series2.setName("Obstacle");
			series2.getData().add(new XYChart.Data(2181, 4004));
			series2.getData().add(new XYChart.Data(2181, 5005));
			series2.getData().add(new XYChart.Data(3272, 5005));
			series2.getData().add(new XYChart.Data(3272, 4004));

			XYChart.Series series6 = new XYChart.Series();
			series6.getData().add(new XYChart.Data(2181, 4004));
			series6.getData().add(new XYChart.Data(3272, 4004));
			sc.getData().addAll(series2, series6);
		} else if (SCENERIO == 2) {
			XYChart.Series series2 = new XYChart.Series();
//			series2.setName("Obstacle");
			series2.getData().add(new XYChart.Data(3960, 7392));
			series2.getData().add(new XYChart.Data(3960, 9856));
			series2.getData().add(new XYChart.Data(4950, 9856));
			series2.getData().add(new XYChart.Data(4950, 7392));
			// <obstacle xmin="6500" ymin="13500" xmax="7000" ymax="14000"/>
			XYChart.Series series3 = new XYChart.Series();
			series3.getData().add(new XYChart.Data(0, 0));
			series3.getData().add(new XYChart.Data(0, 2464));
			series3.getData().add(new XYChart.Data(990, 2464));
			series3.getData().add(new XYChart.Data(990, 0));

			XYChart.Series series4 = new XYChart.Series();
			series4.getData().add(new XYChart.Data(2970, 9856));
			series4.getData().add(new XYChart.Data(2970, 12320));
			series4.getData().add(new XYChart.Data(3960, 12320));
			series4.getData().add(new XYChart.Data(3960, 9856));

			XYChart.Series series5 = new XYChart.Series();
			series5.getData().add(new XYChart.Data(0, 0));
			series5.getData().add(new XYChart.Data(990, 0));
			XYChart.Series series6 = new XYChart.Series();
			series6.getData().add(new XYChart.Data(2970, 9856));
			series6.getData().add(new XYChart.Data(3960, 9856));
			XYChart.Series series7 = new XYChart.Series();
			series7.getData().add(new XYChart.Data(3960, 7392));
			series7.getData().add(new XYChart.Data(4959, 7392));
			sc.getData().addAll(series2, series3, series4, series5, series6, series7);
		} else if (SCENERIO == 3) {
			XYChart.Series series2 = new XYChart.Series();
//			series2.setName("Obstacle");
			series2.getData().add(new XYChart.Data(1347, 2053));
			series2.getData().add(new XYChart.Data(1347, 3080));
			series2.getData().add(new XYChart.Data(4042, 3080));
			series2.getData().add(new XYChart.Data(4042, 2053));
			// <obstacle xmin="6500" ymin="13500" xmax="7000" ymax="14000"/>
			XYChart.Series series3 = new XYChart.Series();
			series3.getData().add(new XYChart.Data(1347, 6160));
			series3.getData().add(new XYChart.Data(1347, 7186));
			series3.getData().add(new XYChart.Data(4042, 7186));
			series3.getData().add(new XYChart.Data(4042, 6160));

			XYChart.Series series4 = new XYChart.Series();
			series4.getData().add(new XYChart.Data(6737, 3080));
			series4.getData().add(new XYChart.Data(6737, 4106));
			series4.getData().add(new XYChart.Data(8085, 4106));
			series4.getData().add(new XYChart.Data(8085, 3080));

			XYChart.Series series5 = new XYChart.Series();
			series5.getData().add(new XYChart.Data(1347, 2053));
			series5.getData().add(new XYChart.Data(4042, 2053));
			XYChart.Series series6 = new XYChart.Series();
			series6.getData().add(new XYChart.Data(1347, 6160));
			series6.getData().add(new XYChart.Data(4042, 6160));
			XYChart.Series series7 = new XYChart.Series();
			series7.getData().add(new XYChart.Data(6737, 3080));
			series7.getData().add(new XYChart.Data(8085, 3080));
			sc.getData().addAll(series2, series3, series4, series5, series6, series7);
		} else if (SCENERIO == 4) {
			XYChart.Series series2 = new XYChart.Series();
//			series2.setName("Obstacle");
			series2.getData().add(new XYChart.Data(3368, 0));
			series2.getData().add(new XYChart.Data(3368, 727));
			series2.getData().add(new XYChart.Data(4042, 727));
			series2.getData().add(new XYChart.Data(4042, 0));
			XYChart.Series series3 = new XYChart.Series();
			series3.getData().add(new XYChart.Data(3368, 0));
			series3.getData().add(new XYChart.Data(4042, 0));
			sc.getData().addAll(series2, series3);
		}

	}

	public static File getFile() {

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("D:/HIS/SEM3/HIS_Project/"));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(true);

		File file = null;
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		} else {
			System.out.println("No Selection ");
		}
		return file;
	}

	public static void main(String[] args) {
		launch(args);
	}
}