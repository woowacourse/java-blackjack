package domain;

public abstract class Gamer {

    private final Nickname nickname;
    private final Hand hand;

    public Gamer(final Nickname nickname, final Hand hand) {
        this.nickname = nickname;
        this.hand = hand;

    }

    public void receiveCard(final Card card) {
        hand.add(card);
    }

    public int calculateSumOfRank() {
        return hand.getSumOfRank();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
