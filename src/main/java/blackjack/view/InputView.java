package blackjack.view;

import blackjack.domain.participant.Player;

import java.util.*;

import static blackjack.view.OutputView.NEW_LINE;
import static blackjack.view.OutputView.printNewLine;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(scanner.nextLine().split(","));
    }

    public static boolean wantMoreCard(final Player player) {
        try {
            printNewLine();
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getNameAsString());
            String yesOrNo = scanner.nextLine();
            validateYesOrNo(yesOrNo);
            return "y".equals(yesOrNo);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return wantMoreCard(player);
        }
    }

    private static void validateYesOrNo(String yesOrNo) {
        yesOrNo = yesOrNo.trim().toLowerCase();
        if (!("y".equals(yesOrNo) || "n".equals(yesOrNo))) {
            throw new IllegalArgumentException("y 혹은 n 으로만 입력해주세요.");
        }
    }

    public static Map<String, Double> getBettings(List<String> playerNames) {
        Map<String, Double> bettings = new HashMap<>();
        for (String name : playerNames) {
            System.out.print(NEW_LINE);
            System.out.println(name + "의 베팅 금액은?");
            bettings.put(name, Double.parseDouble(scanner.nextLine()));
        }
        return bettings;
    }
}
