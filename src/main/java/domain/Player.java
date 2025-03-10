package domain;

import domain.constant.BlackJackRules;
import domain.constant.GameResult;
import java.util.List;

public class Player {

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
        if (sum > BlackJackRules.BUST_STANDARD || dealerScore > BlackJackRules.BUST_STANDARD) {
            return getWinDrawLoseWhenOverBustStandard(sum);
        }
        return determineGameResult(dealerScore, sum);
    }

    private GameResult getWinDrawLoseWhenOverBustStandard(int sum) {
        if (sum > BlackJackRules.BUST_STANDARD) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }

    private GameResult determineGameResult(int dealerScore, int sum) {
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
        if (cards.getSize() != BlackJackRules.INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 " + BlackJackRules.INITIAL_CARD_COUNT + "장을 받아야 합니다.");
        }
    }

    public String getNickname() {
        return nickname.getValue();
    }
}
