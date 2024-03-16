package participant;

import card.Card;
import card.Cards;
import java.util.List;
import participant.player.Name;

public class Participant {

    private static final int MAX_BLACK_JACK_SCORE = 21;
    private static final int INIT_CARD_SETTING_COUNT = 2;

    private final Name name;
    private final Cards cards;

    public Participant(List<Card> cardDeck, Name name) {
        this.cards = new Cards(cardDeck);
        this.name = name;
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

    public Name getName() {
        return name;
    }

    public int getCardScore() {
        return cards.countMaxScore();
    }

    public Cards getCards() {
        return cards;
    }
}
