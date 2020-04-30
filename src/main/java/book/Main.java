package book;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:bookDb");
        jdbi.installPlugin(new SqlObjectPlugin());

        try(Handle handle = jdbi.open()){
            BookDao bookDao = handle.attach((BookDao.class));
            bookDao.createTable();

            bookDao.insert(new Book("9781406360196","Anthony Horowitz","Alex Rider:Stormbreaker",Book.Format.PAPERBACK,"Walker Books Ltd", LocalDate.parse("2015-05-05"),272,true));

            bookDao.insert(new Book("9781406360240","Anthony Horowitz","Alex Rider: Ark angel",Book.Format.PAPERBACK,"Walker Books Ltd", LocalDate.parse("2015-05-05"),384,false));

            bookDao.insert(new Book("9781612620244","Hajime Isayama","Attack on titan 1",Book.Format.PAPERBACK,"Kodansha America, Inc",LocalDate.parse("2012-06-19"),200,true));

            bookDao.insert(new Book("9781421587660","Haruichi Furudate","Haikyuu 1",Book.Format.PAPERBACK,"Viz Media, Subs. of Shogakukan Inc",LocalDate.parse("2016-07-28"),192,true));

            bookDao.findAll().forEach(System.out::println);
            bookDao.delete(bookDao.find("9781406360240").get());
            bookDao.findAll().forEach(System.out::println);
        }


    }
}
