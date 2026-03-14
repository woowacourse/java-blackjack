package domain.betting.policy;


import domain.betting.BettingResult;
import domain.gamer.Dealer;
import domain.gamer.Player;

/**
 * 블랙잭 규칙 - BlackJack 에서 블랙잭에 의한 무승부를 Push라고 한다.
 * */
public class BlackjackPushPolicy extends BettingPolicy{

    public BlackjackPushPolicy() {
        super(BettingResult.BLACK_JACK_PUSH);
    }

    @Override
    public boolean isPolicyApplied(Dealer dealer, Player player) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    @Override
    public double getBettingRate(Dealer dealer, Player player) {
        return bettingResult.bettingRate();
    }

}
