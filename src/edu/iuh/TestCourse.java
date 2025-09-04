/**
 * @description: Chua lop Test de kiem tra, menu va du lieu mau test
 * @author: Tran Van Trieu
 * @version: 1.0
 * @created: 20:42
 *  * Thursday, September 4, 2025
 */
package edu.iuh.;

import java.util.Scanner;

public class Test {

    //Ham tao du lieu mau
    private static void initData(CoursList list) {
        list.addCourse(new course("CS101", "Intro to Programming", 3, "Computer Science"));
        list.addCourse(new course("CS102", "Data Structures", 4, "Computer Science"));
        list.addCourse(new course("MA101", "Calculus I", 3, "Mathematics"));
        list.addCourse(new course("MA102", "Linear Algebra", 3, "Mathematics"));
        list.addCourse(new course("PH101", "Physics I", 4, "Physics"));
        list.addCourse(new course("CS201", "Algorithms", 4, "Computer Science"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CoursList list = new CoursList(100);

        //Khoi tao du lieu mau
        initData(list);

        while (true) {
            System.out.println("\n=== COURSE MANAGEMENT MENU ===");
            System.out.println("1. Add course");
            System.out.println("2. Show all courses");
            System.out.println("3. Remove course");
            System.out.println("4. Search course by ID");
            System.out.println("5. Search courses by title");
            System.out.println("6. Search courses by department");
            System.out.println("7. Sort courses by title");
            System.out.println("8. Find courses with max credits");
            System.out.println("9. Find department with most courses");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Credit: ");
                        int credit;
                        try {
                            credit = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input, please enter a number.");
                            continue;
                        }
                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();
                        course c = new course(id, title, credit, dept);
                        boolean added = list.addCourse(c);
                        System.out.println(added ? "Added successfully." : "Failed to add course.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    course[] all = list.getCourses();
                    if (all.length == 0) {
                        System.out.println("No courses available.");
                    } else {
                        System.out.printf("%-10s %-20s %-5s %-15s\n", "ID", "Title", "Cred", "Department");
                        for (course c : all) {
                            System.out.println(c);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter course ID to remove: ");
                    String rid = sc.nextLine();
                    boolean removed = list.removeCourse(rid);
                    System.out.println(removed ? "Removed successfully." : "Course not found.");
                    break;
                case 4:
                    System.out.print("Enter course ID: ");
                    String sid = sc.nextLine();
                    course found = list.searchCourseById(sid);
                    System.out.println(found != null ? found : "Not found");
                    break;
                case 5:
                    System.out.print("Enter title keyword: ");
                    String kw = sc.nextLine();
                    course[] res1 = list.searchCourse(kw);
                    if (res1.length > 0) {
                        for (course c : res1) System.out.println(c);
                    } else {
                        System.out.println("No courses found");
                    }
                    break;
                case 6:
                    System.out.print("Enter department: ");
                    String dep = sc.nextLine();
                    course[] res2 = list.searchCourseByDepartment(dep);
                    if (res2.length > 0) {
                        for (course c : res2) System.out.println(c);
                    } else {
                        System.out.println("No courses found");
                    }
                    break;
                case 7:
                    course[] sorted = list.sortCourses();
                    for (course c : sorted) System.out.println(c);
                    break;
                case 8:
                    course[] maxCourses = list.findMaxCreditCourses();
                    if (maxCourses.length > 0) {
                        for (course c : maxCourses) System.out.println(c);
                    } else {
                        System.out.println("No courses found");
                    }
                    break;
                case 9:
                    String dept = list.findDepartmentWithMostCourses();
                    System.out.println(dept != null ? dept : "No courses");
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
            }
        }
    }
}