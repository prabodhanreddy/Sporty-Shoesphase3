package com.example.demo.repository;


	
	import java.util.List;

	import org.hibernate.Query;
	import org.hibernate.Session;
	import org.hibernate.Transaction;
	import com.example.demo.model.Admin;
	import com.example.demo.repository.AdminRepository;
	import org.springframework.orm.hibernate5.HibernateTemplate;



	@SuppressWarnings("deprecation")
	public class AdminRepository {

		
		private HibernateTemplate hibernateTemplate;

		public HibernateTemplate getHibernateTemplate() {
			return hibernateTemplate;
		}

		public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
			this.hibernateTemplate = hibernateTemplate;
		}

		
		public boolean verifyAdmin(Admin a) {
			Session session=hibernateTemplate.getSessionFactory().openSession();
			Transaction transaction=session.beginTransaction();
			
			String hql = "from Admin where email=:email and password=:password";
			Query<Admin> query = session.createQuery(hql,Admin.class);
			query.setParameter("email", a.getEmail());
			query.setParameter("password", a.getPassword());
			List<Admin> results = query.getResultList();
			transaction.commit();
			session.close();
			
			return results.size()>0;	
		}
		
	}


