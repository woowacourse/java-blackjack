package model.cards;

import exception.IllegalBlackjackStateException;
import java.util.Map;
import java.util.Set;

public class ParticipantsCards {

    private final DealerCards dealerCards;
    private final Map<String, PlayerCards> playerCards;

    public ParticipantsCards(final DealerCards dealerCards, final Map<String, PlayerCards> playerCards) {
        this.dealerCards = dealerCards;
        this.playerCards = playerCards;
    }

    public DealerCards getDealerCards() {
        return dealerCards;
    }

    public PlayerCards findCardsByName(final String name) {
        if (!playerCards.containsKey(name)) {
            throw new IllegalBlackjackStateException("존재하지 않는 플레이어입니다.");
        }
        return playerCards.get(name);
    }

    public Set<String> getNames() {
        return playerCards.keySet();
    }
}
