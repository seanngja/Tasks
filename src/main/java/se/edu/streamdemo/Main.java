package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;
import se.edu.streamdemo.task.TaskComparator;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);


        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        ArrayList<Task> filteredList = filterTasksByString(tasksData, "11");

        printAllData(filteredList);

        printDataWithStreams(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesWithStreams(tasksData);

        System.out.println("Total number of deadlines (iterating): " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines (streams): " + countDeadlinesWithStreams(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Counting deadlines with iteration");
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing data with iteration:");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data with streams:");
        tasks.stream()  // create a stream
                .forEach(System.out::println); // terminal operator
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines with iteration:");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Print deadlines using streams:");
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);

    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(toList());
        return filteredList;
    }

    public static void printDeadlinesWithStreams (ArrayList<Task> tasks) {
        System.out.println("Printing deadlines with streams:");
        tasks.stream()
                .filter((t) -> t instanceof Deadline) // lambda function
                .forEach(System.out::println);
    }

    public static int countDeadlinesWithStreams (ArrayList<Task> tasks) {
        System.out.println("Counting deadlines with streams:");
        int count = (int) tasks.stream()
                .filter((t) -> t instanceof Deadline) // lambda function
                .count(); //terminal operation; aggregate operation

        return count;
    }


}
