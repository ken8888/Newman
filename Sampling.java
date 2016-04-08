import java.util.*;
import java.math.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.*;

public class Sampling {
	double [] countArray;
	int size;
	Random rand;
	static int numSelected = 0;
	
	public Sampling(int fileSize){
		this.size = fileSize;
		this.countArray = new double[fileSize];
		for(int i = 0; i < fileSize; i++){
			this.countArray[i] = 0;
		}
		this.rand = new Random(4);
	}

	public void randomSelect() {
		// TODO Auto-generated constructor stub:
	
		int Nr = size;
		int Nn = (int)(0.1 * Nr);
		double nextProb = 0.1;
		double nextPoint;

		int i = 0;
		while(i < size){

			nextPoint = rand.nextDouble();
			if(nextPoint < nextProb){
				countArray[i]++;
				Nr--;
				Nn--;
				nextProb = (double)Nn / Nr;
				numSelected++;
			}
			else{
				Nr--;
				nextProb = (double)Nn / Nr;
			}

			System.out.println("Index:" + i + ", Next probability:" + nextProb + ", Count:" + countArray[i]);
			i++;

		}
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		
		//File input = new File(args[0]);
		
		//Scanner fileScanner = new Scanner(input);
		int lineNum = 0;
		while(reader.readLine() != null){
			System.out.println(lineNum);
			lineNum++;
		}

		Sampling sampling = new Sampling(lineNum);
		sampling.randomSelect();
		System.out.println("Total number selected: " + numSelected);
		reader.close();
    DescriptiveStatistics stats = new DescriptiveStatistics(sampling.countArray);
    System.out.println("Mean: " + stats.getGeometricMean());
	}


}
