package DPS;
public class Seller extends Person {

	private Person person;

	public void showMenu() {
		System.out.println("Using Bridge Pattern");
		System.out.println("Menu list");

	}

	@Override
	public void createProductMenu(int categoryOfProducts) {
		System.out.println("Using Factory Design Pattern");
		if (categoryOfProducts != 0) {
			menuOfProducts = new ProduceProductMenu();
			System.out.println("Menu of produce products is Created for Buer");
		} else {
			menuOfProducts = new MeatProductMenu();
			System.out.println("Menu of meat products are created for buyer");
		}


	}



}
