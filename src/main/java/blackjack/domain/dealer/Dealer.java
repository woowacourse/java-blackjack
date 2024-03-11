package blackjack.domain.dealer;

import blackjack.domain.rule.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.ParticipantName;
import java.util.List;
import java.util.stream.Stream;

public class Dealer {

    private static final Score NEED_CARD_CRITERION = new Score(17);
    private static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private final Participant participant;

    public Dealer(final Deck deck) {
        this.deck = deck;
        this.participant = Participant.from(DEALER_NAME);
    }

    public boolean needMoreCard() {
        return NEED_CARD_CRITERION.isBiggerThan(participant.calculateScore());
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
        participant.addCard(drawCard());
    }

    public void addCard(int count) {
        while (count-- > 0) {
            participant.addCard(drawCard());
        }
    }

    public Score calculateScore() {
        return participant.calculateScore();
    }

    public boolean isNotBlackjack() {
        return participant.isNotBlackjack();
    }

    public Hands getOpenedHands() {
        return participant.getFirstCard();
    }

    public Hands getHands() {
        return participant.getHands();
    }

    public ParticipantName getName() {
        return participant.getName();
    }
}
