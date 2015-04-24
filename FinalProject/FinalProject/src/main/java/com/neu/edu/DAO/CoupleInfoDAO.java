package com.neu.edu.DAO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.neu.edu.Model.Activities;
import com.neu.edu.Model.CoupleInfo;
import com.neu.edu.Model.CoupleSignUp;
import com.neu.edu.Model.Person;

public class CoupleInfoDAO extends DAO{

	public List<Activities> retreiveActivity(String[] actSel) throws HibernateException{
		List<Activities> actList  = new ArrayList<Activities>();
		try {
			System.out.println("Entered the method");
			int length = actSel.length;
			System.out.println("checking the length of activity inside the method "+ length);
			for(int i=0; i<length; i++){
				Session session = getSession();
				Query query = session.createQuery("from Activities where activityName =:name");
				query.setString("name", actSel[i]);
				Activities activities = (Activities) query.uniqueResult();
				actList.add(activities);
				session.close();
			}
		} catch (HibernateException e) {
			// TODO: handle exception
		}

		return actList;
	}



	public CoupleInfo addCoupleInfo(Person person1,Person person2, CoupleInfo coupleInfo, CoupleSignUp csu,ArrayList<Activities> activitiesList) throws HibernateException{
		CoupleInfo cinfo = csu.getCoupleInfo();
		Session session = getSession();
		Transaction tx = session.beginTransaction();

		//CoupleInfo coupleInfo1 = (CoupleInfo) session.get(CoupleInfo.class,coupleId);
		if(cinfo==null){
			List<Person> personList = new ArrayList<Person>();
			person1.setCoupleInfo(coupleInfo);
			person2.setCoupleInfo(coupleInfo);
			personList.add(person1);
			personList.add(person2);
			coupleInfo.setPersonList(personList);
			coupleInfo.setActivityList(activitiesList);
			csu.setCoupleInfo(coupleInfo);
			session.save(coupleInfo);
			session.update(csu);
			tx.commit();
			session.close();
			return coupleInfo;
		} else {   // can use copy construtor to copy the data from existing object
			String story1 = coupleInfo.getStory();
			String lookingfor1 = coupleInfo.getLookingfor();
			String location1 = coupleInfo.getLocation();
			Person personA = cinfo.getPersonList().get(0);
			Person personB = cinfo.getPersonList().get(1);
			personA.setAge(person1.getAge());
			personA.setEmailId(person1.getEmailId());
			personA.setFirstName(person1.getFirstName());
			personA.setLastName(person1.getLastName());
			personA.setHometown(person1.getHometown());
			personA.setOccupation(person1.getOccupation());
			personA.setPhone(person1.getPhone());

			// entering for person 2
			personB.setAge(person2.getAge());
			personB.setEmailId(person2.getEmailId());
			personB.setFirstName(person2.getFirstName());
			personB.setLastName(person2.getLastName());
			personB.setHometown(person2.getHometown());
			personB.setOccupation(person2.getOccupation());
			personB.setPhone(person2.getPhone());
			cinfo.getPersonList().add(0, personA);
			cinfo.getPersonList().add(1, personB);
			cinfo.setLocation(location1);
			cinfo.setLookingfor(lookingfor1);
			cinfo.setStory(story1);
			cinfo.setActivityList(activitiesList);
			session.update(cinfo);
			session.update(personA);
			session.update(personB);
			tx.commit();
			session.close();
			return cinfo;
		}
	}

	public CoupleSignUp fetchViewCouple(String coupleName){

		Session session = getSession();
		Query query = session.createQuery("from CoupleSignUp where coupleName =:coupleName");
		query.setString("coupleName", coupleName);
		CoupleSignUp coupleSignUp =  (CoupleSignUp) query.uniqueResult();
		session.close();
		return coupleSignUp;
	}




	//	public ArrayList<CoupleSignUp> fetchViewCoupleList(CoupleInfo[] coupleInfo){
	//		ArrayList<CoupleSignUp> coupleSignList = new ArrayList<CoupleSignUp>();
	//		Session session = getSession();
	//		for(int i=0; i<coupleSignList.size(); i++){
	//		Query query = session.createQuery("from CoupleSignUp where coupleName =:coupleName");
	//		query.setString("coupleName", coupleName);
	//		CoupleSignUp coupleSignUp =  (CoupleSignUp) query.uniqueResult();
	//		session.close();
	//		return coupleSignUp;
	//	}

}
