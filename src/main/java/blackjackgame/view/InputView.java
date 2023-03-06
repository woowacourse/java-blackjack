package blackjackgame.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    public static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readGuestsName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String guestsName = scanner.nextLine();
        validateBlank(guestsName);
        return List.of(guestsName.split(DELIMITER));
    }

    private void validateBlank(final String guestsName) {
        if (guestsName.isBlank()) {
            printErrorMsg("참여할 사람 이름은 비어서는 안 됩니다.");
            readGuestsName();
        }
    }

    public AddCardResponse readWantMoreCard(final String playerName) {
        System.out.printf(AddCardResponse.printAddCardResponse(playerName));
        try {
            return AddCardResponse.validate(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            printErrorMsg(e.getMessage());
            return readWantMoreCard(playerName);
        }
    }

    public void printErrorMsg(final String message) {
        System.out.println(message);
    }
}
