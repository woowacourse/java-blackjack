package blackjack.domain;

// TODO 딜러 싱글톤 여부 검토
public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;

    public Dealer(String name) {
        super(name);
    }

    public boolean isUnderTakeLimit() {
        return computeSumOfCards() < CARD_TAKE_LIMIT;
    }

    public JudgeResult judge(Player player) {
        if (isBust()) {
            return JudgeResult.WIN;
        }

        int dealerSum = computeSumOfCards();
        int playerSum = player.computeSumOfCards();
        // TODO Enum에서 조건에 따라 값 반환하도록 수정
        if (playerSum == dealerSum) {
            return JudgeResult.PUSH;
        }
        if (playerSum > dealerSum) {
            return JudgeResult.WIN;
        }
        return JudgeResult.LOSE;
    }

    public boolean isAvailable() {
        int sum = computeSumOfCards();
        return sum < CARD_TAKE_LIMIT;
    }
}
