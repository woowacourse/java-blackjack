package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> receiveNameInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉽표 기준으로 분리)");
        String nameInput = SCANNER.nextLine();
        return convertToPlayerNames(nameInput);
    }

    private static List<String> convertToPlayerNames(String nameInput) {
        List<String> names = Arrays.asList(nameInput.split(",", -1));
        return names.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String receiveYesOrNoInput(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        return SCANNER.nextLine();
    }
}
