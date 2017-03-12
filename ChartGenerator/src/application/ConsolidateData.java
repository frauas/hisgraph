package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.scene.chart.XYChart;

public class ConsolidateData {

	private Map<Integer, List<Double>> consolidatedMap = new HashMap<>();
	private int allDirLength = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String path = "D:/HIS/SEM3/HIS_Project/robin/0/";
		ConsolidateData cd = new ConsolidateData();

		cd.consolidateOutputs(path);

	}

	private void consolidateOutputs(String path) {

		File fDir = new File(path + "/consolidated");
		fDir.deleteOnExit();
		
		File parseDir = new File(path);

		String[] allDirs = parseDir.list();
		allDirLength = allDirs.length;

		for (int i = 0; i < allDirs.length; i++) {
			File o = new File(path + "/" + allDirs[i] + "/output.txt");
			if(!o.getPath().contains("consolidated"))
				this.readFile(o);
		}
		

		fDir.mkdir();

		File minFit = new File(path + "/consolidated/minFit.txt");
		File maxFit = new File(path + "/consolidated/maxFit.txt");
		File medFit = new File(path + "/consolidated/medianFit.txt");

		try {
			minFit.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		Writer minFitWriter = null;
		Writer maxFitWriter = null;
		Writer medianFitWriter = null;
		try {
			minFitWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(minFit), "utf-8"));
			maxFitWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(maxFit), "utf-8"));
			medianFitWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(medFit), "utf-8"));

			for (int j = 0; j < consolidatedMap.size(); j++) {
				// System.out.println(j + " => " + d/allDirLength);
				List<Double> fitnessData = consolidatedMap.get(j);
				double max = fitnessData.get(fitnessData.size()-1)/allDirLength;
				double min = fitnessData.get(0)/allDirLength;
				double median = (fitnessData.get(9) + fitnessData.get(10)) /(2*allDirLength);
				
				minFitWriter.write(Double.toString(min));
				maxFitWriter.write(Double.toString(max));
				medianFitWriter.write(Double.toString(median));
				
				minFitWriter.write("\n");
				maxFitWriter.write("\n");
				medianFitWriter.write("\n");
				
			}

		} catch (IOException ex) {
		} finally {
			try {
				minFitWriter.close();
				maxFitWriter.close();
				medianFitWriter.close();
			} catch (Exception ex) {
				/* ignore */}
		}

	}

	private void readFile(File readFile) {
		BufferedReader br = null;
		String line = "";
		try {

			br = new BufferedReader(new FileReader(readFile));
			int start = 0;

			List<Double> popFit = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (line.contains("FIT =>")) {
					int index = line.indexOf("=>");
					String data = line.substring(index + 3, line.length() - 2);
					// System.out.println(start + " = " + data);
					popFit.add(Double.parseDouble(data));
				} else if (line.contains("End")) {
					Collections.sort(popFit);
					// Add up all data
					List<Double> cData = new ArrayList<>();
					if (consolidatedMap.containsKey(start)) {
						List<Double> existing = consolidatedMap.get(start);
						for (int i = 0; i < existing.size(); i++) {
							cData.add(existing.get(i) + popFit.get(i));
							;
						}
					} else {
						cData = popFit;
					}
					consolidatedMap.put(start, cData);
					popFit = new ArrayList<>();
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
	}
}
