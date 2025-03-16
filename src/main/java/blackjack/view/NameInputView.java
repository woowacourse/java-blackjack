package blackjack.view;

import java.util.Scanner;

public class NameInputView implements InputView {

    private static final String INPUT_PLAYERS_NICKNAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private final Scanner scanner;

    public NameInputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String read() {
        System.out.println(INPUT_PLAYERS_NICKNAMES);
        return scanner.nextLine();
    }
}
