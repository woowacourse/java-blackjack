package model.betting;

import model.participant.Dealer;

public class Betting {
    private static final int INSURANCE_BET_DIVISOR  = 2;
    private static final int INSURANCE_PAYOUT_RATIO = 2;
    private static final int BLACKJACK_PAYOUT_RATIO = 3/2;


    private int bet;
    private int insuranceBet;

    public Betting(int bet) {
        this.bet = bet;
    }

    public void addBet(int bet) {
        this.bet += bet;
    }

    public int calculateMaxInsuranceAmount() {
        return bet / INSURANCE_BET_DIVISOR;
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
            return insuranceBet * INSURANCE_PAYOUT_RATIO;
        }
        return insuranceBet;
    }

    public int calculateBlackJack() {
        return bet * BLACKJACK_PAYOUT_RATIO;
    }

    public int calculateWin() {
        return bet;
    }
}
