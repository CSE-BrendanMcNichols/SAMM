package backEnd;

import java.util.ArrayList;

public class TestDriver {
    private ApplicationFacade facade;

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

        ArrayList<Major> electives = DataLoader.getMajors();

        electives.get(0).displaycoreReq();
    }
}