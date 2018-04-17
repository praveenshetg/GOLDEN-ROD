package com.papi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.papi.entity.Conversation;

@Repository
@Transactional
public class SchedulerDaoImpl implements SchedulerDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void addConversation(Conversation Conversation) {
		sessionFactory.getCurrentSession().saveOrUpdate(Conversation);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Conversation> getAllConversation() {

		return sessionFactory.getCurrentSession().createQuery("from Conversation").list();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Conversation> getAllConversationForGroup(Long groupId) {

		return sessionFactory.getCurrentSession().createQuery("from Conversation where group_id=" + groupId).list();
	}

	@Transactional
	@Override
	public void deleteConversation(Long id) {
		Conversation Conversation = (Conversation) sessionFactory.getCurrentSession().load(Conversation.class, id);
		if (null != Conversation) {
			this.sessionFactory.getCurrentSession().delete(Conversation);
		}

	}

	@Transactional
	public Conversation getConversation(Long id) {
		return (Conversation) sessionFactory.getCurrentSession().get(Conversation.class, id);
	}

	@Override
	public Conversation updateConversation(Conversation Conversation) {
		sessionFactory.getCurrentSession().update(Conversation);
		return Conversation;
	}

}
