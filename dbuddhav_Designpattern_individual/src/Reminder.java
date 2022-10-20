package DPS;
public class Reminder {

    void displayReminder(ClassProductList productList) {
        ReminderVisitor visitor = new ReminderVisitor(this);
        Facade f = new Facade();
        visitor.visitFacade(f);
    }


}
