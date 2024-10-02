package com.ei.virtualclassroom;

class AddClassroomCommand implements Command {
    private String className;

    public AddClassroomCommand(String className) {
        this.className = className;
    }

    @Override
    public void execute() {
        VirtualClassroomManager.getInstance().addClassroom(className);
    }
}