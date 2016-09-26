/*    */ package com.zakoc.websocket;
/*    */ 
/*    */ import com.zakoc.visitortracker.entities.Visitor;
/*    */ import java.io.StringReader;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.enterprise.context.ApplicationScoped;
/*    */ import javax.inject.Inject;
/*    */ import javax.json.Json;
/*    */ import javax.json.JsonNumber;
/*    */ import javax.json.JsonObject;
/*    */ import javax.json.JsonReader;
/*    */ import javax.websocket.OnClose;
/*    */ import javax.websocket.OnError;
/*    */ import javax.websocket.OnMessage;
/*    */ import javax.websocket.OnOpen;
/*    */ import javax.websocket.Session;
/*    */ import javax.websocket.server.ServerEndpoint;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ApplicationScoped
/*    */ @ServerEndpoint("/actions")
/*    */ public class VisitorWebSocketServer
/*    */ {
/*    */   @Inject
/*    */   private VisitorSessionHandler sessionHandler;
/*    */   
/*    */   @OnOpen
/*    */   public void open(Session session)
/*    */   {
/* 37 */     Logger.getLogger(VisitorWebSocketServer.class.getName()).log(Level.SEVERE, "ONOPEN");
/* 38 */     this.sessionHandler.addSession(session);
/*    */   }
/*    */   
/*    */   @OnError
/*    */   public void onError(Throwable error) {
/* 43 */     Logger.getLogger(VisitorWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
/*    */   }
/*    */   
/*    */   @OnClose
/*    */   public void close(Session session) {
/* 48 */     Logger.getLogger(VisitorWebSocketServer.class.getName()).log(Level.SEVERE, "ONCLOSE");
/* 49 */     this.sessionHandler.removeSession(session);
/*    */   }
/*    */   
/*    */   @OnMessage
/*    */   public void handleMessage(String message, Session session) {
/* 54 */     JsonReader reader = Json.createReader(new StringReader(message));Throwable localThrowable3 = null;
/* 55 */     try { JsonObject jsonMessage = reader.readObject();
/*    */       
/* 57 */       if ("add".equals(jsonMessage.getString("action"))) {
/* 58 */         Visitor visitor = new Visitor();
/* 59 */         visitor.setName(jsonMessage.getString("name"));
/* 60 */         visitor.setIpAddress(jsonMessage.getString("ipAddress"));
/* 61 */         visitor.setMobile(jsonMessage.getString("mobile"));
/* 62 */         visitor.setEmail(jsonMessage.getString("email"));
/* 63 */         visitor.setLongitude(Double.valueOf(jsonMessage.getJsonNumber("longitude").doubleValue()));
/* 64 */         visitor.setLatitude(Double.valueOf(jsonMessage.getJsonNumber("latitude").doubleValue()));
/* 65 */         this.sessionHandler.addVisitor(session, visitor);
/*    */       }
/*    */       
/* 68 */       if ("remove".equals(jsonMessage.getString("action"))) {
/* 69 */         String name = jsonMessage.getString("name");
/* 70 */         Logger.getLogger(VisitorWebSocketServer.class.getName()).log(Level.SEVERE, "HANDLEMESSAGEREMOVE " + name);
/* 71 */         this.sessionHandler.removeVisitorByName(name);
/*    */       }
/*    */     }
/*    */     catch (Throwable localThrowable1)
/*    */     {
/* 54 */       localThrowable3 = localThrowable1;throw localThrowable1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     }
/*    */     finally
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 73 */       if (reader != null) if (localThrowable3 != null) try { reader.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else reader.close();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\oheo\Downloads\VisitorTracker\WEB-INF\classes\!\com\zakoc\websocket\VisitorWebSocketServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */