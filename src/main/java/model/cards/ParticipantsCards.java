package model.cards;

import exception.IllegalBlackjackStateException;
import java.util.Map;
import java.util.Set;

public class ParticipantsCards {

    private final Cards dealerCards;
    private final Map<String, Cards> playerCards;

    public ParticipantsCards(final Cards dealerCards, final Map<String, Cards> playerCards) {
        this.dealerCards = dealerCards;
        this.playerCards = playerCards;
    }

    public DealerCards getDealerCards() {
        return (DealerCards) dealerCards;
    }

    public PlayerCards findCardsByName(final String name) {
        if (!playerCards.containsKey(name)) {
            throw new IllegalBlackjackStateException("존재하지 않는 플레이어입니다.");
        }
        return (PlayerCards) playerCards.get(name);
    }

    public Set<String> getNames() {
        return playerCards.keySet();
    }
}
