package blackjack.domain;

public class Player {

    private final PlayerName name;
    private final Hand hand;

    public Player(PlayerName name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void appendCard(Card card) {
        hand.append(card);
    }

    public boolean handSummationExceed(int target) {
        return hand.sum() > target;
    }
}
