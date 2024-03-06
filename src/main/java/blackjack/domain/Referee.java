package blackjack.domain;

public class Referee {

    private final Dealer dealer;

    public Referee(Dealer dealer) {
        this.dealer = dealer;
    }


    public GameResult judgeGameResult(Player player) {
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (player.getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }
}
