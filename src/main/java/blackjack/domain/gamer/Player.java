package blackjack.domain.gamer;

public class Player extends BlackjackGamer {

    private static final int BLACKJACK_MAX_SCORE = 21;

    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    public Player(Hand hand, Name name) {
        super(hand);
        this.name = name;
    }

    @Override
    public boolean canReceiveCard() {
        return getValue() < BLACKJACK_MAX_SCORE;
    }

    public GameResult compete(Score dealerScore) {
        Score playerScore = getScore();
        if (isOnlyPlayerBlackjack(playerScore, dealerScore)) {
            return GameResult.BLACKJACK_WIN;
        }
        if (playerScore.isBust() || isLowerThanDealerHandValue(playerScore, dealerScore)) {
            return GameResult.LOSE;
        }
        if (dealerScore.isBust() || isHigherThanDealerHandValue(playerScore, dealerScore)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private boolean isOnlyPlayerBlackjack(Score playerScore, Score dealerScore) {
        return playerScore.isBlackjack() && dealerScore.isNotBlackjack();
    }

    private boolean isLowerThanDealerHandValue(Score playerScore, Score dealerScore) {
        return dealerScore.isNotBust() && playerScore.isLowerThan(dealerScore);
    }

    private boolean isHigherThanDealerHandValue(Score playerScore, Score dealerScore) {
        return playerScore.isNotBust() && playerScore.isHigherThan(dealerScore);
    }

    public Name getName() {
        return name;
    }
}
