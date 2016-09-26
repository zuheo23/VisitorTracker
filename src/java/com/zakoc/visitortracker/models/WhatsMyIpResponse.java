/*    */ package com.zakoc.visitortracker.models;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @XmlRootElement
/*    */ public class WhatsMyIpResponse
/*    */ {
/*    */   private String ipAddress;
/*    */   private String host;
/*    */   private int port;
/*    */   
/*    */   @XmlElement
/*    */   public String getIpAddress()
/*    */   {
/* 23 */     return this.ipAddress;
/*    */   }
/*    */   
/*    */   public void setIpAddress(String ipAddress) {
/* 27 */     this.ipAddress = ipAddress;
/*    */   }
/*    */   
/*    */   @XmlElement
/*    */   public int getPort() {
/* 32 */     return this.port;
/*    */   }
/*    */   
/*    */   public void setPort(int port) {
/* 36 */     this.port = port;
/*    */   }
/*    */   
/*    */   @XmlElement
/*    */   public String getHost() {
/* 41 */     return this.host;
/*    */   }
/*    */   
/*    */   public void setHost(String host) {
/* 45 */     this.host = host;
/*    */   }
/*    */ }


/* Location:              C:\Users\oheo\Downloads\VisitorTracker\WEB-INF\classes\!\com\zakoc\visitortracker\models\WhatsMyIpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */