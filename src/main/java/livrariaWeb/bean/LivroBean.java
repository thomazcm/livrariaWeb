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
import livrariaWeb.dao.DAO;
import livrariaWeb.model.Autor;
import livrariaWeb.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private Livro livro = new Livro();
    private Integer autorId;

    public void gravar() {

        if (livro.getAutores().isEmpty()) {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            currentInstance.addMessage("autor", new FacesMessage("O livro deve possuir pelo menos um autor."));
            return;
        }
        new DAO<Livro>(Livro.class).adiciona(this.livro);
        this.livro = new Livro();
    }

    public void gravarAutor() {
        Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
        this.livro.adicionaAutor(autor);
    }

    public void comecaComDigitoUm(FacesContext fc, UIComponent uc, Object value)
            throws ValidatorException {
        String valor = value.toString();
        if (!valor.startsWith("1")) {
            throw new ValidatorException(new FacesMessage("O ISBN deve começar com 1"));
        }
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
