
package cpu;

import java.util.List;

public class GanttBlock {

    public String name;   
    public int start;
    public int end;

    public GanttBlock(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

   
    public static void add(List<GanttBlock> chart, String name, int start, int end) {
        if (!chart.isEmpty()) {
            GanttBlock last = chart.get(chart.size() - 1);
            if (last.name.equals(name) && last.end == start) {
                last.end = end;
                return;
            }
        }
        chart.add(new GanttBlock(name, start, end));
    }

    @Override
    public String toString() {
        return name + "[" + start + "-" + end + "]";
    }
}
