package view;

import model.player.Name;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String REQUEST_PARTICIPANT_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String REQUEST_PARTICIPANT_MONEY_MESSAGE = "%s의 배팅 금액은?";
    private static final String REQUEST_ONE_MORE_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final Scanner in = new Scanner(System.in);

    public List<String> requestParticipantNames() {
        System.out.println(REQUEST_PARTICIPANT_NAMES_MESSAGE);

        String input = in.nextLine();
        return List.of(input.split(",", -1));
    }

    public Integer requestParticipantMoney(Name name) {
        System.out.println(REQUEST_PARTICIPANT_MONEY_MESSAGE.formatted(name.getName()));

        String input = in.nextLine();

        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("참가자의 배팅 금액은 숫자로만 이뤄져야합니다.");
        }
        return Integer.parseInt(input);
    }

    public boolean isOneMoreCard(Name name) {
        System.out.println(REQUEST_ONE_MORE_CARD_MESSAGE.formatted(name.getName()));

        String input = in.nextLine();
        if (Command.YES.compareTo(input)) {
            return true;
        }
        if (Command.NO.compareTo(input)) {
            return false;
        }
        throw new IllegalArgumentException("y/n 만 입력할 수 있습니다.");
    }
}
