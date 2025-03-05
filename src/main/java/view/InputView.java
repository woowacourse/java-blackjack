package view;

import domain.Card;
import domain.Game;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }

    public String readHitOrStay(Game game) {
        Map<String, List<Card>> playerNameAndCards = game.getPlayerNameAndCards();
        for (Entry<String, List<Card>> nameAndCardsEntry : playerNameAndCards.entrySet()) {
            String name = nameAndCardsEntry.getKey();
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
            String input = scanner.nextLine();
            validateYesOrNo(input);
        }
        return null;
    }

    private void validateYesOrNo(String input) {
        if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            throw new IllegalArgumentException("[ERROR] y 또는 n 으로 입력해주세요.");
        }
    }
}
