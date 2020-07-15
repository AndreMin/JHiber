package mypack;

import mypack.entity.Doggy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class DoggyQuery {
    private SessionFactory factory;
    private Session session;

    public DoggyQuery() {
        factory = HibernateUtil.getFactory();
    }

    public Doggy getByid(int id) {
        session = factory.openSession();
        Doggy dog = session.get(Doggy.class, id);
        session.close();
        return dog;
    }

    public List<Doggy> getAll() {

        session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Doggy.class);
        Root<Doggy> root = cq.from(Doggy.class);
//        cq.where(cb.equal(root.<String>get("name"), "Agafosha"));   where condition

//        Selection[] ses = {root.get("id"), root.get("name")};
//        cq.select(cb.construct(Doggy.class,ses)).where(cb.like(root.<String>get("name"),"%s%"));
//        cq.select(root); esli hotim vse polja
        Query query = session.createQuery(cq);
        List<Doggy> list = query.getResultList();
        session.close();
        return list;
    }

    public Doggy addDog(Doggy dog) {
        session = factory.openSession();
        session.beginTransaction();
        session.save(dog);
        session.getTransaction().commit();
        session.close();
        return dog;

    }

    public void addDogOne(int age, String name) {
        session = factory.openSession();
        session.beginTransaction();
        Doggy dog = new Doggy();
        dog.setAge(age);
        dog.setName(name);
        session.save(dog);
        session.getTransaction().commit();
        session.close();
    }

    public void updateDog(int id,int age, String name) {
        session = factory.openSession();
        session.beginTransaction();
        Doggy dog = session.get(Doggy.class,id);
        dog.setAge(age);
        dog.setName(name);
        session.save(dog);
        session.getTransaction().commit();
        session.close();
    }


    public void delteById(int id) {
        session = factory.openSession();
        session.beginTransaction();
        Doggy dog = session.get(Doggy.class, id);
        session.delete(dog);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteByCriteria(Doggy name) {
        session = factory.openSession();
        session.getTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<Doggy> cd = cb.createCriteriaDelete(Doggy.class);
        Root<Doggy> root = cd.from(Doggy.class);
        cd.where(cb.like(root.<String>get("name"), "%ers%"));

          /*cd.where(cb.or(
                cb.and(
                        cb.like(root.<String>get("name"), "%ara%"),
                        cb.like(root.<String>get("secondName"), "%ush%")
                ),
                cb.equal(root.<String>get("secondName"), "Shevchenko")
                )
        );*/


        Query query = session.createQuery(cd);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
}
