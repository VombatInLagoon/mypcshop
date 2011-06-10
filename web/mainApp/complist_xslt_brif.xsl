<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html"/>

  <xsl:template match="complist">
  <table border="0" width="840">
        
    <tr bgcolor="orange" cellspacing="0">
    <td>
        <strong>Component</strong> 
    </td>
    
    <td>
        <strong>Description</strong>
    </td>
    
    
    </tr>
        <xsl:apply-templates/>
   </table>
  </xsl:template>
  
  
  <xsl:template match="component">
    <form method="post" action="shop">
    <tr bgcolor="#E8E8E8" >
        <td>
            <xsl:value-of select="name"/>
        </td>
        
        <td>
            <xsl:value-of select="description"/>
        </td>
        

    </tr>
  
        
    <xsl:element name="input"> <!--A ordinary input in XSLT-->
      <xsl:attribute name="type">hidden</xsl:attribute>
      <xsl:attribute name="value"><xsl:value-of select="id"/></xsl:attribute>
      <xsl:attribute name="name">compid</xsl:attribute>
    </xsl:element>
    
    <input type="hidden" name="action" value="add"/>
   </form>
  </xsl:template>
  
</xsl:stylesheet>
  