package blackjack.domain.game;

import java.util.List;
import java.util.Map;

import blackjack.domain.Name;

public final class Dealer extends Participant {

    private static final int HIT_CONDITION = 16;
    private static final String NAME = "딜러";

    private final BettingTable bettingTable;

    Dealer(List<Betting> bettings) {
        super();
        this.bettingTable = new BettingTable(List.copyOf(bettings));
    }

    @Override
    public boolean isDrawable() {
        return getState().isDrawable() && getCards().sum() <= HIT_CONDITION;
    }

    Map<Name, Long> getRevenues(Map<Name, PlayRecord> recordMap) {
        return bettingTable.getRevenues(getName(), recordMap);
    }

    public Name getName() {
        return Name.of(NAME);
    }
}
