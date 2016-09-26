/*    */ package com.zakoc.visitortracker.services;
/*    */ 
/*    */ import com.zakoc.visitortracker.models.WhatsMyIpResponse;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.core.Context;
/*    */ import javax.ws.rs.core.UriInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Path("whatsmyip")
/*    */ public class WhatsMyIp
/*    */ {
/*    */   @Context
/*    */   private UriInfo context;
/* 29 */   private static final Logger LOGGER = Logger.getLogger(WhatsMyIp.class.getName());
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @GET
/*    */   @Produces({"application/json", "application/xml"})
/*    */   public WhatsMyIpResponse getMyIp(@Context HttpServletRequest request)
/*    */   {
/* 47 */     WhatsMyIpResponse w = new WhatsMyIpResponse();
/* 48 */     LOGGER.setLevel(Level.SEVERE);
/* 49 */     w.setIpAddress(request.getRemoteAddr());
/* 50 */     LOGGER.severe("Remote-IP:" + request.getRemoteAddr());
/* 51 */     w.setHost(request.getRemoteHost());
/* 52 */     LOGGER.severe("Remote-Host:" + request.getRemoteHost());
/* 53 */     w.setPort(request.getRemotePort());
/* 54 */     LOGGER.severe("Remote-Port:" + request.getRemotePort());
/* 55 */     return w;
/*    */   }
/*    */ }


/* Location:              C:\Users\oheo\Downloads\VisitorTracker\WEB-INF\classes\!\com\zakoc\visitortracker\services\WhatsMyIp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */