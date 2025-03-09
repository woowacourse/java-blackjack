package domain;

import domain.constant.GameResult;
import java.util.List;

public class Player {

    public static final int BUST_STANDARD = 21;
    public static final int INITIAL_CARD_COUNT = 2;

    private final Nickname nickname;
    private final Cards cards;

    public Player(Nickname nickname, Cards cards) {
        validateInitialCardsSize(cards);
        this.nickname = nickname;
        this.cards = cards;
    }

    public boolean addOneCard(Card card) {
        return cards.addOneCard(card);
    }

    public int sumCardNumbers() {
        return cards.sumCardNumbers();
    }

    public GameResult compareTo(int dealerScore) {
        int sum = sumCardNumbers();
        if (sum > BUST_STANDARD || dealerScore > BUST_STANDARD) {
            return getWinDrawLoseWhenOverBustStandard(sum);
        }
        return getWinDrawLose(dealerScore, sum);
    }

    private GameResult getWinDrawLoseWhenOverBustStandard(int sum) {
        if (sum > BUST_STANDARD) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }

    private GameResult getWinDrawLose(int dealerScore, int sum) {
        if (sum == dealerScore) {
            return GameResult.DRAW;
        }
        if (sum > dealerScore) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    private void validateInitialCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 " + INITIAL_CARD_COUNT + "장을 받아야 합니다.");
        }
    }

    public String getNickname() {
        return nickname.getValue();
    }
}
