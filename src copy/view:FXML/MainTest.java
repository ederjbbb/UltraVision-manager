package view;

public class MainTest {

    String msg ;
public MainTest() {
    String msg = "I am studying";
    print(msg);
}


public void print(String msg){
    System.out.println(msg);
}

    public static void main(String[] args) {
        new MainTest();
    }
}
