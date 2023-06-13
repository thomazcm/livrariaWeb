package livrariaWeb.bean;

import javax.faces.bean.ManagedBean;
import livrariaWeb.dao.DAO;
import livrariaWeb.model.Autor;

@ManagedBean
public class AutorBean {

    private Autor autor = new Autor();

    public Autor getAutor() {
        return autor;
    }

    public void gravar() {
        System.out.println("Gravando autor " + this.autor.getNome());

        new DAO<Autor>(Autor.class).adiciona(this.autor);
        this.autor = new Autor();
    }
}