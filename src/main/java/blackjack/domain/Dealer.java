package blackjack.domain;

import java.util.List;

// TODO 딜러 싱글톤 여부 검토
public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;
    private static final int INITIAL_DEALER_CARD_OPEN_INDEX = 0;

    public Dealer(String name) {
        super(name);
    }

    public JudgeResult judge(Player player) {
        if (isBust()) {
            return JudgeResult.WIN;
        }

        int dealerSum = computeCardsScore();
        int playerSum = player.computeCardsScore();
        // TODO Enum에서 조건에 따라 값 반환하도록 수정
        if (playerSum == dealerSum) {
            return JudgeResult.PUSH;
        }
        if (playerSum > dealerSum) {
            return JudgeResult.WIN;
        }
        return JudgeResult.LOSE;
    }

    @Override
    public boolean isAvailable() {
        return computeCardsScore() < CARD_TAKE_LIMIT;
    }

    public Card openFirstCard() {
        List<Card> cards = getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("딜러가 아직 카드를 가지고 있지 않습니다.");
        }
        return getCards().get(INITIAL_DEALER_CARD_OPEN_INDEX);
    }
}
