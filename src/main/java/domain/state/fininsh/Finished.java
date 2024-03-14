package domain.state.fininsh;

import domain.state.Started;
import domain.state.State;

public abstract class Finished extends Started {

    @Override
    public State draw() {
        return null;
    }

    @Override
    public State stand() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit() {
        return 0;
    }

    public abstract double earningRate();
}
