package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printBlackJackResults(BlackJackResults blackJackResults) {
        System.out.println(createBlackJackResult(blackJackResults));
    }

    public static void printParticipantsInitCards(final Dealer dealer, final List<Player> players) {
        System.out.println(String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName().getValue(), getPlayerNames(players)));
        String dealerInitCards = getParticipantCards(dealer, 1);
        String playersInitCards = players.stream()
                .map(player -> getParticipantCards(player, 2))
                .collect(Collectors.joining());

        System.out.println(dealerInitCards + playersInitCards);
    }

    public static void printParticipantCardWithResult(final Participant participant, final int totalPoint) {
        String playerCards = getParticipantCards(participant, participant.openAll());
        String playerTotalPoint = String.format(" - 결과: %d", totalPoint);
        System.out.println(playerCards + playerTotalPoint);
    }

    public static void printParticipantsCards(final Participant participant) {
        String playerCards = getParticipantCards(participant, participant.openAll());

        System.out.println(playerCards);
    }

    public static void printDealerHit(final int hitCount) {
        System.out.println(String.format("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n", hitCount));
    }

    private static String createBlackJackResult(BlackJackResults blackJackResults) {
        Map<Name, BlackJackResult> participants = blackJackResults.getParticipants();
        StringBuilder stringBuilder = new StringBuilder();

        for (final Name name : participants.keySet()) {
            Map<ResultType, Integer> results = participants.get(name).getResults();
            stringBuilder.append(getParticipantResult(name, results));
        }

        return stringBuilder.toString();
    }

    private static String getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(player -> player.getName().getValue())
                .collect(Collectors.joining(", "));
    }

    private static String getParticipantCards(final Participant participant, final int openCount) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(participant.getName().getValue())
                .append(": ")
                .append(participant.open(openCount)
                        .stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(", ")))
                .append(System.lineSeparator());
        return messageBuilder.toString();
    }

    private static String getParticipantResult(final Name name, final Map<ResultType, Integer> results) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name.getValue()).append(": ");
        results.entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> {
                    int resultTypeCount = entry.getValue();
                    String resultType = entry.getKey().getType();
                    stringBuilder.append(resultTypeCount).append(resultType);
                });
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
