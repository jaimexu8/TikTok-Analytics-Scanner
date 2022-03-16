import java.util.*;
import java.io.*;

public class BallScanner {
   
   public static void main(String[] args) throws FileNotFoundException, IOException {
      
      File outputFile = new File("Output.txt");
      videoBrowsing(outputFile);
   }
   
   public static void videoBrowsing(File outputFile) throws FileNotFoundException, IOException {
      
      PrintStream ps = new PrintStream (outputFile);
      File watchedVideos = new File("./Data/Activity/Video Browsing.txt");
      Scanner s = new Scanner(watchedVideos);
      ArrayList<Integer> hours = new ArrayList<Integer>();
      int vc = 0;
      
      while (s.hasNextLine()) {
         String line = s.nextLine();
         if (line.startsWith("Date:")) {
            String hour = line.substring(17, 19);
            hours.add(Integer.parseInt(hour));
         }
         
         if (line.startsWith("Video Link:"))
            vc++;
      }
      
      Collections.sort(hours);
      int[] counts = new int[24];
      counts = findCounts(hours);
      int mostActive = 0;
      int leastActive = 0;
      for (int i = 1; i < 24; i++) {
         counts[i]++; //adds a single count
         if (counts[i] > counts[mostActive])
            mostActive = i;
         if (counts[i] < counts[leastActive])
            leastActive = i;
      }
      
      System.out.println("Video count: " + vc);
      if (mostActive == 0) {
         System.out.println("Most active hour: 12 am : " + counts[0] + " TikToks");
         ps.println("Most active hour: 12 am : " + counts[0] + " TikToks");
      } else if (mostActive < 12) {
         System.out.println("Most active hour: " + mostActive + " am : " + counts[mostActive] + " TikToks");
         ps.println("Most active hour: " + mostActive + " am : " + counts[mostActive] + " TikToks");
      } else if (mostActive == 12) {
         System.out.println("Most active hour: 12 pm : " + counts[12] + " TikToks");
         ps.println("Most active hour: 12 pm : " + counts[12] + " TikToks");
      } else {
         System.out.println("Most active hour: " + (mostActive - 12) + " pm : " + counts[mostActive] + " TikToks");
         ps.println("Most active hour: " + (mostActive - 12) + " pm : " + counts[mostActive] + " TikToks");
      }
      if (leastActive == 0) {
         System.out.println("Least active hour: 12 am : " + counts[0] + " TikToks");
         ps.println("Least active hour: 12 am : " + counts[0] + " TikToks");
      } else if (leastActive < 12) {
         System.out.println("Least active hour: " + leastActive + " am : " + counts[leastActive] + " TikToks");
         ps.println("Least active hour: " + leastActive + " am : " + counts[leastActive] + " TikToks");
      } else if (leastActive == 12) {
         System.out.println("Least active hour: 12 pm : " + counts[12] + " TikToks");
         ps.println("Least active hour: 12 pm : " + counts[12] + " TikToks");
      } else {
         System.out.println("Least active hour: " + (leastActive - 12) + " pm : " + counts[leastActive] + " TikToks");
         ps.println("Least active hour: " + (leastActive - 12) + " pm : " + counts[leastActive] + " TikToks");
      }
      
      System.out.println();
      ps.println();
      
      for (int i = 0; i < 24; i++) {
         if (i == 0) {
            System.out.println("Videos viewed at 12 am: " + counts[0]);
            ps.println("Videos viewed at 12 am: " + counts[0]);
         } else if (i < 12) {
            System.out.println("Videos viewed at " + i + " am: " + counts[i]);
            ps.println("Videos viewed at " + i + " am: " + counts[i]);
         } else if (i == 12) {
            System.out.println("Videos viewed at 12 pm: " + counts[12]);
            ps.println("Videos viewed at 12 pm: " + counts[12]);
         } else {
            System.out.println("Videos viewed at " + (i - 12) + " pm: " + counts[i]);
            ps.println("Videos viewed at " + (i - 12) + " pm: " + counts[i]);
         }
      }

   }
   
   public static int[] findCounts(ArrayList<Integer> hours) {
      
      int currInt = 0;
      int prevInt = 0;
      int[] counts = new int[24];
      for (int i = 1; i < hours.size(); i++) {
         currInt = hours.get(i);
         if (currInt == prevInt)
            counts[currInt]++;
         prevInt = currInt;
      }
      return counts;
   }
   
}