package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import application.Application;
import entities.Evento;

public class EventoDAO {
	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;

	}

	public EntityManager getEm() {
		return em;
	}

	public void save(Evento e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();

	}

	public Evento getById(UUID id) {
		Evento found = em.find(Evento.class, id);
		System.out.println(found.toString());
		return found;

	}

	public void delete(UUID id) {
		Evento found = em.find(Evento.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();

		}

	}

	public void refresh(UUID id, String titolo) {
		Evento found = em.find(Evento.class, id);
		found.setTitolo(titolo);
		Application.logger.info("Prerefresh" + found);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.refresh(found);
		transaction.commit();
		Application.logger.info("" + found);
	}

}
