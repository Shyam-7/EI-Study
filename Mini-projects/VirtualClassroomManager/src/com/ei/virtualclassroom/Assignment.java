package com.ei.virtualclassroom;

import java.util.*;

class Assignment {
    private String details;
    private Set<Student> submittedBy;

    public Assignment(String details) {
        this.details = details;
        this.submittedBy = new HashSet<>();
    }

    public String getDetails() {
        return details;
    }

    public void submit(Student student) {
        submittedBy.add(student);
    }
}