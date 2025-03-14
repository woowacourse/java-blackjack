package model;

import model.participant.Dealer;

public class Betting {
    private int bet;
    private int insuranceBet;

    public Betting(int bet) {
        this.bet = bet;
    }

    public void addBet(int bet) {
        this.bet += bet;
    }

    public int calculateMaxInsuranceAmount() {
        return bet / 2;
    }

    public void takeInsurance(int insuranceBet) {
        validateInsuranceBet(insuranceBet);
        this.bet -= insuranceBet;
        this.insuranceBet = insuranceBet;
    }

    private void validateInsuranceBet(int insuranceBet) {
        if (insuranceBet < 0 || insuranceBet > calculateMaxInsuranceAmount()) {
            throw new IllegalArgumentException("[ERROR] 설정가능한 보험금액이 아닙니다. 입력값 : " + insuranceBet);
        }
    }

    public int calculateLose() {
        return -bet;
    }

    public int calculateDraw() {
        return 0;
    }

    public int calculateInsurance(Dealer dealer) {
        if (dealer.checkBlackjack()) {
            return insuranceBet * 2;
        }
        return insuranceBet;
    }

    public int calculateBlackJack() {
        return bet * 3 / 2;
    }

    public int calculateWin() {
        return bet;
    }
}
