package blackjack.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import blackjack.domain.card.PlayStatus;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        final String text = scanner.nextLine();
        return Stream.of(text.split(",", -1))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public static PlayStatus requestHitOrStay(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        final String text = scanner.nextLine().trim();
        if (!text.matches("[YyNn]")) {
            throw new IllegalArgumentException("y, n 이외의 값이 입력되었습니다.");
        }

        if (text.equalsIgnoreCase("y")) {
            return PlayStatus.HIT;
        }

        return PlayStatus.STAY;
    }
}
