/*     */ package com.zakoc.visitortracker.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ public class Visitor
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.AUTO)
/*     */   private Long id;
/*     */   private Double longitude;
/*     */   private Double latitude;
/*     */   private String ipAddress;
/*     */   private String name;
/*     */   private String email;
/*     */   private String mobile;
/*     */   
/*     */   public Long getId()
/*     */   {
/*  36 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  40 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Double getLongitude() {
/*  44 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(Double longitude) {
/*  48 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public Double getLatitude() {
/*  52 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(Double latitude) {
/*  56 */     this.latitude = latitude;
/*     */   }
/*     */   
/*     */   public String getIpAddress() {
/*  60 */     return this.ipAddress;
/*     */   }
/*     */   
/*     */   public void setIpAddress(String ipAddress) {
/*  64 */     this.ipAddress = ipAddress;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  68 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  72 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/*  76 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/*  80 */     this.email = email;
/*     */   }
/*     */   
/*     */   public String getMobile() {
/*  84 */     return this.mobile;
/*     */   }
/*     */   
/*     */   public void setMobile(String mobile) {
/*  88 */     this.mobile = mobile;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/*  93 */     int hash = 0;
/*  94 */     hash += (this.id != null ? this.id.hashCode() : 0);
/*  95 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 101 */     if (!(object instanceof Visitor)) {
/* 102 */       return false;
/*     */     }
/* 104 */     Visitor other = (Visitor)object;
/* 105 */     if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
/* 106 */       return false;
/*     */     }
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 113 */     return "com.zakoc.visitortracker.entities.Visitor[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\oheo\Downloads\VisitorTracker\WEB-INF\classes\!\com\zakoc\visitortracker\entities\Visitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */