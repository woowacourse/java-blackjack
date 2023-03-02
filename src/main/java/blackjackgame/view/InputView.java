package blackjackgame.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public List<String> readGuestsName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String guestsName = scanner.nextLine();
        validateBlank(guestsName);
        return List.of(guestsName.split(","));
    }

    private void validateBlank(String guestsName) {
        if (guestsName.isBlank()) {
            throw new IllegalArgumentException("참여할 사람 이름은 비어서는 안 됩니다.");
        }
    }

    public AddCardResponse readWantMoreCard(String playerName) {
        System.out.printf(AddCardResponse.printAddCardResponse(playerName));
        try {
            return AddCardResponse.validate(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            return readWantMoreCard(playerName);
        }
    }
}
