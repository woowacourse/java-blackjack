package participant.dealer;

import card.Card;
import card.CardDeck;
import card.Cards;
import java.util.List;
import participant.player.Name;

public class Dealer {

    private static final String DEALER_NAME = "딜러";
    private static final int MIN_DEALER_SCORE = 16;

    private final Cards cards;
    private final Name name;

    public Dealer(List<Card> initCards) {
        this.cards = new Cards(initCards);
        this.name = new Name(DEALER_NAME);
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    public void playGame(CardDeck cardDeck) {
        while (isNotOverMinScore()) {
            cards.addCard(cardDeck.pickCard());
        }
    }

    private boolean isNotOverMinScore() {
        return cards.countMaxScore() <= MIN_DEALER_SCORE;
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getCardScore() {
        return cards.countMaxScore();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }
}
