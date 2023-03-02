package blackjack.view;

import java.util.List;

public class OutputView {

    public void printParticipants(List<String> participants) {
        StringBuilder stringBuilder = new StringBuilder(participants.remove(0));
        stringBuilder.append("와 ");
        stringBuilder.append(String.join(", ", participants));
        stringBuilder.append("에게 2장을 나누었습니다.");
        System.out.println(stringBuilder);
    }

}
