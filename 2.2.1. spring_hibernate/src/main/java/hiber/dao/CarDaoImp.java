package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(String model, int series) {
        String hql = "FROM Car carr LEFT JOIN FETCH carr.user WHERE carr.model=:model AND carr.series=:series";
        Car car = sessionFactory.getCurrentSession().createQuery(hql, Car.class).setParameter("model", model).setParameter("series", series).uniqueResult();
        if (car == null) {
            System.out.println("Пользователь с такой машиной не найден");
            return null;
        }
        return car.getUser();
    }
}
