package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(String model, int series) {
        Car car = sessionFactory.getCurrentSession().createQuery("FROM Car carr " +
                "LEFT JOIN FETCH carr.user WHERE carr.model=:model AND " +
                "carr.series=:series", Car.class).setParameter("model", model)
                .setParameter("series", series).uniqueResult();
        if (car == null) {
            return null;
        }
        return car.getUser();
    }
}
