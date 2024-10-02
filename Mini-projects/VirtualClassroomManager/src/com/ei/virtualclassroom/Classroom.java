package com.ei.virtualclassroom;

import java.util.*;

class Classroom {
    @SuppressWarnings("unused")
    private String name;
    private Set<Student> students;
    private List<Assignment> assignments;

    public Classroom(String name) {
        this.name = name;
        this.students = new HashSet<>();
        this.assignments = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }
    public Set<Student> getStudents() {
        return new HashSet<>(students);
    }

    public List<Assignment> getAssignments() {
        return new ArrayList<>(assignments);
    }

    public String listAssignments() {
        return assignments.stream()
                .map(Assignment::getDetails)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
    
    public boolean submitAssignment(String studentId, String assignmentDetails) {
        Student student = students.stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst()
                .orElse(null);

        Assignment assignment = assignments.stream()
                .filter(a -> a.getDetails().equals(assignmentDetails))
                .findFirst()
                .orElse(null);

        if (student != null && assignment != null) {
            assignment.submit(student);
            return true;
        }
        return false;
    }

    public String listStudents() {
        return students.stream()
                .map(Student::getId)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}