package domain.strategy;

import domain.Number;
import domain.NumberFinder;

public class PickedNumberFinder implements NumberFinder {

    private final int index;

    public PickedNumberFinder(int index) {
        this.index = index;
    }

    @Override
    public Number find() {
        return Number.findBy(index);
    }
}
