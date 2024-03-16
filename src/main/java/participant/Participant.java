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

    public boolean isBlackJack() {
        return getCardScore() == MAX_BLACK_JACK_SCORE
                && cards.countCard() == INIT_CARD_SETTING_COUNT;
    }

    public boolean isBust() {
        return getCardScore() > MAX_BLACK_JACK_SCORE;
    }

    public void hit(Card card) {
        cards.addCard(card);
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
