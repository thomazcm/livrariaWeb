package livrariaWeb.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import livrariaWeb.bean.util.RedirectView;
import livrariaWeb.dao.DAO;
import livrariaWeb.model.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

    private Autor autor = new Autor();

    public Autor getAutor() {
        return autor;
    }

    public String gravar() {
        System.out.println("Gravando autor " + this.autor.getNome());

        new DAO<Autor>(Autor.class).adiciona(this.autor);
        this.autor = new Autor();
        return "livro?faces-redirect=true";
    }

    public List<Autor> getAutoresCadastrados() {
        return new DAO<Autor>(Autor.class).listaTodos();
    }
    
    public void remover(Autor autor) {
        System.out.println(String.format("Removendo: %s", autor.getNome()));
        new DAO<Autor>(Autor.class).remove(autor);
    }
}