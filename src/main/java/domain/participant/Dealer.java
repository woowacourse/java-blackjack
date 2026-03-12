package domain.participant;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public record Dealer(Participant dealer) {

    private static final int STAND_SCORE = 17;

    public Dealer(Deck deck) {
        this(new Participant(new Name("딜러"), new Hand()));
        dealer.initHand(deck);
    }

    public void playTurn(Deck deck) {
        dealer.playTurn(deck);
    }

    public boolean stay() {
        return dealer.getScore() < STAND_SCORE;
    }

    public int getScore() {
        return dealer.getScore();
    }

    public boolean isBlackJack() {
        return dealer.isBlackJack();
    }

    public Participant getDealer() {
        return dealer;
    }

    public boolean isBust() {
        return dealer.isBust();
    }

    public List<String> firstCardNames() {
        List<String> dealerCard = new ArrayList<>(dealer.createCardNames());
        dealerCard.removeLast();
        return dealerCard;
    }

    public List<String> createCardNames() {
        return dealer.createCardNames();
    }
}
