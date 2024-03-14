package blackjack.view;

import blackjack.domain.player.Name;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static blackjack.view.PlayerChoice.HIT;
import static blackjack.view.PlayerChoice.STAND;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public List<Name> askPlayerNames() {
        printPlayerNamesInputMessage();
        String rawInput = scanner.nextLine();
        return Arrays.stream(rawInput.split(",", -1))
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public boolean askForMoreCard(final String name) {
        printAskingForAnotherCardMessage(name);
        final String rawInput = scanner.nextLine();
        return PlayerChoice.isDrawable(rawInput);
    }

    private void printPlayerNamesInputMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    private void printAskingForAnotherCardMessage(final String name) {
        printLineSeparator();
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 " + HIT.getMessage() +
                ", 아니오는 " + STAND.getMessage() + ")");
    }

    private void printLineSeparator() {
        System.out.println();
    }
}
