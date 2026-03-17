package view;

import static constant.GameRule.NO_ANSWER;
import static constant.GameRule.YES_ANSWER;

import domain.participant.Name;
import java.util.Scanner;
import message.ErrorMessage;

public class InputView {

    private static final String LINE_SEPARATOR = "\n";
    private static final String ENTER_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_BETTING_MONEY = "%s의 배팅 금액은?";
    private static final String ASK_HIT_OR_NOT = "%s는 한장의 카드를 더 받겠습니까?(예: %s, 아니오: %s)";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String askPlayerNames() {
        System.out.println(ENTER_PLAYER_NAME);
        return scanner.nextLine();
    }

    public String askPlayerBet(Name name) {
        System.out.printf(LINE_SEPARATOR + ASK_BETTING_MONEY + LINE_SEPARATOR, name.name());
        return scanner.nextLine();
    }

    public String askPlayerHit(Name name) {
        System.out.printf(ASK_HIT_OR_NOT + LINE_SEPARATOR, name.name(), YES_ANSWER, NO_ANSWER);
        String input = scanner.nextLine();
        if (!YES_ANSWER.contains(input) && !NO_ANSWER.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_HIT_INPUT.getMessage());
        }
        return input;
    }
}
