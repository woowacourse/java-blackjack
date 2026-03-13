package domain.participant;

import domain.BattingMoney;
import domain.card.Deck;
import java.util.List;

public class Player {
    private final Participant participant;
    private final BattingMoney battingMoney;

    private Player(Participant participant, BattingMoney battingMoney) {
        this.participant = participant;
        this.battingMoney = battingMoney;
    }

    public static Player from(String name, int amount) {
        Participant participant = new Participant(new Name(name), new Hand());
        return new Player(participant, new BattingMoney(amount));
    }

    public void playTurn(Deck deck) {
        participant.playTurn(deck);
    }

    public String getName() {
        return this.participant.getName();
    }

    public boolean isBust() {
        return participant.isBust();
    }

    public boolean isBlackJack() {
        return participant.isBlackJack();
    }

    public int getScore() {
        return participant.getScore();
    }

    public void initHand(Deck deck) {
        participant.initHand(deck);
    }

    public List<String> createCardNames() {
        return participant.createCardNames();
    }

    public int calculateBattingProfit(double profit) {
        return battingMoney.calculateBattingProfit(profit);
    }
}
