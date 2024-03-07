package blackjack.domain;

import java.util.List;

public class Dealer {

    public static final Score NEED_CARD_CRITERION = new Score(17);
    private static final String DEALER_SIGNAL = "DEALER";

    private final Deck deck;
    private final Participant participant;


    public Dealer(final Deck deck) {
        this.deck = deck;
        this.participant = Participant.from(DEALER_SIGNAL);
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public Card drawCard() {
        return deck.pick();
    }

    public List<Card> drawCards(final int count) {
        return deck.pick(count);
    }

    public void addStartCard() {
        final List<Card> cards = deck.pick(2);
        cards.forEach(participant::addCard);
    }

    public boolean needMoreCard() {
        return NEED_CARD_CRITERION.isBiggerThan(participant.calculate());
    }

    public Score calculate() {
        return participant.calculate();
    }

    public void addCard() {
        participant.addCard(drawCard());
    }

    public Hands getOpenedHands() {
        return participant.getFirstCard();
    }

    public boolean isNotBlackjack() {
        return participant.isNotBlackjack();
    }

    public Hands getHands() {
        return participant.getHands();
    }

    public ParticipantName getName() {
        return participant.getName();
    }
}
