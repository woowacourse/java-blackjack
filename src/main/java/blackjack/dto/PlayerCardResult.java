package blackjack.dto;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerCardResult {
    private final String playerName;
    private final List<String> cards;

    public PlayerCardResult(String playerName, List<Card> cards) {
        this.playerName = playerName;
        this.cards = toCardNames(cards);
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getCards() {
        return cards;
    }

    private List<String> toCardNames(List<Card> cards) {
        return cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.toList());
    }
}
