package model;

import static model.Dealer.DEALER_NAME;
import static model.card.Cards.BLACK_JACK_SCORE;

public class Player extends Participator {
    private Betting betting;

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
        return Result.of(this, dealer);
    }

    public long getBettingAmount() {
        return betting.getBettingAmount();
    }

    public void bet(Dealer dealer) {
        betting = betting.bet(dealer);
    }

    public long getProfitAmount() {
        return betting.getProfitAmount();
    }
}
