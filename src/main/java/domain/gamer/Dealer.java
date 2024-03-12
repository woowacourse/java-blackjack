package domain.gamer;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import java.util.List;

public class Dealer {

    private static final int MIN_DEALER_SCORE = 16;
    private final CardDeck cardDeck;
    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
        this.cardDeck = new CardDeck();
        initDealerCard();
    }

    private void initDealerCard() {
        cards.addCard(dealCard());
    }

    public Card dealCard() {
        return cardDeck.pickCard();
    }

    public void receiveCard() {
        cards.addCard(dealCard());
    }

    public boolean shouldDraw() {
        return cards.getMinGameScore() <= MIN_DEALER_SCORE;
    }

    public int getMaxGameScore() {
        return cards.countMaxScore();
    }

    public List<String> getCardStatus() {
        return cards.getCardsName();
    }
}
