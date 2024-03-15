package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.ArrayList;

public class Dealer extends Gamer {

    //TODO: 어떤 것에 대한 Threshold인지 더 명확히 나타내도록 이름 변경하기
    private static final int THRESHOLD = 16;

    private final Deck deck;

    private Dealer(final Deck deck, final Hand hand) {
        super(hand);
        this.deck = deck;
    }

    public static Dealer from(final Deck deck) {
        return new Dealer(deck, new Hand(new ArrayList<>()));
    }

    public void draw() {
        hand.add(deck.pop());
    }

    public Card drawPlayerCard() {
        return deck.pop();
    }

    public Card openFirstCard() {
        return hand.findFirst();
    }

    @Override
    public boolean canDraw() {
        return hand.calculateOptimalSum() <= THRESHOLD;
    }
}
