package blackjack.domain.dealer;

import blackjack.domain.card.BlackjackStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.card.Score;
import java.util.List;
import java.util.stream.Stream;

public class Dealer {

    public static final int NEED_CARD_NUMBER_MAX = 16;
    public static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private final Hands hands;

    public Dealer(final Deck deck) {
        this.deck = deck;
        this.hands = new Hands();
    }

    public boolean needMoreCard() {
        return hands.calculateScore().isSmallerOrEqual(new Score(NEED_CARD_NUMBER_MAX));
    }


    public Card drawCard() {
        return deck.pick();
    }

    public List<List<Card>> drawCards(final int playerSize, final int eachCardCount) {
        return Stream.generate(() -> deck.pick(eachCardCount))
                .limit(playerSize)
                .toList();
    }

    public void addCard() {
        hands.addCard(drawCard());
    }

    public void addCard(int count) {
        while (count-- > 0) {
            hands.addCard(drawCard());
        }
    }

    public boolean isNotBlackjack() {
        return BlackjackStatus.from(hands.calculateScore()) != BlackjackStatus.BLACKJACK;
    }

    public Hands getOpenedHands() {
        return hands.getFirstCard();
    }

    public Hands getHands() {
        return new Hands(hands.getCards());
    }
}
