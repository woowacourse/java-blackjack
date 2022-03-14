package blackjack.dto;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class FirstTurnCards {

    private final String playerName;
    private final List<String> cards;

    public FirstTurnCards(String playerName, List<Card> cards) {
        this.playerName = playerName;
        this.cards = toName(cards);
    }

    private List<String> toName(List<Card> cards) {
        return cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.toList());
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getCards() {
        return cards;
    }
}
