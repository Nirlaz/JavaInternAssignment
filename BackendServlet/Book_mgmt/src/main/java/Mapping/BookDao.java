package Mapping;


import Model.Books;

public class BookDao  {
	
	private SessionFactory sessionFactory;
	
	public BookDao(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
//    public void saveBook(Books book) {
//        Transaction transaction = null;
//        try (Session session = sessionFactory.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(book);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

    public List<Book> getBooks() {
        try (Session session = sessionFactory.getCurrentSession()) {
            return session.createQuery("FROM Book").list();
        }
        catch(Exception e) {
        	System.out.println(e);
        }
    }

//    public void updateBook(Books book) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(book);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteBook(Long bookId) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Books book = session.get(Books.class, bookId);
//            if (book != null) {
//                session.delete(book);
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//
//    public Book getBook(Long bookId) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Book.class, bookId);
//        }
//    }
}