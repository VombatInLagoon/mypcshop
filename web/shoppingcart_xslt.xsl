<?xml version="1.0" encoding="UTF-8" ?>
<!-- Exempel:
<book>
  <id>1</id>   
  <title>Javaprogramming</title>
  <authorname>Fredrik</authorname>
  <authorsurname>Alund</authorsurname> 
  <price>23</price>
  <pages>234</pages>
  <description>Bla bla bla</description>
</book>
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html"/>

  <xsl:template match="shoppingcart">
  <br /> <br />
  <table border="0" cellspacing="0" width = "840">
    <tr bgcolor="silver">
    <td colspan="4">
        <strong>Shoppingcart</strong>
    </td>
    <tr bgcolor="silver">
      <td>Title</td>
      <td>Quantity</td>
      <td colspan="2">Remove</td>
    </tr>
    </tr>
        <xsl:apply-templates/>
      <tr>
      <td colspan="2">
        <a href="shop?action=checkout">Checkout</a>
      </td>
    </tr>
   </table>
  </xsl:template>
  <xsl:template match="order">
  <form method="post" action="shop">
    <tr>
        <td>
            <xsl:value-of select="book/title"/>
        </td>
        <td align="right">
            <xsl:value-of select="quantity"/>
        </td>

        <td>
            <xsl:element name="input"> <!--A ordinary input in XSLT-->
              <xsl:attribute name="size">2</xsl:attribute>
              <xsl:attribute name="type">text</xsl:attribute>
              <xsl:attribute name="value">1</xsl:attribute>
              <xsl:attribute name="name">quantity</xsl:attribute>
            </xsl:element>        
        </td>
        <td>
            <input type="submit" value="Remove"/>
        </td>
    
      <xsl:element name="input"> <!--A ordinary input in XSLT-->
        <xsl:attribute name="type">hidden</xsl:attribute>
        <xsl:attribute name="value"><xsl:value-of select="book/id"/></xsl:attribute>
        <xsl:attribute name="name">bookid</xsl:attribute>
      </xsl:element>
      <xsl:element name="input"> <!--A ordinary input in XSLT-->
        <xsl:attribute name="type">hidden</xsl:attribute>
        <xsl:attribute name="value">remove</xsl:attribute>
        <xsl:attribute name="name">action</xsl:attribute>
      </xsl:element>

    </tr>
    </form>
  </xsl:template>

  
</xsl:stylesheet>
