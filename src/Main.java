enum Singleton {
    INSTANCE;  // single element

    public void showMessage() {
        System.out.println("Enum Singleton!");
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton s = Singleton.INSTANCE;
        s.showMessage();   // Output: Hello from Enum Singleton!
    }
}
