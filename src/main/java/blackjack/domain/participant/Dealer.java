package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ScoreResult;
import blackjack.domain.rule.ScoreRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer implements Participant {
    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DRAW_BOUND_SCORE = 16;
    private static final String DEALER_NAME = "딜러";
    private static final int BLACKJACK_NUMBER = 21;

    private final String name;
    private final List<Card> cards;
    private final ScoreRule scoreRule;

    public Dealer(ScoreRule scoreRule) {
        this.name = DEALER_NAME;
        this.cards = new ArrayList<>();
        this.scoreRule = scoreRule;
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
        return cards.size() == 2 && sumTotalScore() == BLACKJACK_NUMBER;
    }

    public DealerResult getDealerResult(List<Player> players) {
        List<ScoreResult> scoreResults = decideWinOrLoseResults(players);
        Map<GameResult, List<ScoreResult>> dealerResult = statisticsDealerResult(scoreResults);

        return new DealerResult(name, dealerResult);
    }

    public List<ScoreResult> decideWinOrLoseResults(List<Player> players) {
        return players.stream()
                .map(player -> new ScoreResult(player, this.judgeResult(player)))
                .collect(Collectors.toList());
    }

    private Map<GameResult, List<ScoreResult>> statisticsDealerResult(List<ScoreResult> scoreResults) {
        return scoreResults.stream()
                .collect(Collectors.groupingBy(ScoreResult::getGameResult));
    }

    private GameResult judgeResult(Player player) {
        return GameResult.valueOf(player.sumTotalScore(), sumTotalScore());
    }
}
