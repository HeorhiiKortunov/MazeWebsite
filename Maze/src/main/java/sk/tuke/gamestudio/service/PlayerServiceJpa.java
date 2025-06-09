package sk.tuke.gamestudio.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.entity.Player;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Primary
@Transactional
public class PlayerServiceJpa implements PlayerService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addPlayer(Player player) {
        em.persist(player);
    }

    @Override
    public Player getPlayerByName(String name) {
        try {
            return em.createQuery(
                    "SELECT p FROM Player p WHERE p.name = :name", Player.class)
                .setParameter("name", name)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void reset() {
        em.createQuery("DELETE FROM Player").executeUpdate();
    }
}
