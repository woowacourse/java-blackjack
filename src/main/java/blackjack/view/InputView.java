package blackjack.view;

import blackjack.model.participant.Name;
import blackjack.model.participant.ParticipantAction;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitWithComma(trim(scanner.nextLine()));
    }

    private List<String> splitWithComma(String input) {
        return Arrays.stream(input.split(",", -1))
                .toList();
    }

    public ParticipantAction readHitOrNot(Name playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName.value());
        return parseParticipantAction(trim(scanner.nextLine()));
    }

    private ParticipantAction parseParticipantAction(String input) {
        if (input.equals("y")) {
            return ParticipantAction.HIT;
        }
        if (input.equals("n")) {
            return ParticipantAction.STAND;
        }
        throw new IllegalArgumentException("올바른 입력이 아닙니다. 입력: %s".formatted(input));
    }

    private String trim(String input) {
        return input.replaceAll(" ", "");
    }
}
