package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> a=new AListNoResizing<>();
        BuggyAList<Integer> b=new BuggyAList<>();
        for(int i=4;i<7;i+=1){
            a.addLast(i);
            b.addLast(i);
        }
        for(int i=0;i<3;i+=1){
            int a1=a.removeLast();
            int b1=b.removeLast();
            assertEquals(a1,b1);
        }
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1=new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L1.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size1= L1.size();
                assertEquals(size,size1);
            }else if(operationNumber==2){
                if(L.size()==0||L1.size()==0)
                    continue;
                int num=L.getLast();
                int num1=L.getLast();
                assertEquals(num,num1);
            }else if(operationNumber==3){
                if(L.size()==0||L1.size()==0)
                    continue;
                int num=L.removeLast();
                int num1=L1.removeLast();
                assertEquals(num,num1);
            }
        }
    }
}
