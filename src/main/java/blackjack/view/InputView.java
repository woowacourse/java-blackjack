package blackjack.view;

import blackjack.domain.gambler.Name;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SPLIT_DELIMITER = ",";
    private static final String OPTION_YES = "y";
    private static final String OPTION_NO = "n";
    private static final String INVALID_HIT_RESPONSE = "플레이어의 Hit 응답이 올바르지 못합니다.";
    private static final String INVALID_AMOUNT = "올바르지 않은 금액입니다.";

    public List<Name> inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String answer = scanner.nextLine();
        String[] parsedName = answer.split(SPLIT_DELIMITER);
        List<Name> names = Arrays.stream(parsedName)
                .map(Name::new)
                .toList();
        return names;
    }

    public boolean inputPlayerHit(final Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)\n", name, OPTION_YES, OPTION_NO);
        String answer = scanner.nextLine();
        if (answer.equals(OPTION_YES)) {
            return true;
        }
        if (answer.equals(OPTION_NO)) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_HIT_RESPONSE);
    }

    public int inputBettingAmount(final Name name) {
        System.out.printf("%s의 배팅 금액은?\n", name);
        try {
            return scanner.nextInt();
        } catch (final InputMismatchException e) {
            throw new IllegalArgumentException(INVALID_AMOUNT);
        } finally {
            scanner.nextLine();
        }
    }
}
