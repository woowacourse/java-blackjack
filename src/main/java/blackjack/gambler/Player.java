package blackjack.gambler;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.constant.MatchResult;
import java.util.List;

public class Player extends Gambler {

    public static final int MAX_SCORE = 21;

    private final Nickname nickname;

    public Player(Nickname nickname, Cards cards) {
        super(cards);
        this.nickname = nickname;
    }

    public MatchResult compareTo(int dealerScore) {
        int sum = sumCardScores();
        if (sum > MAX_SCORE || dealerScore > MAX_SCORE) {
            return getMatchResultWhenOverBustStandard(sum);
        }
        return getMatchResult(dealerScore, sum);
    }

    @Override
    public List<Card> openInitialCards() {
        return cards.openPlayerInitialCards();
    }

    private MatchResult getMatchResultWhenOverBustStandard(int sum) {
        if (sum > MAX_SCORE) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }

    private MatchResult getMatchResult(int dealerScore, int sum) {
        if (sum == dealerScore) {
            return MatchResult.DRAW;
        }
        if (sum > dealerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }

    public String getNickname() {
        return nickname.getValue();
    }
}
