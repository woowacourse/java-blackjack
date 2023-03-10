package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.BettingResults;
import blackjack.domain.participant.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printBettingResults(final BettingResults bettingResults) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        System.out.println(createBettingResults(bettingResults));
    }

    public static void printParticipantsInitCards(final Dealer dealer, final List<Player> players) {
        System.out.println(String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName().getValue(), getPlayerNames(players)));
        final String dealerInitCards = getParticipantCards(dealer, dealer.open(1));
        final String playersInitCards = getParticipantsInitCards(players);

        System.out.println(dealerInitCards);
        System.out.println(playersInitCards);
    }

    public static void printParticipantCardWithResult(final Participant participant, final int totalPoint) {
        final String playerCards = getParticipantCards(participant, participant.openAll());
        final String playerTotalPoint = String.format(" - 결과: %d", totalPoint);
        System.out.println(playerCards + playerTotalPoint);
    }

    public static void printParticipantsCards(final Participant participant) {
        final String playerCards = getParticipantCards(participant, participant.openAll());

        System.out.println(playerCards);
    }

    public static void printDealerHit(final int hitCount) {
        System.out.println(String.format("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n", hitCount));
    }

    private static String getParticipantsInitCards(final List<Player> players) {
        return players.stream()
                .map(player -> getParticipantCards(player, player.open(2)))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String createBettingResults(final BettingResults bettingResults) {
        final StringBuilder resultBuilder = new StringBuilder();
        final Map<Participant, Money> playerResults = bettingResults.getPlayerBettingResults();
        playerResults.forEach((participant, money) -> resultBuilder
                .append(participant.getName().getValue())
                .append(": ")
                .append(money.getValue())
                .append(System.lineSeparator())
        );

        return resultBuilder.toString();
    }

    private static String getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(player -> player.getName().getValue())
                .collect(Collectors.joining(", "));
    }

    private static String getParticipantCards(final Participant participant, final List<Card> participantCards) {
        return new StringBuilder().append(participant.getName().getValue())
                .append(": ")
                .append(participantCards.stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(", ")))
                .toString();
    }
}
