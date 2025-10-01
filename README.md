AstronautScheduler – Overview

The AstronautScheduler project is a console-based application that helps astronauts manage their daily schedules efficiently. 
It supports adding, removing, and viewing tasks while ensuring no overlapping schedules. 
The project demonstrates OOP principles and incorporates key design patterns such as Singleton, Factory, and Observer.

Components

Task.java
Represents a task with:
Description
Start time
End time
Priority level (High, Medium, Low)

TaskFactory.java
Implements the Factory Pattern to create Task objects with validated input.

ScheduleManager.java
Implements the Singleton Pattern to ensure a single instance manages all tasks, including adding, removing, validating overlaps, and fetching tasks.

Observer.java
Interface for observers to implement, enabling event-driven updates.

TaskConflictObserver.java
Implements the Observer Pattern to notify users when newly added tasks conflict with existing ones.

ConsoleLogger.java
Provides logging for application usage and errors, ensuring traceability.

App.java
Contains the main application logic (menu-driven interface for CRUD operations).

Main.java
The entry point of the program that starts the application.


Design Patterns Used

Singleton Pattern
Class: ScheduleManager
Purpose: Ensures only one schedule manager instance exists to handle all tasks, preventing inconsistencies.

Factory Pattern
Class: TaskFactory
Purpose: Handles creation of validated Task objects, encapsulating task creation logic and ensuring uniformity.

Observer Pattern
Classes: Observer, TaskConflictObserver
Purpose: Observers subscribe to the ScheduleManager and receive notifications about conflicts or updates, enhancing real-time feedback.
