//package cloudscapes;
import java.util.Locale;
import java.util.concurrent.ForkJoinPool;
/**
 * The Main program processes the data on clouds and implements the Java 
 * ForkJoin framework in order to speedup the processing of large amounts
 * of data.
 *
 * @author Leanne January
 */

public class Main {
	static long startTime = 0;
	static final ForkJoinPool fjPool = new ForkJoinPool();
	static int numElements;
	static int SEQUENTIAL_CUTOFF;
	static CloudData cloudData;
	static Vector averageWindVector = new Vector();
	static Vector sumArr(CloudData cloudData) {
		return fjPool.invoke(new SumArr(0,numElements,cloudData,SEQUENTIAL_CUTOFF));
	}

	static final ForkJoinPool fjPool2 = new ForkJoinPool();
	static void sumArr2(CloudData cloudData) {
		fjPool2.invoke(new SumArr2(0,numElements,cloudData,SEQUENTIAL_CUTOFF));
	}

	/**
	 * This is the main method which processes the data.
	 * @param args stores the input file, output file and sequential
	 * cutoff
	 */
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		cloudData = new CloudData();
		String dataFile = args[0];
		String outputFile = args[1];
		SEQUENTIAL_CUTOFF = Integer.parseInt(args[2]);

		//read data fromfile
		cloudData.readData(dataFile);

		numElements = cloudData.dim();
		
		float sum = 0;
		// activate Java warm up
		for (int i=0;i<10;i++) {
			tick();
			Vector sumWind = sumArr(cloudData);
			averageWindVector.x = sumWind.x / numElements;
			averageWindVector.y = sumWind.y / numElements;
			sumArr2(cloudData);
			float testSpeed = tock();
			if (i>1){
				sum += testSpeed;
			}
		}
		//Testing
		System.out.println("Average Speed of: " + sum/8);

		// output to file
		cloudData.writeData(outputFile, averageWindVector);
	}
	/**
	 * The tick method is used in testing how long the parallel component
	 * of the program runs for by recording the time before the
	 * parallel component executes.
	 */
	private static void tick() { startTime = System.currentTimeMillis(); }
	/**
	 * The tock method us used in testing how long the parallel component
	 * of the program runs for by calculating the time in miliseconds it
	 * took for the parallel component to execute.
	 */
	private static float tock() { return (System.currentTimeMillis() - startTime);}
	
}
