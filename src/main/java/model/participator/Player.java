package model.participator;

import static model.card.Cards.BLACK_JACK_SCORE;
import static model.participator.Dealer.DEALER_NAME;

import model.Result;
import model.Status;
import model.betting.Betting;
import model.betting.BettingCalculateStrategy;
import model.betting.BlackJacCalculateStrategy;
import model.betting.NormalCalculateStrategy;

public class Player extends Participator {
    private Betting betting;
    private boolean canMatch = true;

    private Player(String playerName, Betting betting) {
        super(playerName);
        checkNameIsDealer(playerName);
        this.betting = betting;
    }

    public Player(String playerName, long bettingAmount) {
        this(playerName, new Betting(bettingAmount));
    }

    private void checkNameIsDealer(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("이름 입력 실패 : 딜러라는 이름은 사용할 수 없습니다.");
        }
    }

    @Override
    public boolean canReceiveCard() {
        return this.getSum() < BLACK_JACK_SCORE;
    }

    public Result matchWith(Dealer dealer) {
        canMatch = false;
        return Result.of(this, dealer);
    }

    public void lostBet(Dealer dealer) {
        this.subtractProfit(betting.getBettingAmount(new NormalCalculateStrategy()));
        dealer.addProfit(betting.getBettingAmount(new NormalCalculateStrategy()));
    }

    public void winBet(Dealer dealer) {
        BettingCalculateStrategy strategy = new NormalCalculateStrategy();
        if (this.getStatus().equals(Status.BLACKJACK)) {
            strategy = new BlackJacCalculateStrategy();
        }
        dealer.subtractProfit(betting.getBettingAmount(strategy));
        this.addProfit(betting.getBettingAmount(strategy));
    }

    public boolean canMatch() {
        return canMatch;
    }

    public long getBettingAmount() {
        return betting.getBettingAmount(new NormalCalculateStrategy());
    }
}
