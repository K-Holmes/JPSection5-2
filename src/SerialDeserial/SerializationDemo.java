package SerialDeserial;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationDemo {
    public static void main(String[] args) {
        Course course = new Course("Java Programming", "Oracle", "JP", 60);
        Path path = Paths.get("C:/JavaProgramming/details.ser");
        serializeData(course, path);
        Course savedCourse = deserializeData(path);
        if (course!=null){
            displayData(savedCourse);
        }
    }

    static void serializeData(Course course, Path path){
        try(FileOutputStream fileOut = new FileOutputStream(path.toString());
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){
            objOut.writeObject(course);
            System.out.println("Serialized data is saved in "+path.toString());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    static Course deserializeData(Path path){
        try(FileInputStream fileIn = new FileInputStream(path.toString());
            ObjectInputStream objIn = new ObjectInputStream(fileIn)){
            Course course = (Course) objIn.readObject();
            return course;
        } catch(ClassNotFoundException e){
            System.out.println("Course class not found");
            return null;
        } catch (IOException i){
            i.printStackTrace();
            return null;
        }
    }

    public static void displayData(Course course){
        System.out.println("Deserialized Course Details...");
        System.out.println(course);
//        System.out.println("Name: "+course.getName());
//        System.out.println("Type: "+course.getType());
//        System.out.println("Code: "+course.getCourseCode());
//        System.out.println("Pass Score: "+course.getPassingScore());
    }
}
