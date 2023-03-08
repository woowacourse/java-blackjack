package blackjackgame.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readGuestsName() {
        String guestsName;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            guestsName = scanner.nextLine();
        } while (isBlank(guestsName));
        return List.of(guestsName.split(","));
    }

    private boolean isBlank(String guestsName) {
        boolean isBlank = guestsName.isBlank();
        if (isBlank) {
            printErrorMsg("참여할 사람 이름은 비어서는 안 됩니다.");
        }
        return isBlank;
    }

    public AddCardResponse readWantMoreCard(final String playerName) {
        Optional<AddCardResponse> addCardResponse;
        do {
            System.out.printf(AddCardResponse.printAddCardResponse(playerName));
            addCardResponse = AddCardResponse.findAndCreate(scanner.nextLine());
            if (addCardResponse.isEmpty()) {
                printErrorMsg(AddCardResponse.getErrorPowerMsg());
            }
        } while (addCardResponse.isEmpty());
        return addCardResponse.get();
    }

    public void printErrorMsg(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
