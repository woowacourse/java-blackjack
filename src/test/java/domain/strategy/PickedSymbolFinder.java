package domain.strategy;

import domain.Symbol;
import domain.SymbolFinder;

public class PickedSymbolFinder implements SymbolFinder {
    private final int index;

    public PickedSymbolFinder(int index) {
        this.index = index;
    }

    @Override
    public Symbol find() {
        return Symbol.findBy(index);
    }
}
