package livrariaWeb.bean.util;

public class RedirectView implements View {
    
    private String viewName;

    public RedirectView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String toString() {
        return viewName + "?faces-redirect=true";
    }

}
