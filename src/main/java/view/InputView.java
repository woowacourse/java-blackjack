package view;

import dto.RequestPlayerNameDTO;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static RequestPlayerNameDTO inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            String playerName = SCANNER.nextLine();
            validatePlayerName(playerName);
            return new RequestPlayerNameDTO(playerName);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerName();
        }
    }

    private static void validatePlayerName(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}

