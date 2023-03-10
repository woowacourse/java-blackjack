package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_CARD_COMMAND_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private final Scanner scanner;
    private final InputValidator validator;

    public InputView(final Scanner scanner, final InputValidator validator) {
        this.scanner = scanner;
        this.validator = validator;
    }

    public int inputBetAmount(final String player) {
        System.out.println(player +"의 배팅 금액은?");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<String> inputParticipantsName() {
        System.out.println(INPUT_PLAYER_MESSAGE);
        String line = scanner.nextLine();
        validator.validateNotBlank(line);

        String[] splitNames = line.split(",");

        return trimList(List.of(splitNames));
    }

    private List<String> trimList(final List<String> targets) {
        List<String> trimmedList = new ArrayList<>();
        for (String target : targets) {
            trimmedList.add(target.trim());
        }

        return trimmedList;
    }

    public String inputCardCommand(final String player) {
        System.out.println(player + INPUT_CARD_COMMAND_MESSAGE);
        String line = scanner.nextLine();
        validator.validateNotBlank(line);
        return line;
    }
}
