package blackjack.view;

import blackjack.domain.BlackJackResult;
import blackjack.domain.BlackJackResults;
import blackjack.domain.Name;
import blackjack.domain.ResultType;

import java.util.Map;

public class OutputView {
    private OutputView() {}

    public static void printBlackJackResults(BlackJackResults blackJackResults) {
        System.out.println(createBlackJackResult(blackJackResults));
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
