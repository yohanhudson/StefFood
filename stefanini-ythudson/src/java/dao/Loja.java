
package dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "loja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loja.findAll", query = "SELECT u FROM Loja u"),
    @NamedQuery(name = "Loja.findByendereco", query = "SELECT u FROM Loja u WHERE u.endereco = :endereco"),
    @NamedQuery(name = "Loja.findById", query = "SELECT u FROM Loja u WHERE u.id = :id"),
    @NamedQuery(name = "Loja.findByName", query = "SELECT u FROM Loja u WHERE u.name = :name"),
    @NamedQuery(name = "Loja.findByemail", query = "SELECT u FROM Loja u WHERE u.email = :email"),
    @NamedQuery(name = "Loja.findBycnpj", query = "SELECT u FROM Loja u WHERE u.cnpj = :cnpj")})
public class Loja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "endereco")
    private String endereco;
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 14, max = 14)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 20, max = 2147483647)
    @Column(name = "email")
    private String email;

    public Loja() {
    }

    public Loja(Long id) {
        this.id = id;
    }

    public Loja(Long id, String endereco, String name, String cnpj, String email) {
        this.id = id;
        this.endereco = endereco;
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loja)) {
            return false;
        }
        Loja other = (Loja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Loja[ id=" + id + " ]";
    }
    
}
