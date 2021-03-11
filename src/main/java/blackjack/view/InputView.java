package blackjack.view;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER_COMMA = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return Arrays.asList(input.split(DELIMITER_COMMA));
    }


    public static int inputBettingMoney(Participant participant) {
        System.out.println(participant.getName() + "의 배팅 금액은?");
        int money = Integer.parseInt(scanner.nextLine());
        return money;
    }

    public static boolean inputAskMoreCard(Participant participant) {
        System.out.println(participant.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        if ("y".equals(input)) {
            return true;
        }

        if ("n".equals(input)) {
            return false;
        }

        throw new IllegalArgumentException("y 혹은 n만 입력할 수 있습니다.");
    }
}
