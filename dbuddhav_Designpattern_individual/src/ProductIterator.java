package DPS;

import java.util.HashMap;

public class ProductIterator extends ListIterator {

	private ClassProductList classProductList;

	public ProductIterator(ClassProductList theProductList) {
		super();
	}
	int productPositionIndex=0;
	String[] allCoursesList = new String[0];
	public boolean hasNext() {

		if (productPositionIndex < allCoursesList.length) if (allCoursesList[productPositionIndex] != null) return true;
		return false;
	}

	public Object next() {
		return null;
	}

	public void moveToHead() {

	}

	public void remove() {

	}
	private HashMap<Integer, String> getIntegerStringHashMap(String courseName, HashMap<Integer, String> hashmap) {
		int productPositionIndex=0;
		hashmap.put(productPositionIndex, courseName);
		return hashmap;
	}

}
