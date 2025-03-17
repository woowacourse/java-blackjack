package domain.participants;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;

import domain.game.GameResult;

public class Player extends Gamer {
    private static final int BUST_THRESHOLD = 21;

    private final PlayerName playerName;
    private final BettingAmount bettingAmount;

    public Player(PlayerName playerName, BettingAmount bettingAmount) {
        super();
        this.playerName = playerName;
        this.bettingAmount = bettingAmount;
    }

    private Player(Player player){
        super(player);
        this.playerName = player.playerName;
        this.bettingAmount = player.bettingAmount;
    }

    @Override
    public Gamer newInstance() {
        return new Player(this);
    }

    public boolean isDrawable() {
        return super.isDrawable(BUST_THRESHOLD);
    }

    public GameResult decideGameResult(Dealer dealer) {
        if (this.isBust() || dealer.isBust()) {
            return decideGameResultWithBust(dealer);
        }
        return decideGameResultWithoutBust(dealer);
    }

    public boolean hasSameName(PlayerName playerName) {
        return this.playerName.equals(playerName);
    }


    public PlayerName getPlayerName() {
        return playerName;
    }

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }

    private GameResult decideGameResultWithBust(Dealer dealer) {
        if (this.isBust() && dealer.isBust()) {
            return DRAW;
        }
        if (this.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    private GameResult decideGameResultWithoutBust(Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        int playerScore = this.calculateScore();
        if (dealerScore == playerScore) {
            return DRAW;
        }
        if (dealerScore > playerScore) {
            return LOSE;
        }
        return WIN;
    }
}
