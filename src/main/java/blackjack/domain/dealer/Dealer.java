package blackjack.domain.dealer;

import blackjack.domain.card.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import java.util.List;
import java.util.stream.Stream;

public class Dealer {

    public static final int NEED_CARD_NUMBER_MAX = 16;
    private static final ParticipantName DEALER_NAME = new ParticipantName("딜러");

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
        return hands.isNotBlackjack();
    }

    public Hands getOpenedHands() {
        return hands.getFirstCard();
    }

    public Hands getHands() {
        return new Hands(hands.getCards());
    }

    public ParticipantName getName() {
        return DEALER_NAME;
    }
}
