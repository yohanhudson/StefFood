
package bean;

import dao.Loja;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class LojaFacade extends AbstractFacade<Loja> {

    @PersistenceContext(unitName = "stefanini-ythudsonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LojaFacade() {
        super(Loja.class);
    }
    
    public List<Loja> findByCnpj(String cnpj) {
      return em.createQuery(
              "SELECT u FROM Loja u WHERE u.cnpj = :cnpjBuscar")
              .setParameter("cnpjBuscar", cnpj)
              .getResultList();
    } 
}
