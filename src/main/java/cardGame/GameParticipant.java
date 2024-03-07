package cardGame;

import card.Card;
import card.Cards;
import java.util.List;

public class GameParticipant {

    private static final int MAX_GAME_SCORE = 21;

    private Cards cards;

    public GameParticipant(List<Card> cardDeck) {
        this.cards = new Cards(cardDeck);
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isOverMaxCardScore() {
        int totalCardScore = cards.countRoundScore();

        return MAX_GAME_SCORE < totalCardScore;
    }

    public int getMaxGameScore() {
        return cards.countMaxScore();
    }

    public Cards getCards() {
        return cards;
    }

    public int getCardsSize() {
        return cards.countCard();
    }
}
