package blackjack.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public List<String> scanPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final List<String> playerNames = getPlayerNames();
        validateDuplicateNames(playerNames);
        return playerNames;
    }

    private List<String> getPlayerNames() {
        return Arrays.stream(SCANNER.nextLine()
                .split(",", -1))
            .map(String::trim)
            .collect(toList());
    }

    public void validateDuplicateNames(List<String> names) {
        if (isDuplicate(names)) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    private boolean isDuplicate(final List<String> names) {
        return names.size() != names.stream()
            .distinct()
            .count();
    }

    public boolean scanHitOrStay(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        final String hitOrStay = SCANNER.nextLine();
        return isHit(hitOrStay);
    }

    private boolean isHit(final String hitOrStay) {
        return "y".equalsIgnoreCase(hitOrStay);
    }
}
