package domain.card;

public class BlackjackCard extends Card {

    private boolean isOpened;

    public BlackjackCard(Rank rank, Suit suit) {
        super(rank, suit);
        isOpened = false;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        isOpened = true;
    }
}
