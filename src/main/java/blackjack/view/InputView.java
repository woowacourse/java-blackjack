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
        printPlayerNamesInputMessage();
        String rawInput = scanner.nextLine();
        return Arrays.stream(rawInput.split(",", -1))
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public boolean askForMoreCard(final Name name) {
        printAskingForAnotherCardMessage(name);
        final String rawInput = scanner.nextLine();
        return PlayerChoice.isDrawable(rawInput);
    }

    private void printPlayerNamesInputMessage() {
        final String askingMessage = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
        System.out.println(askingMessage);
    }

    private void printAskingForAnotherCardMessage(final Name name) {
        final String askingMessage = String.join("", name.getValue(), "는 한장의 카드를 더 받겠습니까?");
        final String exampleMessage = String.join("(예는 " + HIT.getMessage() + ", 아니오는 " + STAND.getMessage() + ")");
        System.out.println(String.join("", LINE_SEPARATOR, askingMessage, exampleMessage));
    }

    public Money askBettingMoney(final Name name) {
        printAskingBettingMoneyMessage(name);
        try {
            return new Money(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("돈의 액수는 숫자여야 합니다.");
        }
    }

    private void printAskingBettingMoneyMessage(final Name name) {
        final String askingMessage = name.getValue() + "의 베팅 금액은?";
        System.out.println(String.join("", LINE_SEPARATOR, askingMessage));
    }
}
