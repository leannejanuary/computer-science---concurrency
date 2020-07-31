//package cloudscapes;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * A class which stores data about clouds and is used to predict what types
 * of clouds will occur.
 */
public class CloudData {
	/**
 	* In-plane regular grid of wind vectors, that evolves over time.
 	*/
	Vector [][][] advection; 

	/**
	 * Vertical air movement strength, that evolves over time.
	 */
	float [][][] convection;  
	
	/**
	 * Cloud type per grif point, evolving over time.
	 */
	int [][][] classification; 

	/**
	 * Data dimensions
	 */
	int dimx, dimy, dimt; 

	/**
	 *  Calculates the total number of elements in the timeline grids
	 *  @return the total number of elements in the timeline grids
	 */
	int dim(){
		return dimt*dimx*dimy;
	}
	

	/**
	 * Converts linear position into 3D location in simulation grid
	 * @param pos the position
	 * @param ind the integer array
	 */
	void locate(int pos, int [] ind)
	{
		ind[0] = (int) pos / (dimx*dimy); // t
		ind[1] = (pos % (dimx*dimy)) / dimy; // x
		ind[2] = pos % (dimy); // y
	}
	
	/**
	 * Reads cloud simulation data from file
	 * @param fileName the name of the file to be read from
	 */
	void readData(String fileName){ 
		try{ 
			Scanner sc = new Scanner(new File(fileName), "UTF-8");
			
			// input grid dimensions and simulation duration in timesteps
			dimt = sc.nextInt();
			dimx = sc.nextInt(); 
			dimy = sc.nextInt();
			
			// initialize and load advection (wind direction and strength) and convection
			advection = new Vector[dimt][dimx][dimy];
			convection = new float[dimt][dimx][dimy];
			for(int t = 0; t < dimt; t++)
				for(int x = 0; x < dimx; x++)
					for(int y = 0; y < dimy; y++){
						advection[t][x][y] = new Vector();
						advection[t][x][y].x = Float.parseFloat(sc.next());
						advection[t][x][y].y = Float.parseFloat(sc.next());
						convection[t][x][y] = Float.parseFloat(sc.next());
					}
			
			classification = new int[dimt][dimx][dimy];
			sc.close(); 
		} 
		catch (IOException e){ 
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){ 
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}
	}

	/**
	 * Based on cloud data, classifies the cloud at the current grid point
	 * into a category
	 */
	void compareAdd(int t, int x, int y, float windMag) {
		// check if cumulus
		if (Math.abs(convection[t][x][y]) > windMag){
                	classification[t][x][y] = 0;
                }
                else {
			// check if amorphous stratus
                	if (windMag < 0.2){
                        	classification[t][x][y]=2;
                       	}
			// check if striated stratus
                        else {
                         	classification[t][x][y]=1;
                      	}
              	}

	}

	/**
	 * Calculates local average wind direction for a specific element in 
	 * the grid.
	 * @param tPos
	 * @param xPos
	 * @param yPos
	 */
	float localAverage(int tPos, int xPos, int yPos){
		float sumY = 0;
		float sumX = 0;
		int numNeighbours = 0;

		for (int i=Math.max(0, xPos -1); i<Math.min(dimx, xPos+2); i++ ){
			for (int j = Math.max(0, yPos-1); j<Math.min(dimy, yPos+2); j++){
				sumX += advection[tPos][i][j].x;
			     	sumY += advection[tPos][i][j].y;	
				numNeighbours++;
			}
		}

		float average = (float) Math.sqrt(Math.pow((sumY/numNeighbours),2)+Math.pow((sumX/numNeighbours),2));
		return average;
	}


	/**
	 *  Writes classification output to file
	 *  @param fileName the name of the file to be written to
	 *  @param wind the vector depicting the average wind direction
	 */
	void writeData(String fileName, Vector wind){
		 try{
			 FileWriter fileWriter = new FileWriter(fileName);
			 PrintWriter printWriter = new PrintWriter(fileWriter);
			 printWriter.printf("%d %d %d\n", dimt, dimx, dimy);
			 printWriter.printf("%f %f\n", wind.x, wind.y);
			 
			 for(int t = 0; t < dimt; t++){
				 for(int x = 0; x < dimx; x++){
					for(int y = 0; y < dimy; y++){
						printWriter.printf("%d ", classification[t][x][y]);
					}
				 }
				 printWriter.printf("\n");
		     }
				 
			 printWriter.close();
		 }
		 catch (IOException e){
			 System.out.println("Unable to open output file "+fileName);
				e.printStackTrace();
		 }
	}
}
