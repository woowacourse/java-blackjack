package view;

import java.util.List;
import java.util.Scanner;
import model.BettingMoney;
import model.player.Name;

public class InputView {
    private static final String ASK_PARTICIPANT_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_ONE_MORE_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String ASK_BETTING_AMOUNT = "%s의 배팅 금액은?";
    private static final Scanner in = new Scanner(System.in);

    public List<String> askParticipantNames() {
        System.out.println(ASK_PARTICIPANT_NAMES_MESSAGE);

        String input = in.nextLine();
        return List.of(input.split(",", -1));
    }

    public boolean isOneMoreCard(Name name) {
        System.out.println(System.lineSeparator() + ASK_ONE_MORE_CARD_MESSAGE.formatted(name));

        String input = in.nextLine();
        if (Command.YES.compareTo(input)) {
            return true;
        }
        if (Command.NO.compareTo(input)) {
            return false;
        }
        throw new IllegalArgumentException("y/n 만 입력할 수 있습니다.");
    }

    public BettingMoney askBettingAmount(String name) {
        System.out.println(System.lineSeparator() + ASK_BETTING_AMOUNT.formatted(name));
        String bettingAmount = in.nextLine();
        validateIsNumber(bettingAmount);
        return new BettingMoney(Integer.parseInt(bettingAmount));
    }

    private void validateIsNumber(String bettingAmount) {
        try {
            Integer.parseInt(bettingAmount);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("배팅금액은 숫자로만 입력할 수 있습니다.");
        }
    }
}
