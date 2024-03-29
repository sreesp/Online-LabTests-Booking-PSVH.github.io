package com.psvh.db.doa;

 

import java.sql.SQLException;

 

import org.hibernate.Query;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


import com.psvh.db.pojo.User;

 

 

public class UserDAO {

 

           public String insert(User u) {

                      try {

                    	  Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class);
                  		
                  		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
                  		
                  		SessionFactory sf = cfg.buildSessionFactory(sr);

 

                      Session s = sf.openSession();

                      s.beginTransaction();

                      s.save(u);

                      s.getTransaction().commit();

                      return "registered successfully";

                      }

                      catch(ConstraintViolationException e) {

                                 return "Exception";

                      }

 

           }

          

                      public String findPassword(String username) {

                     

                    	  Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class);
                    		
                    		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
                    		
                    		SessionFactory sf = cfg.buildSessionFactory(sr);

                                

                                 Session s = sf.openSession();

                                 s.beginTransaction();

          

                                 Query q = s.createQuery("from User where username =:i");

                                 q.setParameter("i", username);

                                 Object obj = (Object) q.list().get(0);

          

                                 s.getTransaction().commit();

                                 String pw = null;

                                 if (obj != null) {

                                            User u = (User) obj;

                                            pw = u.getPassword();

                                 }

                                 return pw;

 

           }

 

}