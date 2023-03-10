package view;

import domain.player.Gambler;
import domain.player.Name;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ",";

    public static List<String> readParticipantsName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return Arrays.asList(input.split(DELIMITER));
    }

    public static boolean readWantHit(final Gambler gambler) {
        final String message = String.format("%s는 한장의 카드를 더 받으시겠습니까?(예는 y, 아니오는 n)", gambler.nameValue());
        System.out.println(NEW_LINE + message);
        return parseHitCommand(scanner.nextLine());
    }

    private static boolean parseHitCommand(final String input) {
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y 혹은 n 만을 입력해주세요");
    }

    public static int readBettingMoney(final Name name) {
        System.out.println(name.value() + "의 배팅 금액은?");
        final int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}
