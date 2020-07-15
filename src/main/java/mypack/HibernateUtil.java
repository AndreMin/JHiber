package mypack;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.MetadataSource;

public class HibernateUtil {
    private static SessionFactory factory;

    static  {
        /*try{
            factory=new Configuration().configure().buildSessionFactory();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }*/

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            factory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionFactory getFactory(){
        return factory;
    }
}
