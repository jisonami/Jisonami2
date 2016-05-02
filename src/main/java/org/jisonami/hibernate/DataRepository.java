package org.jisonami.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;

@Service
public class DataRepository implements Repository{

	private Session initSession(boolean transactionFlag) {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure().build();
		Metadata metadata = new MetadataSources(standardRegistry)
				.getMetadataBuilder()
				.applyImplicitNamingStrategy(
						ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
				.build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		if (transactionFlag) {
			session.beginTransaction();
		}
		return session;
	}

	private boolean closeSession(Session session, boolean transactionFlag) {
		if (transactionFlag) {
			Transaction transaction = session.getTransaction();
			transaction.commit();
		}
		SessionFactory sessionFactory = session.getSessionFactory();
		session.close();
		sessionFactory.close();
		return true;
	}

	public String add(Object o) {
		Session session = initSession(true);
		String id = (String) session.save(o);
		closeSession(session, true);
		return id;
	}

	public boolean edit(Object o) {
		Session session = initSession(true);
		session.update(o);
		closeSession(session, true);
		return true;
	}

	public boolean delete(Object o) {
		Session session = initSession(true);
		session.delete(o);
		closeSession(session, true);
		return true;
	}
	
	public <T> boolean delete(Class<T> clazz, String id){
		return delete(queryOne(clazz, id));
	}

	public <T> T queryOne(Class<T> clazz, String id) {
		Session session = initSession(false);
		T t = (T) session.get(clazz, id);
		closeSession(session, false);
		return t;
	}

	public <T> List<T> query(String hql) {
		Session session = initSession(false);
		@SuppressWarnings("unchecked")
		List<T> list = session.createQuery(hql).list();
		closeSession(session, false);
		return list;
	}

	public <T> List<T> query(String hql, Map<String, Object> queryParam) {
		Session session = initSession(false);
		Query query = session.createQuery(hql);
		for (String key : queryParam.keySet()) {
			query.setParameter(key, queryParam.get(key));
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		closeSession(session, false);
		return list;
	}

}
