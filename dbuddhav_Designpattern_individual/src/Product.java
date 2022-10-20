package DPS;
public class Product {
    //the variable names can be changed
    private String productsName;
    private String kindOfProduct; // it says the productType

    public Product(String productName, String productType){
        this.productsName =productName;
        this.kindOfProduct =productType;
    }

    public String getProductName(){return productsName;}
    public String getProductType(){return kindOfProduct;}

}
