package domain.participant;

import domain.card.CardHand;
import domain.game.Bet;
import domain.game.GameResult;

public class Player extends GameParticipant {
    private Bet bet;

    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
        validatePlayerName(name);
    }

    private void validatePlayerName(String playerName) {
        if (playerName.equals(Dealer.NAME)) {
            throw new IllegalArgumentException(String.format("[ERROR] '%s'는 플레이어 이름으로 사용할 수 없습니다.", Dealer.NAME));
        }
    }

    public void bet(int betAmount) {
        bet = new Bet(betAmount);
    }

    public double calculateProfit(Dealer dealer) {
        GameResult playerGameResult = GameResult.calculatePlayerGameResult(dealer, this);
        double earningRate = playerGameResult.getEarningRate(this.isBlackJack());
        return earningRate * bet.getAmount();
    }

    @Override
    public boolean canHit() {
        return !isBlackJack() && !isBust();
    }
}
