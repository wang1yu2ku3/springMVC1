package com.wyl.test.util;

import com.wyl.test.entity.Dict;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Demo {
	
	private static ApplicationContext ctx;
	
	static 
    {  
		ctx = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
    }
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		SessionFactory factory =  (SessionFactory)ctx.getBean("sessionFactory");
		Session session = factory.openSession();
		
		String hql = "FROM Dict as d WHERE d.word like :word";
		String word = "ci";
		Query q = session.createQuery(hql);
		q.setString("word", word+"%");
		//List<Dict> result = (List<Dict>) session.createQuery(hql).list();
		
		List<Dict> result = q.list();
		
		for(int i=0;i<result.size();i++){
			Dict d = result.get(i);
			System.out.print(d.getWord() + " : ");
			System.out.println(d.getMeaning());
		}
		
	}
	
}
