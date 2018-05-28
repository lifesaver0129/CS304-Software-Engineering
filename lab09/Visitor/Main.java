
public class Main {


    public static Element[] list = {new This(), new That(), new TheOther()};

    // 4. Client creates "visitor" objects and passes each to accept() calls
    public static void main(String[] args) {
        UpVisitor up = new UpVisitor();
        DownVisitor down = new DownVisitor();

        for (int i = 0; i < list.length; i++) {
            list[i].accept(up);
        }

        for (int i = 0; i < list.length; i++) {
            list[i].accept(down);
        }
    }

}
