/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Produto;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import bean.ProdutoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author ythudson
 */

@Named("produtoController")
@SessionScoped
public class ProdutoController implements Serializable {
    
    
        @EJB
    private bean.ProdutoFacade ejbFacade;
    private List<Produto> items = null;
    private Produto selected;

    public ProdutoController() {

    }

    public Produto getSelected() {
        return selected;
    }

    public void setSelected(Produto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProdutoFacade getFacade() {
        return ejbFacade;
    }

    public Produto prepareCreate() {
        selected = new Produto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "Cadastro com Sucesso");
        if (!JsfUtil.isValidationFailed()) {
            items = null;
        }
}

public void update() {
        persist(PersistAction.UPDATE, "Dados atualizado com sucesso");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Conta excluida com sucesso");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; 
            items = null;   
        }
    }

    public List<Produto> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                }else if (persistAction != PersistAction.INVALIDO){
                    JsfUtil.addSuccessMessage(successMessage);
                }else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Produto getProduto(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Produto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Produto> getItemsAvailableSelectOne() {
        return getFacade().findAll();

}

    @FacesConverter(forClass = Produto.class)
public static class ProdutoControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        ProdutoController controller = (ProdutoController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "ProdutoController");
        return controller.getProduto(getKey(value));
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Produto) {
            Produto o = (Produto) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Produto.class.getName()});
            return null;
        }
    }

}
}
