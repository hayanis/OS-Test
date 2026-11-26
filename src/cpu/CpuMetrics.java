
package cpu;

import java.util.List;

public class CpuMetrics {

    public static double averageTurnaroundTime(List<Process> processes) {
        double sum = 0;
        for (Process p : processes) {
            sum += p.getTurnaroundTime();
        }
        return sum / processes.size();
    }

    public static double averageWaitingTime(List<Process> processes) {
        double sum = 0;
        for (Process p : processes) {
            sum += p.getWaitingTime();
        }
        return sum / processes.size();
    }

    public static double averageResponseTime(List<Process> processes) {
        double sum = 0;
        for (Process p : processes) {
            sum += p.getResponseTime();
        }
        return sum / processes.size();
    }

    
    public static int totalTime(List<GanttBlock> chart) {
        return chart.get(chart.size() - 1).end;
    }

    
    public static int busyTime(List<GanttBlock> chart) {
        int busy = 0;
        for (GanttBlock b : chart) {
            if (!b.name.equals("Idle")) {
                busy += b.end - b.start;
            }
        }
        return busy;
    }

    
    public static double cpuUtilization(List<GanttBlock> chart) {
        return busyTime(chart) * 100.0 / totalTime(chart);
    }

   
    public static double throughput(List<Process> processes, List<GanttBlock> chart) {
        return (double) processes.size() / totalTime(chart);
    }
}
