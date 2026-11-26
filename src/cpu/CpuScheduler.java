
package cpu;

import java.util.List;

public interface CpuScheduler {

    
    String getName();

   
    boolean usesPriority();

   
    List<GanttBlock> schedule(List<Process> processes);
}
