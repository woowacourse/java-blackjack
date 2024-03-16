package blackjack.view;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.user.PlayerNames;
import blackjack.domain.user.UserName;
import blackjack.view.format.CardRequestFormat;
import java.util.Arrays;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static PlayerNames readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();

        validateDelimiter(input);

        return new PlayerNames(Arrays.stream(input.split(DELIMITER))
                .map(UserName::new)
                .toList());
    }

    private static void validateDelimiter(String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(DELIMITER + " 로 끝날 수 없습니다.");
        }
    }

    public static BetAmount readBetAmount(UserName name) {
        System.out.println(name.getName() + "의 배팅 금액은?");
        return new BetAmount(readInt());
    }

    private static int readInt() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    public static boolean readHitDesire(UserName name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", name.getName(),
                CardRequestFormat.YES.getFormat(),
                CardRequestFormat.NO.getFormat());

        String input = SCANNER.nextLine();

        return CardRequestFormat.from(input);
    }
}
