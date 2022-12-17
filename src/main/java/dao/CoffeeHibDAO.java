package dao;

import jakarta.inject.Inject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import model.Coffee;

import java.util.List;

@Log4j2
public class CoffeeHibDAO {
    private JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public CoffeeHibDAO(JPAUtil hu){
        this.jpautil =hu;
    }

    public List<Coffee> getAll() {
        List<Coffee> list = null;
        try {
            em = jpautil.getEntityManager();
            list = em.createQuery("from Coffee", Coffee.class).getResultList();
        }
        finally {
            if (em != null)  em.close();
        }

        return list;
    }

    public Coffee get(String name) {
        Coffee coffee;
        em = jpautil.getEntityManager();
        try {
            coffee = em
                    .createNamedQuery( "hql_get_coffee_by_name", Coffee.class )
                .setParameter( "name", name )
                .getSingleResult();
    }
        finally {
            if (em != null)  em.close();
    }

        return coffee;
}

    public void add(Coffee coffee) {

        //With cascade.PERSIST

        em = jpautil.getEntityManager();
        EntityTransaction tx=null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(coffee);
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
        finally {
            if (em != null)  em.close();
        }
    }

    public void delete(Coffee coffee) {
        //Set cascade to coffee.EncriptedCode
        em = jpautil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //Reattach the object before removing
            em.remove(em.merge(coffee));
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            //Add log for Hibernate
        }
        finally {
            if (em != null)  em.close();
        }
    }
}
