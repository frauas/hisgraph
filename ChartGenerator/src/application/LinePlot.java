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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LinePlot extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Fitness Comparision graph");
//		final NumberAxis xAxis = new NumberAxis(0.0,100.0,1);
//		final NumberAxis yAxis = new NumberAxis(5,15.22,0.1);
		final NumberAxis xAxis = new NumberAxis(0,50,1);
		final NumberAxis yAxis = new NumberAxis(5.86,6.38,0.01);
		final LineChart<Number,Number> linechart = new LineChart<>(xAxis,yAxis);
		xAxis.setLabel("Generations");
		yAxis.setLabel("Fitness");
		linechart.setTitle("Fitness Chart | Scenario 1");

		XYChart.Series series = new XYChart.Series();
		XYChart.Series series1 = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		series.setName("Base Code  (Turbines: 595/1029) ");
		series1.setName("Edge Selection (Turbines: 597/1029) ");
		series2.setName("Hybrid (Turbines: 869/1029) ");
		
		String path = "D:/HIS/SEM3/HIS_Project/";
		
		String csvFile = path + "output1/robin_code_xml/1/Fri_Jan_20_09-25-02_IST_2017/fitness.txt";
		String csvFile1 = path + "output1/bala_old_xml/output/with_edge/1/Thu_Jan_19_01-12-56_CET_2017/fitness.txt";
		String csvFile2 = path + "server_data/local/1/Mon_Feb_06_02-02-40_IST_2017/fitness.txt";
//		D:\HIS\SEM3\HIS_Project\output1\robin_code_xml\0\Fri_Jan_20_01-38-59_IST_2017
//		D:\HIS\SEM3\HIS_Project\output1\bala_old_xml\output\with_edge\0\Wed_Jan_18_11-57-04_CET_2017
//		D:\HIS\SEM3\HIS_Project\server_data\local\0\Sun_Feb_05_22-20-52_IST_2017
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			int iteration = 0;
			while ((line = br.readLine()) != null) {
				iteration++;
				// use comma as separator
				double x = iteration;
				double y = Double.parseDouble(line);
				series.getData().add(new XYChart.Data(x,y));

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
		//..................................................................................
		BufferedReader br1 = null;
		String line1 = "";
		String cvsSplitBy1 = ",";
		try {

			br1 = new BufferedReader(new FileReader(csvFile1));
			int iteration = 0;
			while ((line1 = br1.readLine()) != null) {
				iteration++;
				// use comma as separator
				double p = iteration;
				double q = Double.parseDouble(line1);
				series1.getData().add(new XYChart.Data(p,q));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br1 != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		BufferedReader br2 = null;
		String line2 = "";
		String cvsSplitBy2 = ",";
		try {

			br2 = new BufferedReader(new FileReader(csvFile2));
			int iteration = 0;
			while ((line2 = br2.readLine()) != null) {
				iteration++;
				// use comma as separator
				double r = iteration;
				double s = Double.parseDouble(line2);
				series2.getData().add(new XYChart.Data(r,s));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br2 != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		 //Setting the data to Line chart    
	      linechart.getData().addAll(series,series1,series2);        
	        
	         
	      //Creating a scene object 
	      Scene scene = new Scene(linechart,600, 300);  
	      //Adding scene to the stage 
	      stage.setScene(scene);
		   
	      //Displaying the contents of the stage 
	      stage.show();         
	}

	public static void main(String[] args) {
		launch(args);
	}
}