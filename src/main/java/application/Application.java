package application;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.EventoDAO;
import entities.Evento;
import entities.TipoEvento;
import utils.JpaUtil;

public class Application {
	public static Logger logger = LoggerFactory.getLogger(Application.class);
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EventoDAO eventoDao = new EventoDAO(em);

		Evento evento1 = new Evento("festa di compleanno", LocalDate.of(2023, 05, 17), "compleanno di Giovanni",
				TipoEvento.PRIVATO, 20);
		Evento evento2 = new Evento("festa di compleanno", LocalDate.of(2023, 05, 17), "compleanno di Maria",
				TipoEvento.PRIVATO, 30);
		eventoDao.save(evento1);
		eventoDao.save(evento2);

		eventoDao.getById(evento1.getId());
		eventoDao.delete(evento2.getId());
		eventoDao.refresh(evento1.getId(), "comunione");

		em.close();
		emf.close();

	}

}
