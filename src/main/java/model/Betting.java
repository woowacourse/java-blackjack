package model;

public class Betting {
    private int bet;
    private int insuranceBet;

    public Betting(int bet) {
        this.bet = bet;
    }

    public void addBet(int bet) {
        this.bet += bet;
    }
}
