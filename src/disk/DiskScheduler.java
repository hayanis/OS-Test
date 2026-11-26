
package disk;

import java.util.List;

public interface DiskScheduler {

   
    String getName();

    /**
     * Returns the head's path: the starting position first, then every
     * cylinder the head moves to, in order (including the jumps).
     *
     * @param requests     the request queue (cylinder numbers)
     * @param head         starting position of the head
     * @param diskSize     number of cylinders N (cylinders are 0 to N-1)
     * @param towardHigher true = head starts moving toward N-1, false = toward 0
     */
    List<Integer> schedule(int[] requests, int head, int diskSize, boolean towardHigher);
}
