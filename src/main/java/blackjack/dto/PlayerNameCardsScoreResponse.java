package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;

import java.util.List;

public class PlayerNameCardsScoreResponse {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public PlayerNameCardsScoreResponse(Player player) {
        this.name = player.getName();
        this.cards = player.getCards().getCards();
        this.score = player.calculateScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
