package model.betting;

import model.participant.Dealer;

public class Betting {
    private static final int INSURANCE_BET_DIVISOR  = 2;
    private static final int SURRENDER_PAYOUT_RATIO  = 2;
    private static final int INSURANCE_PAYOUT_RATIO = 2;
    private static final double BLACKJACK_PAYOUT_RATIO = 1.5;


    private int bet;
    private int insuranceBet;
    private boolean isSurrender = false;

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
        this.bet = this.bet - insuranceBet;
        this.insuranceBet = insuranceBet;
    }

    private void validateInsuranceBet(int insuranceBet) {
        int maxInsuranceAmount = calculateMaxInsuranceAmount();
        if (insuranceBet < 0 || insuranceBet > maxInsuranceAmount) {
            throw new IllegalArgumentException("[ERROR] 설정가능한 보험금액이 아닙니다. 입력값 : " + insuranceBet);
        }
    }

    public int calculateLose() {
        return -bet;
    }

    public int calculateDraw() {
        return 0;
    }

    public void surrender() {
        isSurrender = true;
    }

    public boolean checkSurrender(){
        return isSurrender;
    }

    public int calculateSurrender(){
        return -(bet / SURRENDER_PAYOUT_RATIO);
    }

    public int calculateInsurance(Dealer dealer) {
        if (dealer.checkBlackjack()) {
            return insuranceBet * INSURANCE_PAYOUT_RATIO;
        }
        return insuranceBet;
    }

    public int calculateBlackJack() {
        return (int) (bet * BLACKJACK_PAYOUT_RATIO);
    }

    public int calculateWin() {
        return bet;
    }
}
