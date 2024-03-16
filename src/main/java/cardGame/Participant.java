package cardGame;

import card.Card;
import card.Cards;
import java.util.List;

public class Participant {

    private static final int MAX_BLACK_JACK_SCORE = 21;
    private static final int INIT_CARD_SETTING_COUNT = 2;

    private final Cards cards;

    public Participant(List<Card> cardDeck) {
        this.cards = new Cards(cardDeck);
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isBlackJack() {
        return cards.countMaxScore() == MAX_BLACK_JACK_SCORE && cards.countCard() == INIT_CARD_SETTING_COUNT;
    }

    public boolean isBust() {
        int totalCardScore = cards.countMaxScore();
        return MAX_BLACK_JACK_SCORE < totalCardScore;
    }

    public int getCardScore() {
        return cards.countMaxScore();
    }

    public Cards getCards() {
        return cards;
    }
}
