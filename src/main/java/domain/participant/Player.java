package domain.participant;

import domain.constant.GameResult;
import domain.game.BettingMoney;
import domain.playingcard.Deck;

import static domain.constant.GameResult.DRAW;
import static domain.constant.GameResult.LOSE;

public class Player extends Participant {
    private static final int MAXIMUM_ENABLE_TOTAL_SCORE = 20;

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;

    public Player(final PlayerName playerName, final BettingMoney bettingMoney, final Hand hand) {
        super(hand);
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public static Player of(final PlayerName playerName, final BettingMoney bettingMoney, final Deck deck) {
        Hand hand = Hand.init();
        initDraw(deck, hand);
        return new Player(playerName, bettingMoney, hand);
    }

    @Override
    public boolean isDrawable() {
        return hand.isTotalScoreLessOrEqual(MAXIMUM_ENABLE_TOTAL_SCORE);
    }

    public int calculateRevenue(final Dealer dealer) {
        return determineGameResult(dealer).calculateRevenue(bettingMoney);
    }

    private GameResult determineGameResult(final Dealer dealer) {
        if (isWin(dealer)) {
            return GameResult.getWinResult(isBlackJack());
        }

        if (isLose(dealer)) {
            return LOSE;
        }

        return DRAW;
    }

    private boolean isWin(final Dealer dealer) {
        return (!this.isBust() && dealer.isBust())
                || (this.isBlackJack() && !dealer.isBlackJack())
                || !this.isBust() && this.getTotalScore().isBigger(dealer.getTotalScore());
    }

    private boolean isLose(final Dealer dealer) {
        return this.isBust()
                || (dealer.isBlackJack() && !this.isBlackJack())
                || (!dealer.isBust() && dealer.getTotalScore().isBigger(this.getTotalScore()));
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
