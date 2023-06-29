package livrariaWeb.bean.util;

public class ForwardView implements View {

    private String viewName;

    public ForwardView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String toString() {
        return viewName;
    }

}
