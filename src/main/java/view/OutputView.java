package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.participant.Participant;
import model.Participants;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printDealOut(Participants participants) {
        List<String> names = participants.getNames();
        String joinedNames = String.join(", ", names.subList(1, names.size()));
        System.out.println(LINE_SEPARATOR + "딜러와 " + joinedNames + "에게 2장을 나누었습니다.");

        for (Participant participant : participants) {
            String replaced = participant.open().toString().replaceAll("[]\\[]", "");
            System.out.printf("%s카드: %s", participant.getName(), replaced + LINE_SEPARATOR);
        }

        System.out.println();
    }

    public void printHands(Participant participant) {
        String replaced = participant.open().toString().replaceAll("[]\\[]", "");
        System.out.printf("%s카드: %s", participant.getName(), replaced + LINE_SEPARATOR);
    }

    public void printHandsAndScore(Participants participants) {
        for (Participant participant : participants) {
            System.out.printf("%s카드: %s - 결과: %d%s", participant.getName(), participant.open(),
                    participant.calculateScore(), LINE_SEPARATOR);
        }

        System.out.println();
    }

    public void printResult(Map<String, Integer> dealerResult, Map<String, Boolean> playerResult) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패%s", dealerResult.getOrDefault("승", 0), dealerResult.getOrDefault("패", 0),
                LINE_SEPARATOR);
        for (Entry<String, Boolean> entry : playerResult.entrySet()) {
            System.out.printf("%s: %s%s", entry.getKey(), entry.getValue() ? "승" : "패", LINE_SEPARATOR);
        }
    }

    public void printBustState(String name, int score) {
        System.out.printf("%s는 %d점이므로 21점 초과로 버스트입니다.%s", name, score, LINE_SEPARATOR);
    }

    public void printDealerDraw() {
        System.out.println(LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다." + LINE_SEPARATOR);
    }
}
