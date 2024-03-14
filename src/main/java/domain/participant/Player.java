package domain.participant;

import domain.playingcard.Deck;

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

    public boolean isWin(final Dealer dealer) {
        return (!this.isBust() && dealer.isBust())
                || (this.isBlackJack() && !dealer.isBlackJack())
                || this.getTotalScore().isBigger(dealer.getTotalScore());
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
