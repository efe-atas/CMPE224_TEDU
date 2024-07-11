
//-----------------------------------------------------
// Title: HW2_solution Class
// Author: İsmail Efe Ataş
// ID: 
// Section: 1
// Assignment: Homework2_Question_1
// Description: This class for main.
//-----------------------------------------------------
import java.util.List;
import java.util.Scanner;

public class HW2_solution {
    public static void main(String[] args) {
        String filePath = "HW2_Q1_text.txt";
        DirectGraph graph = new DirectGraph();


        List<String> lines = FileRead.readLines(filePath);
        Valuefinder.processLines(graph, lines);


        System.out.println(" V="+Valuefinder.V);
        System.out.println("E="+Valuefinder.E);
        for(String input : lines){
              if(input.length() > 2)
            System.out.println(input);
        }
        Scanner keyboard = new Scanner(System.in);
        int start = keyboard.nextInt();
        keyboard.close();
        System.out.println();
        System.out.println("Start point:");
        System.out.println(start);
        graph.getTwoStepDestinations(start);


    }
}
