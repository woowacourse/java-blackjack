package blackjack.view;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ASK_PLAYERS_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String DELIMITER = ",";
    private static final String MORE_DRAW_MSG_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String ANSWER_MUST_BE_YES_OR_NO_ERROR_MSG_FORMAT = "%s 또는 %s 로 입력해주세요.";

    public static List<String> askPlayersName() {
        System.out.println(ASK_PLAYERS_NAME_MSG);
        return trimNames(splitNames(SCANNER.nextLine()));
    }

    private static List<String> splitNames(String names) {
        return Arrays.asList(names.split(DELIMITER));
    }

    private static List<String> trimNames(List<String> splitNames) {
        return splitNames.stream().map(String::trim).collect(Collectors.toList());
    }

    public static Boolean askMoreDraw(Name userName) {
        System.out.printf(MORE_DRAW_MSG_FORMAT, userName);
        String yesOrNo = SCANNER.nextLine();
        validateYesOrNo(yesOrNo);
        return yesOrNo.equalsIgnoreCase(YES);
    }

    private static void validateYesOrNo(String yesOrNo) {
        if (!yesOrNo.equalsIgnoreCase(YES) && !yesOrNo.equalsIgnoreCase(NO)) {
            throw new IllegalArgumentException(String.format(ANSWER_MUST_BE_YES_OR_NO_ERROR_MSG_FORMAT, YES, NO));
        }
    }
}