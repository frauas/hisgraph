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

public class TurbineLinePlot extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Turbine Evaluation");
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setTickUnit(1);
		final NumberAxis yAxis = new NumberAxis(500,600,10);
//		yAxis.setLowerBound(450);
//		yAxis.setTickUnit(10);
//		final ScatterChart sc = new ScatterChart(xAxis, yAxis);
		final LineChart<Number,Number> sc = new LineChart<>(xAxis,yAxis);
		xAxis.setLabel("Generations");
		yAxis.setLabel("Turbines");
		sc.setTitle("Turbine Count | Scenario 0");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Base code Turbine");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Edge Selection");
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Final");
		
		
		
	    sc.setAnimated(false);
	    series1 = TurbineLinePlot.parseData();
	    series2 = TurbineLinePlot.parseData();
	    series3 = TurbineLinePlot.parseData();
	    
		sc.getData().addAll(series1,series2,series3);
		Scene scene = new Scene(sc, 500, 400);
		stage.setScene(scene);
		stage.show();
	}

	public static XYChart.Series parseData(){

		XYChart.Series series = new XYChart.Series();
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
		
		BufferedReader br = null;
		String line = "";
		try {

			br = new BufferedReader(new FileReader(file));
			int start = 1;
			
			while ((line = br.readLine()) != null) {
				if(line.contains("Turbines =>  ")){
					int index = line.indexOf("Turbines =>  ");
					int endindex = line.indexOf(" |");
					String data = line.substring(index +12, endindex);
					System.out.println(start + " = " + data);
					series.getData().add(new XYChart.Data(start,Double.parseDouble(data)));
				}else if(line.contains("End")){
					start++;
				}
				// use comma as separator
				//String[] data = line.split(cvsSplitBy);
				//double y = Double.parseDouble(data[1]);

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
