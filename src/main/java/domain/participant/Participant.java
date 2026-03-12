package domain.participant;

import domain.BetMoney;
import domain.Score;
import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected final Hand hand;
    protected BetMoney betMoney;

    public Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
        this.betMoney = BetMoney.ZERO;
    }

    public Participant(Name name, Hand hand) {
        validate(name, hand);
        this.name = name;
        this.hand = hand;
        this.betMoney = BetMoney.ZERO;
    }

    public Participant(String name, String value) {
        this.name = new Name(name);
        this.hand = new Hand();
        this.betMoney = BetMoney.of(value);
    }

    public Participant(String name, int value) {
        this.name = new Name(name);
        this.hand = new Hand();
        this.betMoney = BetMoney.of(value);
    }

    private void validate(Name name, Hand hand) {
        if (name == null || hand == null) {
            throw new IllegalArgumentException(Card.FIELD_CAN_NOT_BE_NULL);
        }
    }

    public void setBetMoney(String value) {
        this.betMoney = BetMoney.of(value);
    }

    public BetMoney getResult(Participant target) {
        if (isBlackjack() && target.isBlackjack()) {
            return betMoney.draw();
        }
        if (isBlackjack()) {
            return betMoney.blackjack();
        }
        if (target.isBlackjack()) {
            return betMoney.lose();
        }
        if (isBust()) {
            return betMoney.lose();
        }
        if (target.isBust()) {
            return betMoney.win();
        }
        Score playerScore = getTotalSum();
        Score targetScore = target.getTotalSum();

        if (playerScore.isEqualTo(targetScore)) {
            return betMoney.draw();
        }
        if (playerScore.isGreaterThan(targetScore)) {
            return betMoney.win();
        }
        return betMoney.lose();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void addCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public Score getTotalSum() {
        return hand.totalSum();
    }

    public Name getName() {
        return name;
    }

    public Hand getCards() {
        return hand;
    }
}
