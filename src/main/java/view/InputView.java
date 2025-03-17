package view;

import domain.gamer.Gamer;
import domain.gamer.Nickname;
import java.util.Scanner;

public final class InputView {

    private InputView() {
    }

    public static String readPlayerName() {
        print("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readValidInput();
    }

    public static String readBetAmount(final Nickname nickname) {
        print(String.format("%s의 배팅 금액은?", nickname.getDisplayName()));
        return readValidInput();
    }

    public static boolean readQuestOneMoreCard(final Gamer gamer) {
        print(String.format("%s는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", gamer.getDisplayName()));
        final String input = readValidInput();

        return input.equals("y");
    }

    private static String readValidInput() {
        final Scanner scanner = new Scanner(System.in);
        final String input = scanner.nextLine();

        if (input.isBlank()) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
        return input.strip();
    }

    private static void print(final String message) {
        System.out.println(message);
    }
}
