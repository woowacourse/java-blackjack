package blackjack.domain;

public class Dealer extends Player {

    private static final int CONDITION_HIT = 16;
    private static final String NAME = "딜러";

    private Deck deck;

    public Dealer() {
        super(NAME);
        deck = Deck.generateDeck();
    }

    public Card handOutCard(NumberGenerator numberGenerator) {
        return deck.randomPick(numberGenerator);
    }

    public boolean isHit() {
        return (score() <= CONDITION_HIT);
    }

    public int isWin(Player player) {
        return Integer.compare(this.score(), player.score());
    }
}
