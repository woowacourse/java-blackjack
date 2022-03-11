package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.Participator;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        return splitAndTrim(SCANNER.nextLine());
    }

    private static List<String> splitAndTrim(String inputPlayerNames) {
        return Arrays.stream(inputPlayerNames.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static boolean inputHitResponse(Participator player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)%n", player.getPlayerName().getValue());
        return convertToBoolean(SCANNER.nextLine());
    }

    private static boolean convertToBoolean(String hitResponse) {
        if (isValidHitResponse(hitResponse)) {
            return hitResponse.equalsIgnoreCase("y");
        }
        throw new IllegalArgumentException("Y 또는 N을 입력해주세요.(대소문자 상관없습니다.)");
    }

    private static boolean isValidHitResponse(String hitResponse) {
        return hitResponse.equalsIgnoreCase("y") || hitResponse.equalsIgnoreCase("n");
    }
}
