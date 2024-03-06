package cardGame;

import card.Card;
import java.util.List;

public class GameParticipant {

    private static final int MAX_GAME_SCORE = 21;

    private List<Card> cards;

    public GameParticipant(List<Card> cards) {
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isOverMaxGameScore() {
        int totalCardScore = cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();

        return MAX_GAME_SCORE < totalCardScore;
    }
}
