package com.ei.virtualclassroom;

class ScheduleAssignmentCommand implements Command {
    private String className;
    private String assignmentDetails;

    public ScheduleAssignmentCommand(String className, String assignmentDetails) {
        this.className = className;
        this.assignmentDetails = assignmentDetails;
    }

    @Override
    public void execute() {
        VirtualClassroomManager.getInstance().scheduleAssignment(className, assignmentDetails);
    }
}