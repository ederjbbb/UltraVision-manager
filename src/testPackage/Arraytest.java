package testPackage;

import java.util.ArrayList;
import java.util.Collections;

public class Arraytest {
    String name ;
    int age;

    public Arraytest(String name, int age) {
        this.name = name;
        this.age = age;
    }



    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        Arraytest arraytest0 = new Arraytest("eder", 34);
        Arraytest arraytest1 = new Arraytest("eder2",2);
        Arraytest arraytest2 = new Arraytest("eder3",3);
        Collections.addAll(list, arraytest0,arraytest1,arraytest2);
        System.out.println(list.size());
        list.remove(arraytest0);
        System.out.println("now "+list.size());
        list.remove(arraytest1);
        System.out.println("now "+list.size());
        list.remove(arraytest2);
        System.out.println("now "+list.size());

    }

}
