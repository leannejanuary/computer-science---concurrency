import java.util.concurrent.RecursiveAction;

public class SumArr2 extends RecursiveAction {
        int lo;
        int hi;
        int[] ind;
        CloudData cloudData;

        static int SEQUENTIAL_CUTOFF;

	/**
	 * A constructor for the SumArr2 class
	 * @param int l lower bound
	 * @param int h higher bound
	 * @param cloudData the data handler
	 * @param SQ the sequential cutoff
	 */
        SumArr2(int l, int h, CloudData cloudData, int SQ) {
                this.lo=l;
                this.hi=h;
                this.cloudData = cloudData;
                this.ind = new int[3];
		this.SEQUENTIAL_CUTOFF = SQ;
        }

	/**
	 * The compute method parallelises the process of 
	 * classifying cloud data.
	 */
	public void compute() {
		float windMag;
                if((hi-lo) < SEQUENTIAL_CUTOFF) {
                        for (int i=lo;i<hi;i++){
                                cloudData.locate(i,ind);
                                windMag = cloudData.localAverage(ind[0], ind[1], ind[2]);
                               
			       	cloudData.compareAdd (ind[0], ind[1], ind[2], windMag);

			} 
                        
                }

                else {
                        SumArr2 left = new SumArr2(lo,(hi+lo)/2,cloudData,SEQUENTIAL_CUTOFF);
                        SumArr2 right = new SumArr2((hi+lo)/2,hi,cloudData,SEQUENTIAL_CUTOFF);

                        left.fork();
                        right.compute();
                        left.join();
                        
                }
        }
}
