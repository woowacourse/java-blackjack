package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.BlackJack;
import model.Participant;
import model.Participants;

public class OutputView {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public void printDealOut(Participants participants, int round) {
        List<String> names = participants.getNames();
        StringBuilder sb = new StringBuilder(); // name1, name2,
        for (int i = 1; i < names.size(); i++) {
            sb.append(names.get(i) + ", ");
        }
        String joinedNames = String.join(", ", names.subList(1, names.size()));
        System.out.println("딜러와 " + joinedNames + "에게 2장을 나누었습니다.");

        for (Participant participant : participants) {
            String replaced = participant.open(round).toString().replaceAll("[]\\[]", "");
            System.out.printf("%s카드: %s", participant.getName(), replaced + LINE_SEPARATOR);
        }
    }

    public void printHands(Participant participant, int round) {
        String replaced = participant.open(round).toString().replaceAll("[]\\[]", "");
        System.out.printf("%s카드: %s", participant.getName(), replaced + LINE_SEPARATOR);
    }

    public void printHandsAndScore(Participants participants, int round) {
        for (Participant participant : participants) {
            System.out.printf("%s카드: %s - 결과: %d%s", participant.getName(), participant.open(round), participant.calculateScore(), LINE_SEPARATOR);
        }
    }

    public void printResult(Map<String, Integer> dealerResult, Map<String, Boolean> playerResult) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패%s", dealerResult.get("승"), dealerResult.get("패"), LINE_SEPARATOR);
        for (Entry<String, Boolean> entry : playerResult.entrySet()) {
            System.out.printf("%s: %s%s", entry.getKey(), entry.getValue() ? "승" : "패", LINE_SEPARATOR);
        }
    }
}
