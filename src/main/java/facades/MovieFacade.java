package facades;

import dtos.MovieDTO;
import entities.Movie;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade
{

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public MovieDTO create(MovieDTO rm){
        Movie rme = new Movie(rm.getTitle(), rm.getDirector());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(rme);
    }
    public MovieDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Movie mv = em.find(Movie.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new MovieDTO(mv);
    }

    public MovieDTO getMovieByTitle(String title) throws EntityNotFoundException
    {
        EntityManager em = emf.createEntityManager();
        Movie mv = em.find(Movie.class, title);
        if (mv == null)
            throw new EntityNotFoundException("The Movie entity with title: "+title+" was not found");
        return new MovieDTO(mv);
    }

    public MovieDTO getMovieByDirector(String director) throws EntityNotFoundException
    {
        EntityManager em = emf.createEntityManager();
        Movie mv = em.find(Movie.class, director);
        if (mv == null)
            throw new EntityNotFoundException("The Movie entity with director: "+director+" was not found");
        return new MovieDTO(mv);
    }

    //TODO Remove/Change this before use
    public long getMovieCount(){
        EntityManager em = getEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return movieCount;
        }finally{
            em.close();
        }
    }


    public List<MovieDTO> getAllMovies(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Movie> query = em.createQuery("SELECT r FROM Movie r", Movie.class);
        List<Movie> mvs = query.getResultList();
        return MovieDTO.getDtos(mvs);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade fe = getFacadeExample(emf);
        fe.getAllMovies().forEach(dto->System.out.println(dto));
    }

}
