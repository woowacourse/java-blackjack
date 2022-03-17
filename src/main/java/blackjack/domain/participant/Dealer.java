package blackjack.domain.participant;

import java.util.List;
import java.util.Map;

import blackjack.domain.PlayRecord;
import blackjack.domain.PlayStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

public class Dealer extends Participant {

    private static final int HIT_CONDITION = 16;
    private static final String NAME = "딜러";

    private final BettingTable bettingTable;

    public Dealer(List<Betting> bettings) {
        super();
        this.bettingTable = new BettingTable(List.copyOf(bettings));
    }

    public DrawCount drawCards(CardDeck cardDeck) {
        int count = 0;
        while (getStatus() == PlayStatus.HIT && getScore() <= HIT_CONDITION) {
            hit(cardDeck.drawCard());
            count++;
        }

        return DrawCount.of(count);
    }

    public Card openCard() {
        return cards.findFirst();
    }

    public Map<Name, Long> getRevenues(Map<Name, PlayRecord> recordMap) {
        return bettingTable.getRevenues(getName(), recordMap);
    }

    public Name getName() {
        return Name.of(NAME);
    }
}
