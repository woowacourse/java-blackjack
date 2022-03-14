package view;

import domain.participant.Name;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_NAMES_SPLIT_DELIMITER = ",";
    private static final String NAME_DUPLICATE_ERROR_MESSAGE = "[Error] 이름은 중복일 수 없습니다.";
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_ASk_DRAW_MESSAGE_FORMAT = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static Scanner scanner = new Scanner(System.in);

    public static List<Name> inputNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        String inputNames = scanner.nextLine();
        List<Name> names = Arrays.stream(inputNames.split(INPUT_NAMES_SPLIT_DELIMITER))
                .map(String::trim)
                .map(Name::new)
                .collect(Collectors.toList());
        validateNames(names);
        return names;
    }

    private static void validateNames(List<Name> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException(NAME_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public static boolean inputAskDraw(String name) {
        System.out.printf(INPUT_ASk_DRAW_MESSAGE_FORMAT, name);
        String askDraw = scanner.nextLine();
        validateAskDraw(askDraw);
        return askDraw.equals("y");
    }

    private static void validateAskDraw(String askDraw) {
        if (!(askDraw.equals("y") || askDraw.equals("n"))) {
            throw new IllegalArgumentException("[Error] 질문에 대한 대답은 y 혹은 n 만 가능합니다.");
        }
    }
}
