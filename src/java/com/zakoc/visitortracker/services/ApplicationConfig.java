/*    */ package com.zakoc.visitortracker.services;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.ws.rs.ApplicationPath;
/*    */ import javax.ws.rs.core.Application;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ApplicationPath("api")
/*    */ public class ApplicationConfig
/*    */   extends Application
/*    */ {
/*    */   public Set<Class<?>> getClasses()
/*    */   {
/* 20 */     Set<Class<?>> resources = new HashSet();
/* 21 */     addRestResourceClasses(resources);
/* 22 */     return resources;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void addRestResourceClasses(Set<Class<?>> resources)
/*    */   {
/* 32 */     resources.add(com.zakoc.visitortracker.services.VisitorFacadeREST.class);
/* 33 */
 resources.add(com.zakoc.visitortracker.services.WhatsMyIp.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\oheo\Downloads\VisitorTracker\WEB-INF\classes\!\com\zakoc\visitortracker\services\ApplicationConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */