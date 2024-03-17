package domain.participant;

import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;

import java.util.List;

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

    public int calculateRevenue(final List<Integer> playerRevenues) {
        return -playerRevenues.stream()
                .reduce(0, Integer::sum);
    }

    public PlayingCard getFirstPlayingCard() {
        return hand.getPlayingCards().get(0);
    }
}
