package DPS;
public class Buyer extends Person {

	private Person person;

	public void showMenu() {

	}

	public void createProductMenu(int categoryOfProducts)
	{
		System.out.println("Pattern Type : Factory Design Pattern"); // Factory design pattern is implemented
		switch (categoryOfProducts) {
			case 0 -> {
				menuOfProducts = new MeatProductMenu();
				System.out.println("Menu of meat products is created for buyer"); //Buyer can select the meat product menu or the produce product menu
			}
			default -> {
				menuOfProducts = new ProduceProductMenu();
				System.out.println("Menu of Produce product is created for buyer");
			}
		}

	}

}
