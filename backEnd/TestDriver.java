package backEnd;

import java.util.ArrayList;

public class TestDriver {
    private SchedulingFacade facade;

    TestDriver(){
        //facade = new SchedulingFacade;
    }
    public void run(){
        scenario1();
        //scenario2();
    }
    public static void main(String[] args) {
        scenario1();
    }
    public static void scenario1(){
        
        DataLoader dataLoader = new DataLoader();

        ArrayList<Elective> electives = DataLoader.getElectives();

        electives.get(2).displayName();
    }
}