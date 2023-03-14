package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String CARD_INPUT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String BETTING_INPUT_MESSAGE = "의 배팅 금액은?";
    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        printMessage(NAME_INPUT_MESSAGE);
        String names = input();
        return Arrays.stream(names.split(DELIMITER))
                .collect(Collectors.toList());
    }

    public boolean readIntention(String name) {
        printMessage(name + CARD_INPUT_MESSAGE);
        try {
            String intention = input().toLowerCase();
            return Intention.getIntention(intention);
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            return readIntention(name);
        }
    }

    public int readBetting(String name) {
        printMessage(name + BETTING_INPUT_MESSAGE);
        try {
            String inputBetting = input();
            return Integer.parseInt(inputBetting);
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            return readBetting(name);
        }
    }

    private String input() {
        String inputValue = scanner.nextLine();
        if (inputValue.isBlank()) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
        return inputValue;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
