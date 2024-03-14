package domain.state.run;

import domain.state.Started;

public abstract class Running extends Started {
    @Override
    public double profit() {
        return 0;
    }
}
