
package vm;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;


/**
 * Ergasia 2
 *
 * @author Dimitrios Dragatas
 * @AEM 2676
 * @email ddragatas@csd.auth.gr
 */
public class vm {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        int N=0;
        int M=0;

        int[][] processCost = new int[N][M];
        int[][] moveCost = new int[N][M];
        try {
           String fileName=args[0];
           try (Scanner inp=new Scanner(new File(fileName))) {
                N=(inp.nextInt()); /*processes */
                M=(inp.nextInt()); /*types of machines */
               processCost = new int[N][M];/* total cost to run a process in a virtual machine*/
                moveCost = new int[M][M]; /*cost to send data from one machine to another*/
                while (inp.hasNext()) {
                   for (int i=0;i<N;i++) {
                        for (int j=0;j<M;j++) {
                           processCost[i][j] = inp.nextInt();
                        }
                  }
                  for (int i=0;i<M;i++) {
                      for (int j=0;j<M;j++) {
                           moveCost[i][j] = inp.nextInt();
                       }
                   }
               }
            }
       }
      catch (FileNotFoundException ex) {
           System.out.println("Error unable open file");
       }
        int returned [][]= FinalArrayCost(processCost,moveCost,N,M);

        for (int i=0; i<N; i++){
             for (int j=0; j<M; j++){
                  System.out.print(returned[i][j]+" ");
        }
              System.out.println();
        }

    }
        public static int minimumofarray (int[] totalCost) {
        int minValue = totalCost[0];
        for(int i=0;i<totalCost.length;i++) {
            if(totalCost[i]<minValue) {
                minValue=totalCost[i];
            }
        }
        return minValue;
    }

    public static int[][] FinalArrayCost (int[][] processCost,int[][] moveCost,int N,int M) {
        int[][] Cost = new int[N][M]; /* total cost */
        System.arraycopy(processCost, 0, Cost, 0, 1);
        int[] totalCost = new int[M];

        for (int i=0; i<N-1; i++) {
            for (int j=0; j<M; j++) {
                for (int p=0;p<M;p++) {
                    totalCost[p] = processCost[i+1][j] + moveCost[j][p]+Cost[i][p];
                }
                Cost[i+1][j] = minimumofarray(totalCost);  /* Every cell shows the lowest total cost of the complex process up to step i when step i is performed on machine j. */
            }
        }
        return Cost;
    }
}














