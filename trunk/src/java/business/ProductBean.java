/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author amin
 */
public class ProductBean {
    // describe a book

    private int id;
    private String brand;
    private String description;

    /** Creates a new instance of ComponentBean */
    public ProductBean() {
    }

    

    public String getName() {
        return brand;
    }

    public void setName(String _brand) {
        brand = _brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;

    }

    public void setDescription(String _description) {
        description = _description;
    }

    public String getDescription() {
        return description;
    }

    // create an XML document describing the book
    public String getXml() {

        // use a Stringbuffer (not String) to avoid multiple
        // object creation

        StringBuffer xmlOut = new StringBuffer();

        xmlOut.append("<product>");
        xmlOut.append("<id>");
        xmlOut.append(id);
        xmlOut.append("</id>");
        xmlOut.append("<brand><![CDATA[");
        xmlOut.append(brand);
        xmlOut.append("]]></brand>");
        xmlOut.append("<description><![CDATA[");
        xmlOut.append(description);
        xmlOut.append("]]></description>");
        xmlOut.append("</product>");

        return xmlOut.toString();


    }
}
