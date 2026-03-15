package team.blackjack.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Player implements Participant {
    private final String name;
    private final List<Hand> hands;
    private final Bet bet;

    public Player(String name, int stake) {
        this.name = name;
        this.hands = initHands();
        this.bet = Bet.from(stake);
    }

    public List<Hand> getHands() {
        return List.copyOf(hands);
    }

    public String getName() {
        return this.name;
    }

    public List<String> getCardInAllHands() {
        return hands.getFirst().getCardNames();
    }

    public BigDecimal getPayout(Result result){
        return bet.calculatePayout(result);
    }

    @Override
    public int getScore() {
        return this.hands.getFirst().getScore();
    }

    @Override
    public void hit(Card card) {
        this.hands.getFirst().addCard(card);
    }

    private List<Hand> initHands() {
        List<Hand> hands = new ArrayList<>();
        hands.add(new Hand());
        return hands;
    }
}
