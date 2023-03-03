package blackjack.view;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;
import static java.text.MessageFormat.format;

import blackjack.domain.result.Result;
import blackjack.view.dto.CardsResponse;
import blackjack.view.dto.DealerStateResponse;
import blackjack.view.dto.ParticipantResponse;
import blackjack.view.dto.PlayerResultResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String RESULT_FORMAT = "{0}: {1}";

    public void printDealCards(final ParticipantResponse dealer, final List<ParticipantResponse> players,
                               final int count) {
        System.out.println();
        System.out.println(format("{0}와 {1}에게 {2}장을 나누었습니다.",
                dealer.getName(), getPlayerNamesFormat(players), count));

        printHandedCardsWithoutScore(dealer);
        players.forEach(this::printHandedCardsWithoutScore);
    }

    private String getPlayerNamesFormat(final List<ParticipantResponse> players) {
        return players.stream()
                .map(ParticipantResponse::getName)
                .collect(Collectors.joining(", "));
    }

    public void printHandedCardsWithoutScore(final ParticipantResponse participant) {
        System.out.println(getHandedCards(participant));
    }

    private String getHandedCards(final ParticipantResponse participant) {
        final CardsResponse cardsResponse = participant.getCardsResponse();
        final List<String> cardInfos = cardsResponse.getCardInfos();

        return format("{0}카드: {1}", participant.getName(), getCardInfosFormat(cardInfos));
    }

    private String getCardInfosFormat(final List<String> cardInfos) {
        return String.join(", ", cardInfos);
    }

    public void printDealerCardDrawn(final DealerStateResponse dealerStateResponse) {
        if (dealerStateResponse.isDrawable()) {
            System.out.println();
            System.out.println(format("딜러는 {0}이하라 한장의 카드를 더 받았습니다.", dealerStateResponse.getLimit()));
            System.out.println();
        }
    }

    public void printHandedCardsWithScore(final ParticipantResponse participant) {
        final CardsResponse cardsResponse = participant.getCardsResponse();
        final int totalScore = cardsResponse.getTotalScore();

        System.out.println(format("{0} - {1}", getHandedCards(participant), getScoreFormat(totalScore)));
    }

    private String getScoreFormat(final int totalScore) {
        return format("결과: {0}", totalScore);
    }

    public void printResult(final String dealerName, final List<PlayerResultResponse> players) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println(format(RESULT_FORMAT, dealerName, getDealerResult(players)));
        players.forEach(this::printPlayerResult);
    }

    private String getDealerResult(final List<PlayerResultResponse> players) {
        final Map<Result, Integer> playerResult = initPlayerResult();
        for (PlayerResultResponse player : players) {
            final Result result = player.getResult();
            playerResult.put(result, playerResult.getOrDefault(result, 0) + 1);
        }

        return format("{0}승 {1}무 {2}패", playerResult.get(LOSE), playerResult.get(DRAW), playerResult.get(WIN));
    }

    private Map<Result, Integer> initPlayerResult() {
        final Map<Result, Integer> playerResult = new HashMap<>();
        for (final Result result : Result.values()) {
            playerResult.put(result, 0);
        }
        return playerResult;
    }

    private void printPlayerResult(final PlayerResultResponse player) {
        System.out.println(format(RESULT_FORMAT, player.getName(), player.getResult().getName()));
    }
}
