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

    public void printDealCards(final ParticipantResponse dealer, final List<ParticipantResponse> players, final int count) {
        System.out.println();
        System.out.println(format("{0}와 {1}에게 {2}장을 나누었습니다.",
                dealer.getName(), getPlayerNamesFormat(players), count));

        printInitialHandedCardsForDealer(dealer);
        players.forEach(this::printHandedCardsWithoutScore);
    }

    private String getPlayerNamesFormat(final List<ParticipantResponse> players) {
        return players.stream()
                .map(ParticipantResponse::getName)
                .collect(Collectors.joining(", "));
    }

    private void printInitialHandedCardsForDealer(final ParticipantResponse dealer) {
        final CardsResponse cards = dealer.getCardsResponse();
        final List<String> cardInfos = cards.getCardInfos();

        System.out.println(format("{0}카드: {1}", dealer.getName(), cardInfos.get(0)));
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
        System.out.println("## 최종 수익");
        printDealerResult(dealer);
        players.forEach(this::printPlayerResult);
    }

    private void printDealerResult(final DealerResultResponse dealer) {
        final String dealerName = dealer.getName();
        final int dealerProfit = dealer.getProfit();

        System.out.println(format(RESULT_FORMAT, dealerName, dealerProfit));
    }

    private void printPlayerResult(final PlayerResultResponse player) {
        final String playerName = player.getName();
        final int playerProfit = player.getProfit();

        System.out.println(format(RESULT_FORMAT, playerName, playerProfit));
    }
}
