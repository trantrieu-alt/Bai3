/**
 * @description: Chua class CourseList dung de thuc hien cac chuc nang
 * @author: Tran Van Trieu
 * @version: 1.0
 * @created: 20: 25
 *  * Thursday, September 4, 2025
 */
package edu.iuh;

import java.util.Arrays;
import java.util.Comparator;

public class CourseList {
    //Property
    private int count;
    private Course[] courses;

    public CourseList(int size) {
        if(size <= 0) {
            throw new
                    IllegalArgumentException(
                    "Length of the array must be greater than 0");
        }
        this.courses = new Course[size];
        this.count = 0;
    }

    public boolean addCourse(Course c) {
        if(exist(c)) {
            System.out.println("Course ID already exist");
            return false;
        }
        if(count >= courses.length) {
            System.out.println("Course list is full");
            return false;
        }
        courses[count++] = c;
        return true;
    }

    public boolean exist(Course c) {
        for(int i = 0; i < count; i++) {
            if(courses[i].getId().equalsIgnoreCase(c.getId()))
                return true;
        }
        return false;
    }
    //Getters
    public Course[] getCourses() {
        return Arrays.copyOf(courses, count);
    }

    public String findDepartmentWithMostCourses() {
        if (count == 0) return null;
        String[] depNames = new String[count];
        int[] depCounts = new int[count];
        int depSize = 0;
        for (int i = 0; i < count; i++) {
            String dep = courses[i].getDepartment();
            boolean found = false;
            for (int j = 0; j < depSize; j++) {
                if (depNames[j].equals(dep)) {
                    depCounts[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                depNames[depSize] = dep;
                depCounts[depSize] = 1;
                depSize++;
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < depSize; i++) {
            if (depCounts[i] > depCounts[maxIndex]) {
                maxIndex = i;
            }
        }
        return depNames[maxIndex];
    }


    public Course[] findMaxCreditCourses() {
        if(count == 0) return null;
        int maxCredit = courses[0].getCredit();
        for(int i = 1; i < count; i++) {
            if (courses[i].getCredit() > maxCredit) {
                maxCredit = courses[i].getCredit();
            }
        }
        int cnt = 0;
        Course[] tmp = new Course[count];
        for(int i = 0; i < count; i++) {
            if(courses[i].getCredit() == maxCredit) {
                tmp[cnt++] = courses[i];
            }
        }
        if (cnt == 0) return new Course[0];
        return Arrays.copyOf(tmp, cnt);
    }

    public boolean removeCourse(String id) {
        for(int i = 0; i < count; i++) {
            if(courses[i].getId().equalsIgnoreCase(id)) {
                for(int j = i; j < count - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                courses[--count] = null;
                return true;
            }
        }
        return false;
    }

    public Course[] searchCourse(String key) {
        int cnt = 0;
        Course[] temp = new Course[count];
        for(int i = 0; i < count; i++) {
            if(courses[i].getTitle().toLowerCase().contains(key.toLowerCase())) {
                temp[cnt++] = courses[i];
            }
        }
        if (cnt == 0) return new Course[0];
        return Arrays.copyOf(temp, cnt);
    }

    public Course[] searchCourseByDepartment(String dept) {
        int cnt = 0;
        Course[] tmp = new Course[count];
        for(int i = 0; i < count; i++) {
            if(courses[i].getDepartment().equalsIgnoreCase(dept)) {
                tmp[cnt++] = courses[i];
            }
        }
        if (cnt == 0) return new Course[0];
        return Arrays.copyOf(tmp, cnt);
    }

    public Course searchCourseById(String id) {
        for(int i = 0; i < count; i++) {
            if(courses[i].getId().equalsIgnoreCase(id)) {
                return courses[i];
            }
        }
        return null;
    }

    public Course[] sortCourses() {
        Course[] sorted = Arrays.copyOf(courses, count);
        Arrays.sort(sorted, Comparator.comparing(Course::getTitle, String.CASE_INSENSITIVE_ORDER));
        return sorted;
    }

}