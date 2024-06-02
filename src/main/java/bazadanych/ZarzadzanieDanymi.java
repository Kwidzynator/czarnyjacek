package bazadanych;

import jakarta.persistence.*;

public class ZarzadzanieDanymi {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("MyUnit");
    public ZarzadzanieDanymi() {
    }
    public void dodanie(String l, String h){
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Loginy loginy = new Loginy();
        loginy.setLogin(l);
        loginy.setHaslo(h);
        entityManager.merge(loginy);
        transaction.commit();
    }

    //ai
    public boolean sprawdzenie(String l, String h) {
        EntityManager entityManager = FACTORY.createEntityManager();
        try {
            TypedQuery<Loginy> query = entityManager.createQuery(
                    "SELECT l FROM Loginy l WHERE l.login = :login AND l.haslo = :haslo", Loginy.class);
            query.setParameter("login", l);
            query.setParameter("haslo", h);

            Loginy loginy = query.getSingleResult();
            return loginy != null;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
    }
}
