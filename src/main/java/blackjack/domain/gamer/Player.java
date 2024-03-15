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
        return getScore() < BLACKJACK_MAX_SCORE;
    }

    public GameResult compete(HandValue dealerHandValue) {
        HandValue playerHandValue = getHandValue();
        if (isOnlyPlayerBlackjack(playerHandValue, dealerHandValue)) {
            return GameResult.BLACKJACK_WIN;
        }
        if (playerHandValue.isBust() || isLowerThanDealerHandValue(playerHandValue, dealerHandValue)) {
            return GameResult.LOSE;
        }
        if (dealerHandValue.isBust() || isHigherThanDealerHandValue(playerHandValue, dealerHandValue)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private boolean isOnlyPlayerBlackjack(HandValue playerHandValue, HandValue dealerHandValue) {
        return playerHandValue.isBlackjack() && dealerHandValue.isNotBlackjack();
    }

    private boolean isLowerThanDealerHandValue(HandValue playerHandValue, HandValue dealerHandValue) {
        return dealerHandValue.isNotBust() && playerHandValue.isLowerThan(dealerHandValue);
    }

    private boolean isHigherThanDealerHandValue(HandValue playerHandValue, HandValue dealerHandValue) {
        return playerHandValue.isNotBust() && playerHandValue.isHigherThan(dealerHandValue);
    }

    public Name getName() {
        return name;
    }
}
