package ru.igor.bankprocessingan.dao;

import ru.igor.bankprocessingan.db.HibernateSession;
import ru.igor.bankprocessingan.entities.User;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@SessionScoped
public class UserDao implements Serializable {

    @Inject
    private HibernateSession hibernateSession;

    public Integer returnBalance(User user) {
        String amountFrom = "select balance from User " + "WHERE email = :email";
        Query query = hibernateSession.get().createQuery(amountFrom);
        query.setParameter("email", user.getEmail());
        Integer tempBallance = (Integer) query.uniqueResult();
        return tempBallance;
    }

    public User createUser(String email, String name, int age) {
        return createUser(name, email, age, 0);
    }

    public User createUser(String email, String name, int age, int balance) {
        if (isExistedEmail(email)) {
            throw new IllegalArgumentException("Email already exists.");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        user.setBalance(balance);
        user.setRole("user");

        hibernateSession.get().save(user);
        return user;
    }

    public List<User> findAllUsers() {
        Query query = hibernateSession.get().createQuery("FROM User");
        List<User> users = query.list();
        return users;
    }

    public User findUserByEmail(String email) {
        Query query = hibernateSession.get().createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        System.out.println("User is: " + user);
        return user;
    }

    public void deposit(String userName, Integer amount, User user) {

        Transaction tx = null;

        try {
            tx = hibernateSession.get().beginTransaction();
            Integer currentBalanceFrom = user.getBalance() + amount;

            String amountFrom = "Update User set balance = :amount " + "WHERE email = :email";
            Query query = hibernateSession.get().createQuery(amountFrom);
            query.setParameter("amount", currentBalanceFrom);
            query.setParameter("email", user.getEmail());
            int executeUpdate = query.executeUpdate();
            tx.commit();
            user.setBalance(currentBalanceFrom);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public void transfer(String emailTo, Integer amount, User user) {

        if (emailTo == null || !isExistedEmail(emailTo)) {
            throw new RuntimeException("Email is null or does not exist");
        }

        Transaction tx = null;

        try {
            tx = hibernateSession.get().beginTransaction();
            Integer currentUserBalance = user.getBalance();
            Integer balanceToUser = findUserByEmail(emailTo).getBalance();

            if (currentUserBalance < amount) {
                throw new RuntimeException("Balance too low!");
            }

            Query fromUser = hibernateSession.get().createQuery("Update User set balance = :amount " + "WHERE email = :email");
            Query toUser = hibernateSession.get().createQuery("Update User set balance = :amount " + "WHERE email = :email");

            currentUserBalance = currentUserBalance - amount;
            balanceToUser = balanceToUser + amount;

            fromUser.setParameter("amount", currentUserBalance);
            fromUser.setParameter("email", user.getEmail());

            toUser.setParameter("amount", balanceToUser);
            toUser.setParameter("email", emailTo);

            int executeFrom = fromUser.executeUpdate();
            int executeTo = toUser.executeUpdate();
            
            tx.commit();

            user.setBalance(currentUserBalance);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    private boolean isExistedEmail(String email) {
        // select count(*) count from user where email = :email;
        Query query = hibernateSession.get().createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);
        boolean result = !query.list().isEmpty();
        return result;
    }
}


//    public void logOut(HttpSession httpSession) {
//        httpSession.invalidate();
//    } 1994700