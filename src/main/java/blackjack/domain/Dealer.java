package blackjack.domain;

public class Dealer extends Player {

    private static final int CONDITION_HIT = 16;
    private static final String NAME = "딜러";
    private static final int BUST_CONDITION = 22;
    private static final int LOSE_SCORE = 0;

    private final Deck deck;

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

    public Result isWin(Player player) {
        int playerScore = checkScoreIsBust(player);
        int dealerScore = checkScoreIsBust(this);
        return Result.valueOf(Integer.compare(dealerScore, playerScore));
    }

    private int checkScoreIsBust(Player player) {
        if (player.score() >= BUST_CONDITION) {
            return LOSE_SCORE;
        }
        return player.score();
    }
}
