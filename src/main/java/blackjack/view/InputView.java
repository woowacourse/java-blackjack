package blackjack.view;

import blackjack.domain.player.Challenger;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static blackjack.view.OutputView.printNewLine;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(scanner.nextLine().split(","));
    }

    public static boolean wantMoreCard(final Challenger challenger) {
        try {
            printNewLine();
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", challenger.getName());
            String yesOrNo = scanner.nextLine();
            validateYesOrNo(yesOrNo);
            return "y".equals(yesOrNo);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return wantMoreCard(challenger);
        }
    }

    private static void validateYesOrNo(String yesOrNo) {
        yesOrNo = yesOrNo.trim().toLowerCase();
        if (!("y".equals(yesOrNo) || "n".equals(yesOrNo))) {
            throw new IllegalArgumentException("y 혹은 n 으로만 입력해주세요.");
        }
    }

    public static int getBetMoney(final Challenger challenger) {
        System.out.printf("%s의 배팅 금액은?%n", challenger.getName());
        return scanner.nextInt();
    }
}
