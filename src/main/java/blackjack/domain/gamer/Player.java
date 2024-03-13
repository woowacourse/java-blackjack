package blackjack.domain.gamer;

public class Player extends BlackjackGamer {

    private static final int BLACKJACK_MAX_SCORE = 21;

    private final Name name;

    public Player(Name name) {
        this.name = name;
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

        if (playerHandValue.isBlackjack() && !dealerHandValue.isBlackjack()) {
            return GameResult.BLACKJACK_WIN;
        }
        // 플레이어 점수가 21보다 클경우 패배
        if (playerHandValue.getScore() > BLACKJACK_MAX_SCORE) {
            return GameResult.LOSE;
        }
        // 딜러의 점수가 21보다 클경우 승리
        if (dealerHandValue.getScore() > BLACKJACK_MAX_SCORE) {
            return GameResult.WIN;
        }
        // 플레이어 점수가 딜러 점수보다 작으면 패배
        if (playerHandValue.getScore() < dealerHandValue.getScore()) {
            return GameResult.LOSE;
        }
        // 플레이어 점수가 딜러보다 클 경우 승리
        if (playerHandValue.getScore() > dealerHandValue.getScore()) {
            return GameResult.WIN;
        }
        if (dealerHandValue.isBlackjack() && !playerHandValue.isBlackjack()) {
            return GameResult.LOSE;
        }

        return GameResult.DRAW;
    }

    public Name getName() {
        return name;
    }
}
