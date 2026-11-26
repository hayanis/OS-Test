# OS-Test
# CPU and Disk Scheduling Simulator

ICS26012 – Operating Systems · Final Period Project
Section: 3ITH · Group 8
Repository: https://github.com/hayanis/OS-Test.git

| Member  | Algorithm                    | File                                     |
|---------|------------------------------|------------------------------------------|
| Pascual | FCFS (CPU, non-preemptive)   | `src/cpu/FcfsScheduler.java`             |
| Larin   | Priority – Preemptive (CPU)  | `src/cpu/PriorityPreemptiveScheduler.java'|
| Sabater | C-SCAN (Disk)                | `src/disk/CScanScheduler.java`           |
| Larios  | C-LOOK (Disk)                | `src/disk/CLookScheduler.java`           |

## Requirements
Java 8 or newer. Only Java SE and Swing are used (no database, no internet).

## How to run
Double-click `OS-Scheduler.jar`, or in a terminal in this folder:

java -jar OS-Scheduler.jar


## How to build
Windows: `build.bat` · macOS/Linux: `sh build.sh`
This compiles `src/`, runs the tests, and creates `OS-Scheduler.jar` (needs the JDK).

Run the tests only (after building once):

java -cp build\classes;build\test AlgorithmTests      (Windows)
java -cp build/classes:build/test AlgorithmTests      (macOS/Linux)

Run only the Priority algorithm in the console: replace `AlgorithmTests` with `RunPriority`.

In VS Code: install "Extension Pack for Java", open this folder, and use Run and Debug.

## Folders

src/cpu/    Process, GanttBlock, CpuMetrics, CpuScheduler (interface), FCFS, Priority
src/disk/   DiskMetrics, DiskScheduler (interface), C-SCAN, C-LOOK
src/gui/    Main, MainMenu, CpuScreen, DiskScreen, GanttChart, HeadPathGraph, InputCheck
test/       AlgorithmTests (test log), RunPriority (console runner)

The algorithm classes have no GUI code. The screens use them only through the
`CpuScheduler` and `DiskScheduler` interfaces.

## Conventions followed
- Time starts at 0. Idle CPU time is shown in the Gantt chart. Context switch = 0.
- CPU ties: earlier arrival first, then lower PID (earlier row).
- Priority: lower number = higher priority, no aging; preempts only if strictly higher.
- Turnaround = Completion − Arrival; Waiting = Turnaround − Burst; Response = First CPU time − Arrival.
- Utilization = busy time ÷ total time × 100; Throughput = processes ÷ total time.
- Cylinders are 0 to N−1 (default 200). Head movement = sum of |differences|.
- Duplicate requests are served one after the other (zero movement).
- C-SCAN goes to the end of the disk; C-LOOK turns at the last request.
  Both count the return jump in total head movement.
- Average seek distance = total head movement ÷ number of requests.
- C-SCAN: if all requests are in the starting direction, the head stops at the last request.
