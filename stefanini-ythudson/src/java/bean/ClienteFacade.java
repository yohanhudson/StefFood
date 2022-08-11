
package bean;

/**
 *
 * @author ythudson
 */
import dao.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

@PersistenceContext(unitName = "stefanini-ythudsonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    public List<Cliente> findByEmail(String email) {
      return em.createQuery(
              "SELECT u FROM Cliente u WHERE u.email = :emailBuscar")
              .setParameter("emailBuscar", email)
              .getResultList();
    } 
}
