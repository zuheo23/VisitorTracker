/*    */ package com.zakoc.visitortracker.services;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.Query;
/*    */ import javax.persistence.TypedQuery;
/*    */ import javax.persistence.criteria.CriteriaBuilder;
/*    */ import javax.persistence.criteria.CriteriaQuery;
/*    */ import javax.persistence.criteria.Root;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractFacade<T>
/*    */ {
/*    */   private Class<T> entityClass;
/*    */   
/*    */   public AbstractFacade(Class<T> entityClass)
/*    */   {
/* 20 */     this.entityClass = entityClass;
/*    */   }
/*    */   
/*    */   protected abstract EntityManager getEntityManager();
/*    */   
/*    */   public void create(T entity) {
/* 26 */     getEntityManager().persist(entity);
/*    */   }
/*    */   
/*    */   public void edit(T entity) {
/* 30 */     getEntityManager().merge(entity);
/*    */   }
/*    */   
/*    */   public void remove(T entity) {
/* 34 */     getEntityManager().remove(getEntityManager().merge(entity));
/*    */   }
/*    */   
/*    */   public T find(Object id) {
/* 38 */     return (T)getEntityManager().find(this.entityClass, id);
/*    */   }
/*    */   
/*    */   public List<T> findAll() {
/* 42 */     CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
/* 43 */     cq.select(cq.from(this.entityClass));
/* 44 */     return getEntityManager().createQuery(cq).getResultList();
/*    */   }
/*    */   
/*    */   public List<T> findRange(int[] range) {
/* 48 */     CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
/* 49 */     cq.select(cq.from(this.entityClass));
/* 50 */     Query q = getEntityManager().createQuery(cq);
/* 51 */     q.setMaxResults(range[1] - range[0] + 1);
/* 52 */     q.setFirstResult(range[0]);
/* 53 */     return q.getResultList();
/*    */   }
/*    */   
/*    */   public int count() {
/* 57 */     CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
/* 58 */     Root<T> rt = cq.from(this.entityClass);
/* 59 */     cq.select(getEntityManager().getCriteriaBuilder().count(rt));
/* 60 */     Query q = getEntityManager().createQuery(cq);
/* 61 */     return ((Long)q.getSingleResult()).intValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\oheo\Downloads\VisitorTracker\WEB-INF\classes\!\com\zakoc\visitortracker\services\AbstractFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */