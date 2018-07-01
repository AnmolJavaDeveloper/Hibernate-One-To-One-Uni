package com.hibernate.demo;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction(); 
			
			//get the instructor detail object
			 int theId=3;
			 InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			 
			
			
			//print the Instructor detail
		   System.out.println("tempInstructorDetails: " + tempInstructorDetail);
			//print the associated instructor
		   System.out.println("the assoicated instructor " 
			+tempInstructorDetail.getInstructor());
		   
		   //now let's delete the instructor detail
		   System.out.println("Deleting tempInstructorDetail: " +tempInstructorDetail);
		   
		   //**remove the associated object reference
		   tempInstructorDetail.getInstructor().setInstructorDetail(null);
	     	   
		   session.delete(tempInstructorDetail);
		   
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
             
		
		} 
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
               // handle connection leak issue
			session.close();
			factory.close();
		}

	}

}
