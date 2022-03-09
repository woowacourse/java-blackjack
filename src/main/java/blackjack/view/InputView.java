package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String MESSAGE_ASK_PARTICIPANTS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String NAME_DELIMITER = ",";
	private static final String MESSAGE_ASK_ADDITIONAL_CARD_CHOICE = "%s, 한장의 카드를 더 받겠습니까?(예는 Y/y, 아니오는 N/n)";
	private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void terminate() {
        scanner.close();
    }

    public List<String> askPlayerNameInput() {
		System.out.println(MESSAGE_ASK_PARTICIPANTS);
        String input = scanner.nextLine();
        return Arrays.asList(input.split(NAME_DELIMITER));
    }

	public String askAdditionalCardInput(String name) {
		System.out.printf(MESSAGE_ASK_ADDITIONAL_CARD_CHOICE, name);
		return scanner.nextLine();
	}
}
