package blackjackgame.view;

import java.util.List;
import java.util.Scanner;

import static blackjackgame.view.AddCardRequest.NO;
import static blackjackgame.view.AddCardRequest.YES;

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
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("베팅 금액으로 양의 정수를 입력해주세요.");
            return readBettingMoney(guestName);
        }
    }

    public AddCardRequest readWantMoreCard(final String playerName) {
        System.out.println();
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)"
                ,playerName, YES.value(), NO.value()));
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
