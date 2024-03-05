package player;

import card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int MAX_GAME_SCORE = 21;

    private final Name name;
    private List<Card> cards;

    public Player(Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public boolean isOverMaxGameScore() {
        int totalCardScore = cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();

        return MAX_GAME_SCORE < totalCardScore;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }
}
