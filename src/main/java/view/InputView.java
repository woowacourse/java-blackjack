package view;

import domain.player.Gambler;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String DELIMITER = ",";

    public static List<String> readParticipantsName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toList());
    }

    public static boolean readWantHit(final Gambler gambler) {
        System.out.printf("\n%s는 한장의 카드를 더 받으시겠습니까?(예는 y, 아니오는 n)\n", gambler.nameValue());
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
}
