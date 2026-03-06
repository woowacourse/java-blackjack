package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public void printInitialDeal(List<String> names) {
        System.out.println("딜러와 "
        + String.join(", ", names)
        + "에게 2장을 나누었습니다.");
    }

    public void printInitialParticipantsInfo(Map<String, List<String>> participantsInfo) {
        for(Entry<String, List<String>> participant : participantsInfo.entrySet()) {
            System.out.println(participant.getKey() + "카드: " +
                    String.join(", ", participant.getValue()));
        }
    }

}
