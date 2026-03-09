package view;

import domain.CardDto;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printGameInitResult(Map<String, CardDto> result) {
        String playersName = result.keySet().stream()
                .filter(name -> !name.equals("딜러"))
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", playersName);

        for (Map.Entry<String, CardDto> entry : result.entrySet()) {
            String name = entry.getKey();

            printParticipantCard(name, entry.getValue().getFormattedCards());
        }
    }

    public void printParticipantCard(String name, String cards) {
        System.out.printf("%s카드: %s%n", name, cards);
    }
}
