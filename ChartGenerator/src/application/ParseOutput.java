package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;

public class ParseOutput {

	public static void main(String[] args) {
		
//		ParseOutput.parseData();
	}
	
	
	public static void parseData(XYChart.Series series){

		series = new XYChart.Series();
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
				if(line.contains("FIT =>")){
					int index = line.indexOf("=>");
					String data = line.substring(index +3, line.length()-2);
					System.out.println(start + " = " + data);
					series.getData().add(new XYChart.Data(start,data));
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
	
	}
}
