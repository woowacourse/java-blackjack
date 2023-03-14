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

    public int readBettingMoney(String name) {
        String money;
        do {
            System.out.println(System.lineSeparator() + name + "의 배팅 금액은?");
            money = scanner.nextLine();
        } while (isNotMoney(money));
        return Integer.parseInt(money);
    }

    private boolean isNotMoney(String money) {
        try {
            validateAmount(money);
        } catch (NumberFormatException e) {
            printErrorMsg("배팅금은 100 보다 큰 숫자로 입력해주세요");
            return true;
        }
        return false;
    }

    private void validateAmount(String money) {
        if (Integer.parseInt(money) < 100) {
            throw new NumberFormatException();
        }
    }

    public DrawRequest readWantMoreCard(final String playerName) {
        Optional<DrawRequest> addCardResponse;
        do {
            System.out.printf(DrawRequest.message(playerName));
            addCardResponse = DrawRequest.from(scanner.nextLine());
            if (addCardResponse.isEmpty()) {
                printErrorMsg(DrawRequest.getErrorPowerMsg());
            }
        } while (addCardResponse.isEmpty());
        return addCardResponse.get();
    }

    public void printErrorMsg(final String message) {
        System.out.println("[ERROR] " + message);
    }

}
