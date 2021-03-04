package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;
import blackjack.dto.ScoreResultDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer implements Participant {
    public static final int SCORE_LIMIT = 21;
    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DRAW_BOUND_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

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

    public List<ScoreResultDto> decideWinOrLose(List<Player> players) {
        int dealerTotalSum = sumTotalScore();

        if (dealerTotalSum > SCORE_LIMIT) {
            return players.stream()
                    .map(player -> new ScoreResultDto(player.getName(), GameResult.WIN))
                    .collect(Collectors.toList());
        }

        return players.stream()
                .map(player -> new ScoreResultDto(player.getName(), GameResult.get(player, dealerTotalSum)))
                .collect(Collectors.toList());
    }
}
