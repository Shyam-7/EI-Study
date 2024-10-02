package com.ei.virtualclassroom;

import java.io.*;
import java.util.*;

public class FileManager {
    private static final String FILE_NAME = "classroom_data.txt";

    public static void saveData(Map<String, Classroom> classrooms) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Classroom> entry : classrooms.entrySet()) {
                writer.println("Classroom:" + entry.getKey());
                for (Student student : entry.getValue().getStudents()) {
                    writer.println("Student:" + student.getId());
                }
                for (Assignment assignment : entry.getValue().getAssignments()) {
                    writer.println("Assignment:" + assignment.getDetails());
                }
                writer.println("EndClassroom");
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static Map<String, Classroom> loadData() {
        Map<String, Classroom> classrooms = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            Classroom currentClassroom = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Classroom:")) {
                    String className = line.substring(10);
                    currentClassroom = new Classroom(className);
                    classrooms.put(className, currentClassroom);
                } else if (line.startsWith("Student:") && currentClassroom != null) {
                    currentClassroom.addStudent(new Student(line.substring(8)));
                } else if (line.startsWith("Assignment:") && currentClassroom != null) {
                    currentClassroom.addAssignment(new Assignment(line.substring(11)));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return classrooms;
    }
}