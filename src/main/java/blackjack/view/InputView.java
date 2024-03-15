package blackjack.view;

import blackjack.domain.betting.Money;
import blackjack.domain.player.Name;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static blackjack.view.PlayerChoice.HIT;
import static blackjack.view.PlayerChoice.STAND;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public List<Name> askPlayerNames() {
        printAskingPlayerNamesInputMessage();
        String rawInput = scanner.nextLine();
        return Arrays.stream(rawInput.split(",", -1))
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public boolean askForMoreCard(Name name) {
        printAskingForAnotherCardMessage(name);
        String rawInput = scanner.nextLine();
        return PlayerChoice.isDrawable(rawInput);
    }

    private void printAskingPlayerNamesInputMessage() {
        String askingMessage = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
        System.out.println(askingMessage);
    }

    private void printAskingForAnotherCardMessage(Name name) {
        String askingMessage = String.format("%s는 한장의 카드를 더 받겠습니까?", name.value());
        String exampleMessage = String.format("(예는 %s, 아니오는 %s)", HIT.getMessage(), STAND.getMessage());
        System.out.println(String.join("", LINE_SEPARATOR, askingMessage, exampleMessage));
    }

    public Money askBettingMoney(Name name) {
        printAskingBettingMoneyMessage(name);
        try {
            return new Money(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("돈의 액수는 숫자여야 합니다.");
        }
    }

    private void printAskingBettingMoneyMessage(Name name) {
        String askingMessage = String.format("%s의 베팅 금액은?", name.value());
        System.out.println(String.join("", LINE_SEPARATOR, askingMessage));
    }
}
