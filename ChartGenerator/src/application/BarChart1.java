package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarChart1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BarChart Experiments");


        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Scenarios");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Turbines");

        BarChart     barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Base Code");

        dataSeries1.getData().add(new XYChart.Data("0", 539));
        dataSeries1.getData().add(new XYChart.Data("1"  , 599));
        dataSeries1.getData().add(new XYChart.Data("2"  , 477));
        dataSeries1.getData().add(new XYChart.Data("3"  , 508));
        dataSeries1.getData().add(new XYChart.Data("4"  , 507));

        barChart.getData().add(dataSeries1);

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Edge Code");

        dataSeries2.getData().add(new XYChart.Data("0", 568));
        dataSeries2.getData().add(new XYChart.Data("1"  , 624));
        dataSeries2.getData().add(new XYChart.Data("2"  , 539));
        dataSeries2.getData().add(new XYChart.Data("3"  , 565));
        dataSeries2.getData().add(new XYChart.Data("4"  , 560));
        barChart.getData().add(dataSeries2);
        
        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("Final Code");

        dataSeries3.getData().add(new XYChart.Data("0", 509));
        dataSeries3.getData().add(new XYChart.Data("1"  , 898));
        dataSeries3.getData().add(new XYChart.Data("2"  , 509));
        dataSeries3.getData().add(new XYChart.Data("3"  , 569));
        dataSeries3.getData().add(new XYChart.Data("4"  , 508));
        barChart.getData().add(dataSeries3);
//        
//        XYChart.Series dataSeries4 = new XYChart.Series();
//        dataSeries4.setName("2015");
//
//        dataSeries4.getData().add(new XYChart.Data("0", 540));
//        dataSeries4.getData().add(new XYChart.Data("1"  , 120));
//        dataSeries4.getData().add(new XYChart.Data("2"  , 36));
//        dataSeries4.getData().add(new XYChart.Data("3"  , 23));
//        dataSeries4.getData().add(new XYChart.Data("4"  , 23));
//        barChart.getData().add(dataSeries4);
//        
//        XYChart.Series dataSeries5 = new XYChart.Series();
//        dataSeries5.setName("2015");
//
//        dataSeries5.getData().add(new XYChart.Data("0", 540));
//        dataSeries5.getData().add(new XYChart.Data("1"  , 120));
//        dataSeries5.getData().add(new XYChart.Data("2"  , 36));
//        dataSeries5.getData().add(new XYChart.Data("3"  , 23));
//        dataSeries5.getData().add(new XYChart.Data("4"  , 23));
//        barChart.getData().add(dataSeries5);


        VBox vbox = new VBox(barChart);

        Scene scene = new Scene(vbox, 200, 200);
        scene.getStylesheets().add(getClass().getResource("Bar.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);

        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
