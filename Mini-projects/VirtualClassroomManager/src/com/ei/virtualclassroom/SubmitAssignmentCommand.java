package com.ei.virtualclassroom;

class SubmitAssignmentCommand implements Command {
    private String studentId;
    private String className;
    private String assignmentDetails;

    public SubmitAssignmentCommand(String studentId, String className, String assignmentDetails) {
        this.studentId = studentId;
        this.className = className;
        this.assignmentDetails = assignmentDetails;
    }

    @Override
    public void execute() {
        VirtualClassroomManager.getInstance().submitAssignment(studentId, className, assignmentDetails);
    }
}