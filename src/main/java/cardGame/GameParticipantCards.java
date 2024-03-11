package cardGame;

import card.Card;
import card.Cards;
import java.util.List;

public class GameParticipantCards {

    private static final int MAX_GAME_SCORE = 21;

    private final Cards cards;

    public GameParticipantCards(List<Card> cardDeck) {
        this.cards = new Cards(cardDeck);
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isBust() {
        int totalCardScore = cards.countMaxScore();

        return MAX_GAME_SCORE < totalCardScore;
    }

    public int getCardScore() {
        return cards.countMaxScore();
    }

    public Cards getCards() {
        return cards;
    }
}
