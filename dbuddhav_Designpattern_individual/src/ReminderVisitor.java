package DPS;
public class ReminderVisitor extends NodeVisitor {

	private Reminder m_Reminder;

	public ReminderVisitor(Reminder reminder) {
		super();
		m_Reminder = reminder;

	}

	public void visitProduct(Product product) {

	}

	public void visitTrading(Trading trading) {

	}

	public void visitFacade(Facade facade) {
		System.out.println("Visitor design pattern");
		ProductIterator productList = new ProductIterator(facade.listofProduct);
		System.out.print("Reminders");

	}

}
