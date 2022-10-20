package DPS;
public class ClassProductList {

	private ProductIterator productIterator;

	private ArrayList<String> productItems;
	public ClassProductList(ArrayList<String> products) {
		this.productItems = products;
	}

	public ArrayList<String> getList() {
		return productItems;
	}

	public void accept(NodeVisitor visitor) {


	}

}

