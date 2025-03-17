package domain.card;

import java.util.List;

public class Card extends TrumpCard {

    private boolean isOpened;

    public Card(Rank rank, Suit suit) {
        super(rank, suit);
        isOpened = false;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        isOpened = true;
    }

    public List<Integer> scores() {
        return BlackjackRank.getScoresByRank(rank);
    }
}
