package cardGame;

import card.Card;
import card.Cards;
import java.util.List;

public class GameParticipant {

    private static final int MAX_GAME_SCORE = 21;
    private static final int MIN_GAME_SCORE = 0;

    private final Cards cards;

    public GameParticipant(List<Card> cardDeck) {
        this.cards = new Cards(cardDeck);
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isOverMaxCardScore() {
        int totalCardScore = cards.countMatchScore();

        return MAX_GAME_SCORE < totalCardScore;
    }

    public int getMaxGameScore() {
        int maxScore = cards.countMaxScore();

        if (maxScore > MAX_GAME_SCORE) {
            return MIN_GAME_SCORE;
        }
        return maxScore;
    }

    public Cards getCards() {
        return cards;
    }

    public int getCardsSize() {
        return cards.countCard();
    }
}
