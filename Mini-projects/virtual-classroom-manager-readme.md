# Virtual Classroom Manager

## Overview

The Virtual Classroom Manager is a console-based application designed to simulate the management of virtual classrooms. It allows users to create classrooms, enroll students, schedule assignments, and submit assignments. This project demonstrates the application of object-oriented programming principles, design patterns, and SOLID principles in Java.

## Project Requirements

1. **Console-based Application**: The application runs in the terminal/console without a graphical user interface.
2. **Focus on Logic and Code Quality**: Emphasis is placed on well-structured code, adherence to best practices, and efficient logic rather than visual presentation.
3. **Design Patterns**: The project incorporates behavioral, creational, and structural design patterns.
4. **SOLID Principles**: The code adheres to SOLID principles of object-oriented design.
5. **OOP**: Demonstrates strong object-oriented programming practices.

## Features

- Create and manage virtual classrooms
- Add students to classrooms
- Schedule assignments for classrooms
- Submit assignments on behalf of students
- View classroom and assignment information

## Design Patterns Used

1. **Singleton Pattern** (Creational): Used for the VirtualClassroomManager to ensure a single point of control.
2. **Command Pattern** (Behavioral): Implemented to handle user inputs and actions.
3. **Factory Method Pattern** (Creational): Used in creating command objects based on user input.

## Project Structure

- `VirtualClassroomManager.java`: Core class managing classrooms and operations
- `Classroom.java`: Represents a virtual classroom
- `Student.java`: Represents a student
- `Assignment.java`: Represents an assignment
- `Command.java`: Interface for command objects
- Various command classes (e.g., `AddClassroomCommand.java`, `AddStudentCommand.java`, etc.)
- `VirtualClassroomApplication.java`: Main application class with user interface

## How to Run

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Compile all Java files:
   ```
   javac *.java
   ```
3. Run the main application:
   ```
   java VirtualClassroomApplication
   ```
4. Follow the on-screen prompts to interact with the Virtual Classroom Manager.

## Usage

The application accepts the following commands:

- `add_classroom <className>`: Creates a new classroom
- `add_student <studentId> <className>`: Enrolls a student in a classroom
- `schedule_assignment <className> <assignmentDetails>`: Schedules an assignment for a classroom
- `submit_assignment <studentId> <className> <assignmentDetails>`: Submits an assignment for a student
- `exit`: Exits the application

## Extending the Project

To add new features or modify existing ones:

1. Create new command classes implementing the `Command` interface for new functionalities.
2. Add corresponding methods in `VirtualClassroomManager` to handle new operations.
3. Update the `parseCommand` method in `VirtualClassroomApplication` to recognize new commands.

## Notes

- This project demonstrates a basic implementation. In a production environment, additional features like data persistence, user authentication, and more robust error handling would be necessary.
- The current implementation stores data in memory. For long-term use, we can consider implementing a database solution.

