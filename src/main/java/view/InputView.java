package view;

import domain.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String DELIMITER = ", ";

    public static List<String> readParticipantsName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toList());
    }

    public static String readMoreCard(final Player player) {
        System.out.printf("\n%s는 한장의 카드를 더 받으시겠습니까?(예는 y, 아니오는 n)\n", player.name().value());
        return scanner.nextLine();
    }
}
