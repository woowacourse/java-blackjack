package domain.participant;

import domain.constant.GameResult;
import domain.playingcard.Deck;

import static domain.constant.GameResult.*;

public class Player extends Participant {
    private static final int MAXIMUM_ENABLE_TOTAL_SCORE = 20;

    private final PlayerName playerName;

    public Player(final PlayerName playerName, final Hand hand) {
        super(hand);
        this.playerName = playerName;
    }

    public static Player of(final PlayerName playerName, final Deck deck) {
        Hand hand = Hand.init();
        initDraw(deck, hand);
        return new Player(playerName, hand);
    }

    @Override
    public boolean isDrawable() {
        return hand.isTotalScoreLessOrEqual(MAXIMUM_ENABLE_TOTAL_SCORE);
    }

    public GameResult determineGameResult(final Dealer dealer) {
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
