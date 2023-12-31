package livrariaWeb.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import livrariaWeb.bean.util.ForwardView;
import livrariaWeb.bean.util.RedirectView;
import livrariaWeb.bean.util.View;
import livrariaWeb.dao.DAO;
import livrariaWeb.model.Autor;
import livrariaWeb.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private Livro livro = new Livro();
    private Integer autorId;

    public View gravar() {

        if (livro.getAutores().isEmpty()) {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            currentInstance.addMessage("autor", new FacesMessage("O livro deve possuir pelo menos um autor."));
            return new ForwardView("livro");
        }
        
        if (this.livro.getId() == null) {
            new DAO<Livro>(Livro.class).adiciona(this.livro);
        } else {
            new DAO<Livro>(Livro.class).atualiza(this.livro);
        }
        this.livro = new Livro();
        return new RedirectView("livro");
    }
    
    public void carregar(Livro livro) {
        this.livro = livro;
    }
    
    public View remover(Livro livro) {
        System.out.println("removendo livro..");
        new DAO<Livro>(Livro.class).remove(livro);
        return new RedirectView("livro");
    }
    
    public void removerAutorLivro(Autor autor) {
        this.livro.removeAutor(autor.getId());
    }

    public void gravarAutor() {
        Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
        this.livro.adicionaAutor(autor);
    }
    
    public RedirectView formAutor() {
        return new RedirectView("autor");
    }

    public Integer getAutorId() {
        return this.autorId;
    }

    public Livro getLivro() {
        return livro;
    }

    public List<Autor> getAutores() {
        return new DAO<Autor>(Autor.class).listaTodos();
    }

    public List<Autor> getAutoresDoLivro() {
        return Collections.unmodifiableList(this.livro.getAutores());
    }

    public List<Livro> getLivrosCadastrados() {
        return new DAO<Livro>(Livro.class).listaTodos();
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }
}
