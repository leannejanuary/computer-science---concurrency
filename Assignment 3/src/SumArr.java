import java.util.concurrent.RecursiveTask;

public class SumArr extends RecursiveTask<Vector> {
	int lo;
	int hi;
	int[] ind;
	CloudData cloudData;

	static int SEQUENTIAL_CUTOFF;
	
	/**
	 * Constructor for the SumArr class
	 * @param l lower bound
	 * @param h higher bound
	 * @param cloudData the data handler
	 * @param SQ the sequential cutoff
	 */
	SumArr(int l, int h, CloudData cloudData, int SQ) {
		this.lo=l;
		this.hi=h;
		this.cloudData = cloudData;
		this.ind = new int[3];
		this.SEQUENTIAL_CUTOFF = SQ;
	}
	
	/**
	 * The compute method parallelises the process of calculating
	 * the prevailing wind. 
	 * @return vector containing the prevailing wind
	 */
	public Vector compute() {
		if((hi-lo) < SEQUENTIAL_CUTOFF) {
			float sumX = 0;
			float sumY = 0;
			for (int i=lo;i<hi;i++){
				cloudData.locate(i,ind);
				sumX = sumX + cloudData.advection[ind[0]][ind[1]][ind[2]].x;
				sumY = sumY + cloudData.advection[ind[0]][ind[1]][ind[2]].y;

			}
			return new Vector(sumX,sumY);	
		}
		
		else {
			SumArr left = new SumArr(lo,(hi+lo)/2,cloudData,SEQUENTIAL_CUTOFF);
			SumArr right = new SumArr((hi+lo)/2,hi,cloudData,SEQUENTIAL_CUTOFF);

			left.fork();
			Vector rightAns = right.compute();
			Vector leftAns = left.join();
			return leftAns.add(rightAns);
		}
	}
}
