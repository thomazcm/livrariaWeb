package livrariaWeb.util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@ApplicationScoped
public class LogPhaseListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void beforePhase(PhaseEvent arg0) {
        System.out.print("Fase: ");
        System.out.println(getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        // TODO Auto-generated method stub
        return PhaseId.ANY_PHASE;
    }

   

}
