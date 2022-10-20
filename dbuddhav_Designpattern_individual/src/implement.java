package DPS;
public class implement {
    public static void main(String[] args) throws Exception{
        System.out.println("Welcome to the Product Selector!");
        Facade f = new Facade();
        boolean isLoggedIn;
        if (f.login()) isLoggedIn = true;
        else isLoggedIn = false;
    }
}
