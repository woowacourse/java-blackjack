package blakcjack.view;


import blakcjack.domain.card.Card;
import blakcjack.domain.outcome.Outcome;
import blakcjack.dto.EarningDto;
import blakcjack.dto.EarningSummaryDto;
import blakcjack.dto.OutcomeSummaryDto;
import blakcjack.dto.ParticipantDto;
import blakcjack.dto.ParticipantsDto;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_TYPE = "딜러";

    public static void printInitialHands(final ParticipantsDto participantsDto) {
        final ParticipantDto dealer = participantsDto.getDealer();
        final List<ParticipantDto> players = participantsDto.getPlayers();
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(makeCardDistributionMessage(dealer, players));
        stringBuilder.append(makeCardSummary(dealer));
        for (final ParticipantDto player : players) {
            stringBuilder.append(makeCardSummary(player));
        }
        System.out.println(stringBuilder.toString());
    }

    private static String makeCardDistributionMessage(final ParticipantDto dealer, final List<ParticipantDto> players) {
        return String.format("%n%s 와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), concatenatePlayerNames(players));
    }

    private static String concatenatePlayerNames(final List<ParticipantDto> players) {
        return players.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printPlayerHand(final ParticipantDto participantDto) {
        System.out.print(makeCardSummary(participantDto));
    }

    private static String makeCardSummary(final ParticipantDto participantDto) {
        final List<Card> initialCards = getInitialCards(participantDto.getType(), participantDto.getCards());

        return String.format("%s: %s%n", participantDto.getName(),
                concatenateCardsInformation(initialCards));
    }

    private static List<Card> getInitialCards(final String type, final List<Card> cards) {
        if (DEALER_TYPE.equals(type)) {
            return Collections.singletonList(cards.get(0));
        }
        return cards;
    }

    private static String concatenateCardsInformation(final List<Card> cards) {
        return cards.stream()
                .map(OutputView::getCardInformation)
                .collect(Collectors.joining(", "));
    }

    private static String getCardInformation(final Card card) {
        return card.getCardNumber().getName() + card.getCardSymbol().getName();
    }

    public static void printDealerAdditionalCardMessage() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalHandsSummary(final ParticipantsDto participantsDto) {
        final ParticipantDto dealer = participantsDto.getDealer();
        final List<ParticipantDto> players = participantsDto.getPlayers();
        final StringBuilder stringBuilder = new StringBuilder(System.lineSeparator());

        stringBuilder.append(makeFinalSummary(dealer));
        for (final ParticipantDto player : players) {
            stringBuilder.append(makeFinalSummary(player));
        }
        System.out.println(stringBuilder.toString());
    }

    private static String makeFinalSummary(final ParticipantDto participant) {
        return String.format("%s - 결과: %d%n", makeFinalCardSummary(participant), participant.getScore());
    }

    private static String makeFinalCardSummary(final ParticipantDto participant) {
        return participant.getName() + "카드: " + concatenateCardsInformation(participant.getCards());
    }

    public static void printFinalOutcomeSummary(final OutcomeSummaryDto outcomeSummaryDto, final String dealerName) {
        System.out.println("## 최종 승패");
        printDealerOutcome(outcomeSummaryDto.getDealerOutcome(), dealerName);
        printPlayersOutcome(outcomeSummaryDto.getPlayersOutcome());
    }

    private static void printDealerOutcome(final Map<Outcome, Integer> dealerOutcome, final String dealerName) {
        final StringBuilder stringBuilder = new StringBuilder(dealerName)
                .append(":");
        dealerOutcome.forEach((outcome, count) ->
                stringBuilder.append(convertCountToString(count, outcome))
        );
        System.out.println(stringBuilder.toString());
    }

    private static String convertCountToString(final int count, final Outcome outcome) {
        if (count == 0) {
            return "";
        }
        return " " + count + outcome.getValue();
    }

    private static void printPlayersOutcome(final Map<String, Outcome> playersOutcome) {
        final StringBuilder stringBuilder = new StringBuilder();
        playersOutcome.forEach((name, outcome) ->
                stringBuilder.append(makePlayerOutcomeMessage(name, outcome))
        );
        System.out.println(stringBuilder.toString());
    }

    private static String makePlayerOutcomeMessage(final String name, final Outcome outcome) {
        return String.format("%s: %s%n", name, outcome.getValue());
    }

    public static void printFinalEarningSummary(final EarningSummaryDto earningSummaryDto) {
        System.out.println("## 최종 수익");
        printEarning(earningSummaryDto.getDealerEarning());
        printPlayerEarning(earningSummaryDto.getPlayerEarnings());

    }

    private static void printEarning(final EarningDto earningDto) {
        System.out.printf("%s: %d%n", earningDto.getName(), earningDto.getEarning());
    }

    private static void printPlayerEarning(final List<EarningDto> playerEarnings) {
        playerEarnings.forEach(OutputView::printEarning);
    }

    public static void printGameClosing(final String reason) {
        System.out.printf("게임을 종료합니다: %s%n", reason);
    }
}
