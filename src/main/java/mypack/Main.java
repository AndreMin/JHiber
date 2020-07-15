package mypack;

import mypack.entity.Doggy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import org.jboss.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Doggy.class.getName());
    public static void main(String[] args) {

//insert new dog
       /* Doggy dog = new Doggy();
        DoggyQuery doggyQuery = new DoggyQuery();
        dog.setName("Persik");
        dog.setAge(8);
        doggyQuery.addDog(dog);*/

        DoggyQuery doggyQuery = new DoggyQuery();
        Doggy dog = new Doggy();
//        doggyQuery.addDogOne(3, "Pesik");
//        doggyQuery.updateDog(4,10,"Peris");
        List<Doggy> list = doggyQuery.getAll();
        for (Doggy d : list) {
//            System.out.println(d.getAge() + " " + d.getName());
            LOG.warn(d.getName());
        }
        System.out.println("--------------------");
        dog = doggyQuery.getByid(4);
        System.out.println(dog.getAge() + " " + dog.getName());
    }
}
