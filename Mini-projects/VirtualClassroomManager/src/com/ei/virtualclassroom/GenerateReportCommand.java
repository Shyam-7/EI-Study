package com.ei.virtualclassroom;

class GenerateReportCommand implements Command {
    private String className;

    public GenerateReportCommand(String className) {
        this.className = className;
    }

    @Override
    public void execute() {
        VirtualClassroomManager.getInstance().generateReport(className);
    }
}