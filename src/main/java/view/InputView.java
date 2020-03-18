package view;

import domain.gamer.dto.GamerDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static List<GamerDto> inputGamerDto() {
        return inputPlayerNames().stream()
                .map(name -> new GamerDto(name, inputBattingMoney(name)))
                .collect(Collectors.toList());
    }

    private static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNamesValue = scanner.nextLine();

        return Arrays.stream(playerNamesValue.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static int inputBattingMoney(String name) {
        System.out.println(String.format("\n%s의 배팅 금액은?", name));
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputGetMoreCard(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
        return scanner.nextLine();
    }
}
