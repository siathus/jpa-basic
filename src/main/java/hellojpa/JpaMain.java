package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member memberA = new Member(1L, "memberA");
            Member memberB = new Member(2L, "memberB");

            em.persist(memberA);
            em.persist(memberB);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, 1L);
            findMember.setName("updateA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
