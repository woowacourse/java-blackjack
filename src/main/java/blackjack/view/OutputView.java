package blackjack.view;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;
import static java.text.MessageFormat.format;

import blackjack.domain.result.Result;
import blackjack.view.dto.CardsResponse;
import blackjack.view.dto.DealerResultResponse;
import blackjack.view.dto.DealerStateResponse;
import blackjack.view.dto.ParticipantResponse;
import blackjack.view.dto.PlayerResultResponse;
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
        final CardsResponse cards = participant.getCardsResponse();
        final List<String> cardInfos = cards.getCardInfos();

        return format("{0}카드: {1}", participant.getName(), getCardInfosFormat(cardInfos));
    }

    private String getCardInfosFormat(final List<String> cardInfos) {
        return String.join(", ", cardInfos);
    }

    public void printDealerCardDrawn(final DealerStateResponse dealerStateResponse) {
        if (dealerStateResponse.isDrawable()) {
            System.out.println();
            System.out.println(format("딜러는 {0}이하라 한장의 카드를 더 받았습니다.", dealerStateResponse.getThresholdScore()));
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

    public void printResult(final DealerResultResponse dealer, final List<PlayerResultResponse> players) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerResult(dealer);
        players.forEach(this::printPlayerResult);
    }

    private void printDealerResult(final DealerResultResponse dealer) {
        String dealerName = dealer.getName();
        Map<Result, Integer> resultMap = dealer.getResultMap();

        Integer dealerWin = resultMap.get(WIN);
        Integer dealerDraw = resultMap.get(DRAW);
        Integer dealerLose = resultMap.get(LOSE);

        System.out.println(format(RESULT_FORMAT + "{2}승 {3}무 {4}패", dealerName, dealerWin, dealerDraw, dealerLose));
    }

    private void printPlayerResult(final PlayerResultResponse player) {
        System.out.println(format(RESULT_FORMAT, player.getName(), player.getResult().getName()));
    }
}
