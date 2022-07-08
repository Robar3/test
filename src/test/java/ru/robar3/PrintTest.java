package ru.robar3;

import ru.robar3.annotation.AfterSuite;
import ru.robar3.annotation.BeforeSuite;
import ru.robar3.annotation.Test;

public class PrintTest {

    Print tests =new Print();
    @BeforeSuite
    public void beforeTests(){
        System.out.println("Метод выполняющийся до тестов");
    }
    @AfterSuite
    public void afterTests(){
        System.out.println("Метод выполняющийся после тестов");
    }

    @Test()
    public void print1(){
        tests.print1();
    }
    @Test(priority = 10)
    public void print2(){
        tests.print2();
    }
    @Test(priority = 7)
    public void print3(){
        tests.print3();
    }
    @Test(priority = 9)
    public void print4(){
        tests.print4();
    }
}
