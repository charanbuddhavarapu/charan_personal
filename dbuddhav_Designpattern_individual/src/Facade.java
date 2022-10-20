package DPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Facade implements ActionListener {

	private int userType;

	private int ProductSelected;

	private int nProductCategory;

	ClassProductList listofProduct;

	private Person thePerson;

	public int selection=0;

	private static JRadioButton Buyer, Seller;
	private static JLabel USERNAME, PassWord;
	private static JTextField textField1;
	private static JPasswordField textField2;
	private static JButton login, reset;
	private static ButtonGroup group;

	public boolean login() throws IOException {
		Frame jf = new JFrame("Login Frame");
		((JFrame) jf).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel(new GridBagLayout());
		GridBagConstraints gbcs;
		gbcs = new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.BASELINE_TRAILING,
				GridBagConstraints.NONE,
				new Insets(5, 5, 15, 5), 4, 6);

		Buyer =new JRadioButton("Buyer");
		jp.add(Buyer,gbcs);
		gbcs.gridx=1;
		Seller =new JRadioButton("Seller");
		jp.add(Seller,gbcs);
		gbcs.gridx=0;
		gbcs.gridy=1;

		group = new ButtonGroup();
		Buyer.setActionCommand("Buyer");
		Seller.setActionCommand("Seller");
		group.add(Buyer);
		group.add(Seller);
		Buyer.addActionListener((ActionListener) new Facade());
		Seller.addActionListener((ActionListener) new Facade());

		USERNAME = new JLabel("Username");

		jp.add(USERNAME,gbcs);
		gbcs.gridx=1;
		gbcs.gridy = 1;
		textField1 = new JTextField(10);
		jp.add(textField1,gbcs);
		gbcs.gridx=0;
		gbcs.gridy = 2;


		PassWord = new JLabel("Password");
		jp.add(PassWord,gbcs);
		gbcs.anchor = GridBagConstraints.BASELINE_LEADING;
		gbcs.gridx = 1;
		gbcs.gridy = 2;
		textField2 = new JPasswordField(10);
		jp.add(textField2, gbcs);
		gbcs.gridy=4;

		login = new JButton("Login");
		reset = new JButton("Refresh");
		login.addActionListener((ActionListener) new Facade());
		reset.addActionListener((ActionListener) new Facade());
		jp.add(login,gbcs);
		gbcs.gridy=5;
		jp.add(reset,gbcs);


		jf.setSize(800, 300);

		jf.add(jp);
		jf.setVisible(true);
		jp.setBackground(Color.decode("#f4c2c2"));
		return true;
	}

	public void addTrading() {

	}

	public void viewTrading() {

	}

	public void decideBidding() {

	}

	public void submitBidding() {

	}

	public void remind() {
		Reminder reminder = new Reminder();
		reminder.displayReminder(listofProduct);
	}

	public void createUser(int selection) {
		thePerson = !(selection != 0) ? new Buyer() : new Seller();
		switch (selection) {
			case 0 -> System.out.println("Buyer Instance : Created  ");
			default -> System.out.println("Seller instance : Created  ");
		}

	}

	public void createProductList() {

	}

	public void AttachProductList() {

	}

	public void linkProductToUser(String username) throws IOException {
		Map<String,ArrayList<String>> allProducts= new HashMap<>();
		String f = "E:\\Charan\\515_Foundations_of_software_engineering\\Designpattern_individual\\ProductInfo.txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
		String tempStr;

		while((tempStr = bufferedReader.readLine())!=null) {
			if(allProducts.containsKey(tempStr.split(":",-1)[0])) {
				allProducts.get(tempStr.split(":",-1)[0]).add(tempStr.split(":",-1)[1]);
			}
			else {
				ArrayList<String> prod = new ArrayList<>() {};
				prod.add(tempStr.split(":",-1)[1]);
				allProducts.put(tempStr.split(":",-1)[0],prod);
			}
		}
		listofProduct = new ClassProductList(allProducts.get(username));

	}

	public boolean selectProduct() throws IOException {
		System.out.println("Using Iterator Design Pattern");
		System.out.println("Please Select (0) for  Meat, (1) for Produce: ");
		Scanner sc = new Scanner(System.in);
		int categoryType = sc.nextInt();
		Map<String,String> category = new HashMap<String,String>();
		String f = "E:\\Charan\\515_Foundations_of_software_engineering\\Designpattern_individual\\ProductInfo.txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
		String tempStr;

		while((tempStr = bufferedReader.readLine())!=null) {
			category.put(tempStr.split(":")[1],tempStr.split(":",-1)[0]);
		}
		if(categoryType>1)
			return false;

		nProductCategory = categoryType;
		if(categoryType == 0){
			System.out.println("Meat options ");
		}
		else {
			System.out.println("Produce options ");
		}
		ProductIterator iterator = new ProductIterator(listofProduct);
		while (iterator.hasNext()) {
			String st = (String) iterator.next();
			if(categoryType == 0 && category.get(st).equals("Meat"))
				System.out.println(st);
			if(categoryType == 1 && category.get(st).equals("Produce"))
				System.out.println(st);
		}
		return true;
	}

	public int productOperation() {
		System.out.println("Enter 1. Generate Product Menu 2. View Product Menu 3. Reminders 4. Logout");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if(choice ==1 )
			thePerson.createProductMenu(nProductCategory);
		else if (choice == 2)
			thePerson.showMenu();
		else if (choice == 3)
			remind();
		else if (choice == 4)
			System.exit(0);
		else
			System.out.println("Invalid input. Enter a valid choice");
		return 0;

	}

	public void actionPerformed(ActionEvent e) {
		Facade facade = new Facade();
		String u = textField1.getText();
		String p = textField2.getText();
		selection = 0;
		if (Buyer.isSelected()) {
			selection = 0;
			System.out.println("Welcome Buyer!");
		} else {
			selection = 1;
			System.out.println("Welcome Seller!");
		}

		if (e.getSource() == login) {
			Map<String, String> users = new HashMap<>();
			switch (selection) {
				case 0:
					try {
						String f = "E:\\Charan\\515_Foundations_of_software_engineering\\Designpattern_individual\\BuyerInfo.txt";
						BufferedReader br = new BufferedReader(new FileReader(f));
						String s;
						while ((s = br.readLine()) != null) {
							users.put(s.split(":", -1)[0], s.split(":", -1)[1]);
						}
						if (p.equals(users.get(u))) {
							System.out.println("Hello " + u);
							System.out.println("Your Details " + u + ", " + p);
							//JOptionPane.showMessageDialog(null, "Login successful");

							Frame jFrame = new JFrame();
							jFrame.setSize(500, 500);
							JPanel jPanel = new JPanel();
							jPanel.setBackground(Color.decode("#6082B6"));
							JLabel jLabel1 = new JLabel();
							JLabel jLabel = new JLabel("Please Select (0) for Meat, (1) Produce");
							JTextField jTextField = new JTextField(10);
							JButton submit = new JButton("Submit");
							jPanel.add(jLabel1);
							jPanel.add(jLabel);
							jPanel.add(jTextField);
							jPanel.add(submit);
							jFrame.add(jPanel);
							jFrame.setVisible(true);
							submit.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									System.out.println(submit);
									String input;
									input = jTextField.getText();
									System.out.println(input);
									facade.displayMenu(selection, input);
								}
							});
						} else {
							JOptionPane.showMessageDialog(null, "Please Enter the Correct Username or Password");
						}

					} catch (IOException ex) {
						ex.printStackTrace();
					}
					break;
				default:
					try {
						String f = "E:\\Charan\\515_Foundations_of_software_engineering\\Designpattern_individual\\SellerInfo.txt";
						BufferedReader br = new BufferedReader(new FileReader(f));
						String s;
						while ((s = br.readLine()) != null) {
							users.put(s.split(":", -1)[0], s.split(":", -1)[1]);
						}
						if (!p.equals(users.get(u))) {
							JOptionPane.showMessageDialog(null, "Incorrect username or password.");
						} else {
							System.out.println("Hello " + u);
							System.out.println("Your details :  " + u + ", " + p);

							//JOptionPane.showMessageDialog(null, "Login successful");
							Frame jFrame = new JFrame();
							jFrame.setSize(500, 500);
							JPanel jPanel = new JPanel();
							jPanel.setBackground(Color.decode("#FBF4DA"));
							JLabel jLabel1 = new JLabel();
							JLabel jLabel = new JLabel("Please Select (0) for Meat, (1) for Produce");
							JTextField jTextField = new JTextField(10);
							JButton submit = new JButton("Submit");
							jPanel.add(jLabel1);
							jPanel.add(jLabel);
							jPanel.add(jTextField);
							jPanel.add(submit);
							jFrame.add(jPanel);
							jFrame.setVisible(true);
							submit.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									System.out.println(submit);
									String input = jTextField.getText();
									System.out.println(input);
									facade.displayMenu(selection, input);
								}
							});
						}
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					break;
			}
		} else {
			textField1.setText("");
			textField2.setText("");
		}
		createUser(selection);
		try {
			linkProductToUser(u);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void displayMenu(int selection,String input){
		JFrame jFrame = new JFrame("Menu Frame");
		jFrame.setSize(500,300);

		JPanel jp1 = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.BASELINE_TRAILING,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 4, 6);
		jp1.setBackground(Color.decode("#CFFFA3"));
		JLabel msg;
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Meat Menu");
		JMenu menu2 = new JMenu("Produce Menu");

		switch (selection) {
			case 0 -> {
				msg = new JLabel("Hey there Buyer!");
				jp1.add(msg);
				JLabel msg1 = new JLabel("Your available options are...");
				jp1.add(msg1);
				if (!input.contains("0")) {
					if (input.contains("1")) {
						JMenuItem item2 = new JMenuItem("Tomato");
						JMenuItem item3 = new JMenuItem("Onion");
						JMenuItem item4 = new JMenuItem("Mushroom");
						JMenuItem item5 = new JMenuItem("Potato");
						menu2.add(item2);
						menu2.add(item3);
						menu2.add(item4);
						menu2.add(item5);
						menuBar.add(menu2);
					} else {
						JLabel jLabel = new JLabel("Invalid choice");
						jp1.add(jLabel);
					}
				} else {
					JMenuItem item1 = new JMenuItem("Chicken");
					menu1.add(item1);
					menuBar.add(menu1);
				}
				break;
			}
			default -> {
				msg = new JLabel("Hola Seller!");
				jp1.add(msg);
				JLabel msg1 = new JLabel(" Your available options are...");
				jp1.add(msg1);
				if (!input.contains("0")) {
					if (input.contains("1")) {
						JMenuItem item2 = new JMenuItem("Tomato");
						JMenuItem item3 = new JMenuItem("Onion");
						JMenuItem item4 = new JMenuItem("Mushroom");
						JMenuItem item5 = new JMenuItem("Potato");

						menu2.add(item2);
						menu2.add(item3);
						menu2.add(item4);
						menu2.add(item5);
						menuBar.add(menu2);
					} else {
						JLabel jLabel = new JLabel("Invalid choice");
						jp1.add(jLabel);
					}
				} else {
					System.out.println("Test");
					JMenuItem item1 = new JMenuItem("Chicken");
					menu1.add(item1);
					menuBar.add(menu1);
				}
				break;
			}
		}
		jp1.add(menuBar);
		jFrame.add(jp1);
		jFrame.setVisible(true);
		//productOperation();
	}
}



