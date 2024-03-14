package domain.state.run;

import domain.state.State;
import domain.state.fininsh.Stand;

public class Hit extends Running {
    @Override
    public State draw() {return null;}

    @Override
    public State stand() {
        return new Stand();
    }
}
