package blackjack.domain;

public class Player extends Participant {

    private static final int BLACKJACK_THRESHOLD = 21;

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    public GameResult matchGame(Dealer dealer) {
        BlackJackState dealerState = BlackJackState.of(dealer);
        BlackJackState playerState = BlackJackState.of(this);

        if (BlackJackState.BLACKJACK.equals(dealerState) && BlackJackState.BLACKJACK.equals(playerState)) {
            return GameResult.DRAW;
        }
        if (BlackJackState.BUST.equals(dealerState) && BlackJackState.BUST.equals(playerState)) {
            return GameResult.LOSE;
        }
        if (BlackJackState.BLACKJACK.equals(playerState)) {
            return GameResult.WIN;
        }
        if (BlackJackState.OTHERS.equals(dealerState) && BlackJackState.OTHERS.equals(playerState)) {
            int dealerSum = dealer.calculateDenominations();
            int playerSum = this.calculateDenominations();
            if (dealerSum < playerSum) {
                return GameResult.WIN;
            }
        }
        return GameResult.LOSE;
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() < BLACKJACK_THRESHOLD;
    }

    private void validateName(String name) {
        boolean isAllLowerCase = name.chars().allMatch(Character::isLowerCase);
        if (isAllLowerCase) {
            return;
        }
        throw new IllegalArgumentException("이름은 알파벳 소문자만 입력 가능합니다.");
    }

    public String getName() {
        return name;
    }
}
