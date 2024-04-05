package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        try (Session session = buildSessionFactory().openSession()) {
//            Query<Account> query = session.createQuery("FROM Account");
////            khi truy vấn thì vẫn như câu sql bỏ select *
//            //get all account from database
//            List<Account> accounts = query.list();
//            System.out.println("Size: " + accounts.size());
//            for (Account account : accounts) {
//                System.out.println("Account: " + account.getUsername()+" "+account.getId()+" "+account.getFullName());
//            }



//            Query<Account> query = session.createQuery("FROM Account WHERE id = 1");
//            Account account = query.getSingleResultOrNull();
//            System.out.println(account.getId()+" "+account.getFullName()+" "+account.getUsername());
//            System.out.println(account);

            //delete by id
            //cách 1
//            System.out.println("Nhap id can xoa: ");
//            Scanner scanner = new Scanner(System.in);
//            int id = scanner.nextInt();
//            session.beginTransaction();
//            Query query = session.createQuery("DELETE FROM Account WHERE id = "+id);
//            query.executeUpdate();
//            System.out.println("delete success");
//            session.getTransaction().commit();


            //cách 2

//            session.beginTransaction();
//            Query query = session.createQuery("FROM Account WHERE id = 5");
//            Account account = (Account) query.getSingleResultOrNull();
//            if(account != null) {
//                session.delete(account);
//                System.out.println("Delete success");
//            }else {
//                System.out.println("Not found");
//            }
//            session.getTransaction().commit();

            //cách 1 save account chỉ dùng hàm save không dùng câu hql
            session.beginTransaction();
            Account account = new Account();
            account.setUsername("Tuan");
            account.setFullName("Nguyen Tuan");
            account.setBirthDay("2000-01-01");
            session.save(account);
            System.out.println("add done");
            session.getTransaction().commit();
        }
    }
    private static SessionFactory buildSessionFactory() {

        // load db information
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // lấy thông tin và ép nó vào đối tuowjng account
//        phải là class entity mới add được
        configuration.addAnnotatedClass(Account.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
