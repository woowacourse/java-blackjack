package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;
import blackjack.dto.DealerResultDto;
import blackjack.dto.ScoreResultDto;

import java.util.*;
import java.util.stream.Collectors;

public class Dealer implements Participant {
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

    public DealerResultDto getDealerResult(List<Player> players) {
        List<ScoreResultDto> scoreResultDtos = decideWinOrLoseResults(players);
        Map<GameResult, Long> dealerResult = statisticsDealerResult(scoreResultDtos);

        Arrays.stream(GameResult.values())
                .forEach(gameResult -> dealerResult.putIfAbsent(gameResult, 0L));
        return new DealerResultDto(name, dealerResult);
    }

    public List<ScoreResultDto> decideWinOrLoseResults(List<Player> players) {
        return players.stream()
                .map(player -> new ScoreResultDto(player.getName(), this.judgeResult(player)))
                .collect(Collectors.toList());
    }

    private Map<GameResult, Long> statisticsDealerResult(List<ScoreResultDto> scoreResultDtos) {
        return scoreResultDtos.stream()
                .map(scoreResultDto -> scoreResultDto.getGameResult())
                .collect(Collectors.groupingBy(GameResult::reverse,
                        () -> new EnumMap<>(GameResult.class),
                        Collectors.counting()));
    }

    private GameResult judgeResult(Player player) {
        return GameResult.valueOf(player.sumTotalScore(), sumTotalScore());
    }
}
