package blackjack.view;

import static java.text.MessageFormat.format;

import blackjack.controller.dto.CardsResponse;
import blackjack.controller.dto.DealerStateResponse;
import blackjack.controller.dto.ParticipantResponse;
import blackjack.controller.dto.ParticipantResultResponse;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printDealCards(
            final ParticipantResponse dealer,
            final List<ParticipantResponse> players,
            final int count
    ) {
        System.out.println(LINE_SEPARATOR + format("{0}와 {1}에게 {2}장을 나누었습니다.",
                dealer.getName(), formattedPlayerNames(players), count));
        printCardsWithoutScore(dealer);
        players.forEach(this::printCardsWithoutScore);
        System.out.println();
    }

    private String formattedPlayerNames(final List<ParticipantResponse> participants) {
        return participants.stream()
                .map(ParticipantResponse::getName)
                .collect(Collectors.joining(", "));
    }

    public void printCardsWithoutScore(final ParticipantResponse participant) {
        System.out.println(formattedCardsWithoutScore(participant));
    }

    private String formattedCardsWithoutScore(final ParticipantResponse participant) {
        final CardsResponse cards = participant.getCardsResponse();
        final List<String> cardInfos = cards.createCardInfos();
        return format("{0}카드: {1}", participant.getName(), formattedCardInfos(cardInfos));
    }

    private String formattedCardInfos(final List<String> cardInfos) {
        return String.join(", ", cardInfos);
    }

    public void printDealerDrawn(final DealerStateResponse dealer) {
        if (dealer.isDrawn()) {
            System.out.println(LINE_SEPARATOR + format("딜러는 {0}이하라 한장의 카드를 더 받았습니다.", dealer.getLimit()));
        }
    }

    public void printCardsWithScore(final List<ParticipantResponse> participants) {
        System.out.println();
        participants.forEach(this::printCardsWithScore);
    }

    private void printCardsWithScore(final ParticipantResponse participant) {
        final CardsResponse cards = participant.getCardsResponse();
        final int totalScore = cards.getScore();
        System.out.println(
                format("{0} - {1}", formattedCardsWithoutScore(participant), formattedScore(totalScore)));
    }

    private String formattedScore(final int totalScore) {
        return format("결과: {0}", totalScore);
    }

    public void printFinalResult(final List<ParticipantResultResponse> participants) {
        System.out.println(LINE_SEPARATOR + "## 최종 수익");
        participants.forEach(this::printParticipantProfit);
    }

    private void printParticipantProfit(ParticipantResultResponse participant) {
        System.out.println(format("{0}: {1}", participant.getName(), participant.getProfit()));
    }
}
