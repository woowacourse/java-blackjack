package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.participant.Participant;
import model.Participants;

public final class OutputView {

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

    public void printBustState(String name, int score) {
        System.out.printf("%s는 %d점이므로 21점 초과로 버스트입니다.%s", name, score, LINE_SEPARATOR);
    }

    public void printDealerDraw() {
        System.out.println(LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다." + LINE_SEPARATOR);
    }

    public void printResultRevenue(Map<String, Integer> calculatedRevenue) {
        System.out.println("## 최종 수익");
        for (Entry<String, Integer> entry : calculatedRevenue.entrySet()) {
            System.out.printf("%s: %d%s", entry.getKey(), entry.getValue(), LINE_SEPARATOR);
        }
    }
}
