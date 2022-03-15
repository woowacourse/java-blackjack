package blackjack.view;

import static java.lang.System.out;

import blackjack.domain.participant.Participant;
import java.util.Locale;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputParticipantsNames() {
        out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return SCANNER.nextLine();
    }

    public static boolean inputOneMoreCard(Participant player) {
        out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName());
        try {
            String input = SCANNER.nextLine().toLowerCase(Locale.ROOT);
            return validateCardOption(input);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
            return inputOneMoreCard(player);
        }
    }

    private static boolean validateCardOption(String input) {
        if ("y".equals(input) || "n".equals(input)) {
            return "y".equals(input);
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n을 입력하셔야합니다.");
    }
}
