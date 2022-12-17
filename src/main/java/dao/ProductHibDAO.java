package dao;

import jakarta.inject.Inject;
import jakarta.persistence.*;
import model.Product;
import org.hibernate.Session;

import java.util.List;

public class ProductHibDAO {
    private JPAUtil hu;
    private EntityManager em;

    @Inject
    public ProductHibDAO(JPAUtil hu){
        this.hu=hu;
    }

    public List<Product> getAll() {
        List<Product> list = null;

        em = hu.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
 //           list = em.createQuery("from Product", Product.class).getResultList();

            //Hibernate sessions can be generated from an EntityManager object
            Session s= em.unwrap(Session.class);
            list = s.createSelectionQuery("from Product", Product.class).getResultList();
            tx.commit();
        }
        finally {
            if (tx.isActive()) tx.rollback();
            if (em != null)  em.close();
        }

        return list;
    }
}
