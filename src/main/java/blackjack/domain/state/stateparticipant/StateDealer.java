package blackjack.domain.state.stateparticipant;

import java.util.List;
import java.util.Map;

import blackjack.domain.PlayRecord;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Betting;
import blackjack.domain.participant.BettingTable;
import blackjack.domain.participant.DrawCount;
import blackjack.domain.participant.Name;

public class StateDealer extends StateParticipant {

    private static final int HIT_CONDITION = 16;
    private static final String NAME = "딜러";

    private final BettingTable bettingTable;

    public StateDealer(List<Betting> bettings) {
        super();
        this.bettingTable = new BettingTable(List.copyOf(bettings));
    }

    public DrawCount drawCards(CardDeck cardDeck) {
        int count = 0;
        while (isDrawable()) {
            draw(cardDeck.drawCard());
            count++;
        }

        return DrawCount.of(count);
    }

    private boolean isDrawable() {
        return !getState().isFinished() && getCards().sum() <= HIT_CONDITION;
    }

    public Card openCard() {
        return getCards().findFirst();
    }

    public Map<Name, Long> getRevenues(Map<Name, PlayRecord> recordMap) {
        return bettingTable.getRevenues(getName(), recordMap);
    }

    public Name getName() {
        return Name.of(NAME);
    }
}
