package com.object.mentor.cleancode.chapter2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CodeAnalyzer implements JavaFileAnalysis{
	private int lineCount;
	private int maxLineWidth;
	private int widestLineNumber;
	private LineWidthHistogram lineWidthHistogram;
	private int totalChars;
	
	public CodeAnalyzer() {
		lineWidthHistogram = new LineWidthHistogram();
	}
	
	public static List<File> findJavaFiles(File parentDirectory){
		List<File> files = new ArrayList<File>();
		findJavaFiles(parentDirectory, files);
		return files;
	}

	private static void findJavaFiles(File parentDirectory, List<File> files) {
		for (File file : parentDirectory.listFiles()) {
			if(file.getName().endsWith(".java"))
				files.add(file);
			else if(file.isDirectory())
				findJavaFiles(file, files);
		}
	}
	
	public void analyzeFile(File javaFile) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(javaFile));
		String line;
		while((line = br.readLine()) != null)
			measureLine(line);
	}

	private void measureLine(String line) {
		lineCount++;
		int lineSize = line.length();
		totalChars += lineSize;
		lineWidthHistogram.addLine(lineSize, lineCount);
		recordWidestLine(lineSize);
	}

	private void recordWidestLine(int lineSize) {
		if(lineSize > maxLineWidth) {
			maxLineWidth = lineSize;
			widestLineNumber = lineCount;
		}
	}

	public int getLineCount() {
		return lineCount;
	}

	public int getMaxLineWidth() {
		return maxLineWidth;
	}

	public int getWidestLineNumber() {
		return widestLineNumber;
	}
	
	public double getMeanLineWidth() {
		return (double) totalChars / lineCount;
	}
	
	public int getMedianLineWidth() {
		Integer[] sortedWidth = getSortedWidths();
		int cumulativeLineCount = 0;
		for (Integer width : sortedWidth) {
			cumulativeLineCount += lineCountForWidth(width);
			if(cumulativeLineCount > lineCount / 2)
				return width;
		}
		throw new Error("Program cann't get here!");
	}

	private int lineCountForWidth(Integer width) {
		return lineWidthHistogram.getLinesForWidth(width).size();
	}

	private Integer[] getSortedWidths() {
		Set<Integer> widths = lineWidthHistogram.getWidths();
		Integer[] sortedWidths =(widths.toArray(new Integer[0]));
		Arrays.sort(sortedWidths);
		return sortedWidths;
	}
}
