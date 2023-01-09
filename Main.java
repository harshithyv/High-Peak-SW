import java.io.*;
import java.util.*;

class Job {
    int startTime;
    int endTime;
    int profit;

    public Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        // Read the input from the external file
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(br.readLine());
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            int startTime = Integer.parseInt(br.readLine());
            int endTime = Integer.parseInt(br.readLine());
            int profit = Integer.parseInt(br.readLine());
            jobs[i] = new Job(startTime, endTime, profit);
        }
        br.close();

        // Sort the jobs by their end time
        Arrays.sort(jobs, new Comparator<Job>() {
            public int compare(Job j1, Job j2) {
                return j1.endTime - j2.endTime;
            }
        });

        // Pick the jobs for Lokesh in a way that maximizes his earnings
        int maxEarnings = 0;
        for (int i = 0; i < n; i++) {
            int startTime = jobs[i].startTime;
            int endTime = jobs[i].endTime;
            int profit = jobs[i].profit;
            if (endTime <= maxEarnings) {
                maxEarnings = Math.max(maxEarnings, startTime + profit);
            }
        }

        // Calculate the number of tasks and earnings available for others
        int tasks = 0;
        int earnings = 0;
        for (int i = 0; i < n; i++) {
            int startTime = jobs[i].startTime;
            int endTime = jobs[i].endTime;
            int profit = jobs[i].profit;
            if (endTime > maxEarnings) {
                tasks++;
                earnings += profit;
            }
        }

        // Write the output to the external file
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        bw.write("Task: " + tasks);
        bw.newLine();
        bw.write("Earnings: " + earnings);
        bw.close();
    }
}