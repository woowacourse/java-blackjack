package domain.participant;

import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;

public class Dealer extends Participant {
    private static final int MAXIMUM_ENABLE_TOTAL_SCORE = 16;

    public Dealer(final Hand hand) {
        super(hand);
    }

    public static Dealer init(final Deck deck) {
        Hand hand = Hand.init();
        initDraw(deck, hand);
        return new Dealer(hand);
    }

    @Override
    public boolean isDrawable() {
        return hand.isTotalScoreLessOrEqual(MAXIMUM_ENABLE_TOTAL_SCORE);
    }

    public boolean isWin(final Player player) {
        return player.isBust()
                || (this.isBlackJack() && !player.isBlackJack())
                || (!this.isBust() && this.getTotalScore().isBigger(player.getTotalScore()));
    }

    public PlayingCard getFirstPlayingCard() {
        return hand.getPlayingCards().get(0);
    }
}
