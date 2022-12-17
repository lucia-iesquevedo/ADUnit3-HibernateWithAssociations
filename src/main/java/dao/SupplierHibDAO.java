package dao;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Product;
import model.Supplier;
import org.hibernate.Session;

import java.util.List;

public class SupplierHibDAO {
    private JPAUtil hu;
    private EntityManager em;

    @Inject
    public SupplierHibDAO(JPAUtil hu){
        this.hu=hu;
    }

    public List<Supplier> getAll() {
        List<Supplier> list = null;
        try {
            em = hu.getEntityManager();
            list = em.createQuery("from Supplier", Supplier.class).getResultList();
            list.forEach(e -> e.getCoffees().size());
        }
        finally {
            em.close();
        }

        return list;
    }

    //Can also be done with queries - See Coffee
    public Supplier get(int id) {
        Supplier s=null;

        em = hu.getEntityManager();

        try {
            //s = em.find(Supplier.class,id);

            // Hibernate session uses get instead of find
            Session session= em.unwrap(Session.class);
            s = session.get(Supplier.class,id);

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (em != null)  em.close();
        }
        return s;
    }


    public void add(Supplier supp) {
        em = hu.getEntityManager();
        EntityTransaction tx=null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(supp);
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        finally {
                if (em != null)  em.close();
            }
    }



    public void delete(Supplier supp) {
        em = hu.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //Reattach the object before removing
            em.remove(em.merge(supp));
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        finally {
            if (em != null)  em.close();
        }
    }

    public void update(Supplier supp) {
        em = hu.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(supp);
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        finally {
            if (em != null)  em.close();
        }
    }
}
