package blackjack.view;

import static blackjack.view.ErrorMessage.INVALID_HIT_RESPONSE;

import blackjack.domain.gambler.Name;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SPLIT_DELIMITER = ",";
    private static final String OPTION_YES = "y";
    private static final String OPTION_NO = "n";

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
        throw new IllegalArgumentException(INVALID_HIT_RESPONSE.getMessage());
    }
}
