package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.GameResult;
import blackjack.domain.rule.ScoreRule;
import blackjack.domain.money.BattingMoney;

import java.util.ArrayList;
import java.util.List;

public class Player implements Participant {
    private static final int FROM = 0;
    private static final int TO = 2;
    private static final int DRAW_BOUND_SCORE = 21;
    private static final double BLACKJACK_WIN_RATE = 1.5;
    private static final double WIN_RATE = 1.0;
    private static final double DRAW_RATE = 0.0;
    private static final double LOSE_RATE = -1.0;

    private String name;
    private List<Card> cards;
    private ScoreRule scoreRule;
    private BattingMoney battingMoney;
    private boolean isContinue;

    public Player(String name, ScoreRule scoreRule) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.scoreRule = scoreRule;
        this.battingMoney = BattingMoney.ZERO;
        this.isContinue = true;
    }

    @Override
    public void receiveCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showInitCards() {
        return cards.subList(FROM, TO);
    }

    @Override
    public List<Card> showCards() {
        return cards;
    }

    @Override
    public boolean isReceiveCard() {
        int totalScore = scoreRule.sumTotalScore(cards);
        return totalScore <= DRAW_BOUND_SCORE;
    }

    @Override
    public int sumTotalScore() {
        return scoreRule.sumTotalScore(cards);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isBlackJack() {
        return cards.size() == 2 && sumTotalScore() == 21;
    }

    public boolean isContinue() {
        return isContinue;
    }

    public void endOwnTurn() {
        isContinue = false;
    }

    public void bet(final int bettingMoney) {
        this.battingMoney = battingMoney.add(bettingMoney);
    }

    public boolean isNotBatting() {
        return battingMoney.isZero();
    }

    public double calculateEarnings(final GameResult playerGameResult) {
        if (isBlackJack() && playerGameResult == GameResult.WIN) {
            return battingMoney.multiply(BLACKJACK_WIN_RATE);
        }

        if (playerGameResult == GameResult.WIN) {
            return battingMoney.multiply(WIN_RATE);
        }

        if (playerGameResult == GameResult.DRAW) {
            return battingMoney.multiply(DRAW_RATE);
        }

        return battingMoney.multiply(LOSE_RATE);
    }
}
