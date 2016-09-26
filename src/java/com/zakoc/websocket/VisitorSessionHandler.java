/*     */ package com.zakoc.websocket;
/*     */ 
/*     */ import com.zakoc.visitortracker.entities.Visitor;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.enterprise.context.ApplicationScoped;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonObjectBuilder;
/*     */ import javax.json.spi.JsonProvider;
/*     */ import javax.websocket.RemoteEndpoint.Basic;
/*     */ import javax.websocket.Session;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ApplicationScoped
/*     */ public class VisitorSessionHandler
/*     */ {
/*  30 */   private final HashMap<Session, Visitor> map = new HashMap();
/*     */   
/*     */   public void addSession(Session session) {
/*  33 */     Logger.getLogger(VisitorSessionHandler.class.getName()).log(Level.SEVERE, "NEW SESSION WITH NO VISITOR");
/*  34 */     this.map.put(session, null);
/*  35 */     for (Map.Entry<Session, Visitor> entry : this.map.entrySet()) {
/*  36 */       if (entry.getValue() != null) {
/*  37 */         System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
/*  38 */         JsonObject addMessage = createAddMessage((Visitor)entry.getValue());
/*  39 */         sendToSession(session, addMessage);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeSession(Session session) {
/*  45 */     Visitor visitor = (Visitor)this.map.get(session);
/*  46 */     JsonObject removeMessage = null;
/*  47 */     if (visitor != null) {
/*  48 */       removeMessage = createRemoveMessage(visitor);
/*     */     }
/*  50 */     if (removeMessage != null) {
/*  51 */       sendToAllConnectedSessions(removeMessage);
/*     */     }
/*  53 */     this.map.remove(session);
/*     */   }
/*     */   
/*     */   public List getVisitors() {
/*  57 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addVisitor(Session session, Visitor visitor)
/*     */   {
/*  63 */     Visitor existingVisitor = getVisitorByName(visitor.getName());
/*  64 */     this.map.put(session, visitor);
/*     */     
/*  66 */     JsonObject addMessage = createAddMessage(visitor);
/*  67 */     sendToAllConnectedSessions(addMessage);
/*     */   }
/*     */   
/*     */   public void removeVisitorByName(String name)
/*     */   {
/*  72 */     Visitor visitor = getVisitorByName(name);
/*  73 */     JsonObject removeMessage = null;
/*  74 */     if (visitor != null) {
/*  75 */       removeMessage = createRemoveMessage(visitor);
/*     */     }
/*  77 */     Session session = getSessionByVisitorName(name);
/*     */     try {
/*  79 */       session.close();
/*     */     } catch (Exception e) {
/*  81 */       e.printStackTrace();
/*     */     }
/*     */     
/*  84 */     if (removeMessage != null) {
/*  85 */       sendToAllConnectedSessions(removeMessage);
/*     */     }
/*  87 */     this.map.remove(session);
/*     */     
/*  89 */     Logger.getLogger(VisitorSessionHandler.class.getName()).log(Level.SEVERE, "VISITORS LEFT " + this.map.size());
/*     */   }
/*     */   
/*     */   public Session getSessionByVisitorName(String name)
/*     */   {
/*  94 */     for (Map.Entry<Session, Visitor> entry : this.map.entrySet()) {
/*  95 */       if (((Visitor)entry.getValue()).getName() == name) {
/*  96 */         return (Session)entry.getKey();
/*     */       }
/*     */     }
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   public Visitor getVisitorByName(String name) {
/* 103 */     Visitor visitor = null;
/* 104 */     for (Map.Entry<Session, Visitor> entry : this.map.entrySet()) {
/*     */       try {
/* 106 */         visitor = (Visitor)entry.getValue();
/*     */         
/*     */ 
/* 109 */         if (visitor.getName().equals(name)) {
/* 110 */           return (Visitor)entry.getValue();
/*     */         }
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */     
/* 116 */     return visitor;
/*     */   }
/*     */   
/*     */   private JsonObject createAddMessage(Visitor visitor) {
/* 120 */     JsonProvider provider = JsonProvider.provider();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 128 */     JsonObject addMessage = provider.createObjectBuilder().add("action", "add").add("name", visitor.getName()).add("latitude", visitor.getLatitude().doubleValue()).add("longitude", visitor.getLongitude().doubleValue()).add("email", visitor.getEmail()).add("mobile", visitor.getMobile()).build();
/* 129 */     return addMessage;
/*     */   }
/*     */   
/*     */   private JsonObject createRemoveMessage(Visitor visitor) {
/*     */     try {
/* 134 */       JsonProvider provider = JsonProvider.provider();
/*     */       
/*     */ 
/*     */ 
/* 138 */       JsonObject removeMessage = provider.createObjectBuilder().add("action", "remove").add("name", visitor.getName()).build();
/*     */       
/* 140 */       Logger.getLogger(VisitorSessionHandler.class.getName()).log(Level.SEVERE, "CREATEREMOVEMESSAGE ");
/* 141 */       return removeMessage;
/*     */     } catch (Exception e) {
/* 143 */       e.printStackTrace();
/*     */     }
/* 145 */     return null;
/*     */   }
/*     */   
/*     */   private void sendToAllConnectedSessions(JsonObject message) {
/* 149 */     for (Map.Entry<Session, Visitor> entry : this.map.entrySet()) {
/* 150 */       sendToSession((Session)entry.getKey(), message);
/*     */     }
/*     */   }
/*     */   
/*     */   private void sendToSession(Session session, JsonObject message) {
/*     */     try {
/* 156 */       session.getBasicRemote().sendText(message.toString());
/*     */     }
/*     */     catch (IOException ex) {
/* 159 */       Logger.getLogger(VisitorSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\oheo\Downloads\VisitorTracker\WEB-INF\classes\!\com\zakoc\websocket\VisitorSessionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */