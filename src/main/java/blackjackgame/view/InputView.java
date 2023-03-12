package blackjackgame.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    public static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readGuestNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String guestsName = scanner.nextLine();
        validateBlank(guestsName);
        return List.of(guestsName.split(DELIMITER));
    }

    private void validateBlank(final String guestsName) {
        if (guestsName.isBlank()) {
            printErrorMsg("참여할 사람 이름은 비어서는 안 됩니다.");
            readGuestNames();
        }
    }

    public int readBettingMoney(final String guestName) {
        System.out.println();
        System.out.println(guestName + "의 베팅 금액은?");
        try {
            return validateInteger(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("베팅 금액으로 양의 정수를 입력해주세요.");
            return readBettingMoney(guestName);
        }
    }

    private int validateInteger(final String inputMoney) {
        return Integer.parseInt(inputMoney);
    }

    public AddCardRequest readWantMoreCard(final String playerName) {
        System.out.println();
        System.out.println(AddCardRequest.printAddCardRequest(playerName));
        try {
            return AddCardRequest.validate(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            printErrorMsg(e.getMessage());
            return readWantMoreCard(playerName);
        }
    }

    public void printErrorMsg(final String message) {
        System.out.println(message);
    }
}
