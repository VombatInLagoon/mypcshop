package org.amin.pcshop.domain;

/*
 * ShoppingCart.java
 *
 * Created on den 12 december 2002, 09:49
 */

import java.util.*;
/**
 *
 * @author  Fredrik �lund, Olle Eriksson
 * 
 * (Borrowed from the bookshop application)
 */
public class ShoppingCart {
    private Collection cart;
    
    /** Creates a new instance of ShoppingCart */

    public ShoppingCart() {
        cart = new ArrayList();
    }
    
    // add some products to the shopping cart

    public void addProduct(Product pb, int quantity) {
        
        Object newItem[] = null;
        Product tmpBean = null;

	// if the cart is empty just add the book

        if (cart.isEmpty()){
            newItem = new Object[2];
            newItem[0]=pb;
            newItem[1]=new Integer(quantity);    
            cart.add(newItem);
        }

	// otherwise we need to check if this book already
	// is in the cart

        else{
	    Iterator iter = cart.iterator();  // get an iterator
	    Object tmpArr[];
	    boolean found = false;
	    while(iter.hasNext()){
		tmpArr=(Object[])iter.next();

		// check if we found the pc

		if(((Product)tmpArr[0]).getId()==pb.getId()){

		    // yes, increase the quantity

		    Integer tmpAntal = (Integer)tmpArr[1];
		    tmpArr[1]=new Integer(tmpAntal.intValue()+quantity); 
		    found= true;
		}
		
	    }
	    
	    // if we didn't find it, add it
	    
	    if(!found){
		newItem = new Object[2];
		newItem[0]=pb;
		newItem[1]=new Integer(quantity);    
		cart.add(newItem);
		System.out.println("addProduct: cart.size():" + cart.size());
	    }
	    
        }          
    }    

    // remove some copies of a product from the cart

    public void removeProduct(int id, int quantity) {

	// if must not be empty
        
        if(!cart.isEmpty()){
            Iterator iter = cart.iterator();
            Object tmpArr[];

	    // search for the book

            while(iter.hasNext()){
                tmpArr=(Object[])iter.next();
                if(((Product)tmpArr[0]).getId()==id){

		    // found

                    Integer tmpAntal = (Integer)tmpArr[1];

		    // if all copies removed, remove the book
		    // from the cart

                    if(tmpAntal.intValue()<=quantity){
                        iter.remove();
                    }
                    else{

			// else reduce quantity

                        tmpArr[1]=new Integer(tmpAntal.intValue()-quantity);
                    }
                }
            }
        }
    }     
    
    // clear the cart

    void clear() {
        cart.clear();
    }
    
    // create an XML document out of the shopping cart

    public String getXml(){
        StringBuffer buff = new StringBuffer();
        
        Iterator iter = cart.iterator();
        Object objBuff[] = null;
        buff.append("<shoppingcart>");
        
        while(iter.hasNext()){
            objBuff =(Object[])iter.next();
            buff.append("<order>");
            buff.append(((Product)objBuff[0]).getXml());
            buff.append("<id>");
            buff.append(((Product)objBuff[0]).getId());
            buff.append("</id>");
            buff.append("<brand>");
            buff.append(((Product)objBuff[0]).getName());
            buff.append("</brand>");
            
            buff.append("<quantity>");
            buff.append(((Integer)objBuff[1]).intValue());
            buff.append("</quantity>");
            buff.append("</order>");            
        }
        buff.append("</shoppingcart>");
        return buff.toString();
    }

    // get the cart

    public Collection getCart(){
      return cart;
    }
    
}
