/*    */ package com.zakoc.visitortracker.services;
/*    */ 
/*    */ import com.zakoc.visitortracker.entities.Visitor;
/*    */ import java.util.List;
/*    */ import javax.ejb.Stateless;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.PersistenceContext;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.DELETE;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.PUT;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.PathParam;
/*    */ import javax.ws.rs.Produces;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Stateless
/*    */ @Path("visitor")
/*    */ public class VisitorFacadeREST
/*    */   extends AbstractFacade<Visitor>
/*    */ {
/*    */   @PersistenceContext(unitName="VisitorTrackerPU")
/*    */   private EntityManager em;
/*    */   
/*    */   public VisitorFacadeREST()
/*    */   {
/* 35 */     super(Visitor.class);
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Consumes({"application/xml", "application/json"})
/*    */   public void create(Visitor entity)
/*    */   {
/* 42 */     super.create(entity);
/*    */   }
/*    */   
/*    */   @PUT
/*    */   @Path("{id}")
/*    */   @Consumes({"application/xml", "application/json"})
/*    */   public void edit(@PathParam("id") Long id, Visitor entity) {
/* 49 */     super.edit(entity);
/*    */   }
/*    */   
/*    */   @DELETE
/*    */   @Path("{id}")
/*    */   public void remove(@PathParam("id") Long id) {
/* 55 */     super.remove(super.find(id));
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Path("{id}")
/*    */   @Produces({"application/xml", "application/json"})
/*    */   public Visitor find(@PathParam("id") Long id) {
/* 62 */     return (Visitor)super.find(id);
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/xml", "application/json"})
/*    */   public List<Visitor> findAll()
/*    */   {
/* 69 */     return super.findAll();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Path("{from}/{to}")
/*    */   @Produces({"application/xml", "application/json"})
/*    */   public List<Visitor> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
/* 76 */     return super.findRange(new int[] { from.intValue(), to.intValue() });
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Path("count")
/*    */   @Produces({"text/plain"})
/*    */   public String countREST() {
/* 83 */     return String.valueOf(super.count());
/*    */   }
/*    */   
/*    */   protected EntityManager getEntityManager()
/*    */   {
/* 88 */     return this.em;
/*    */   }
/*    */ }


/* Location:              C:\Users\oheo\Downloads\VisitorTracker\WEB-INF\classes\!\com\zakoc\visitortracker\services\VisitorFacadeREST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */