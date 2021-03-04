package blackjack.view;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ASK_PLAYERS_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String DELIMITER = ",";
    private static final String DUPLICATE_NAME_ERROR_MSG = "중복된 이름은 존재할 수 없습니다.";
    private static final String EMPTY_NAME_ERROR_MSG = "빈 값은 입력될 수 없습니다.";
    // todo: 도메인에서 검증 여부
    private static final String DEALER_NAME = "딜러";
    public static final String MORE_DRAW_MSG_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    public static final String YES = "y";
    public static final String NO = "n";
    public static final String ANSWER_MUST_BE_YES_OR_NO_ERROR_MSG_FORMAT = "%s 또는 %s 로 입력해주세요.";

    public static List<String> askPlayersName() {
        System.out.println(ASK_PLAYERS_NAME_MSG);
        List<String> splitNames = trimNames(splitNames(SCANNER.nextLine()));
        validateNames(splitNames);
        System.out.println(splitNames);
        return splitNames;
    }

    private static List<String> splitNames(String names) {
        return Arrays.asList(names.split(DELIMITER));
    }

    private static List<String> trimNames(List<String> splitNames) {
        return splitNames.stream().map(String::trim).collect(Collectors.toList());
    }

    private static void validateNames(List<String> splitNames) {
        validateEmptyName(splitNames);
        validateNotDealerName(splitNames);
        validateDuplicate(splitNames);
    }

    private static void validateDuplicate(List<String> splitNames) {
        Set<String> duplicateChecker =  new HashSet<>(splitNames);
        if (splitNames.size() != duplicateChecker.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MSG);
        }
    }

    private static void validateNotDealerName(List<String> splitNames) {
        if (splitNames.stream().anyMatch(DEALER_NAME::equals)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateEmptyName(List<String> splitNames) {
        if (splitNames.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(EMPTY_NAME_ERROR_MSG);
        }
    }

    private static Boolean askMoreDraw(String userName){
        System.out.printf(MORE_DRAW_MSG_FORMAT, userName);
        String yesOrNo = SCANNER.nextLine();
        validateYesOrNo(yesOrNo);
        return yesOrNo.equalsIgnoreCase(YES);
    }

    private static void validateYesOrNo(String yesOrNo) {
        if (!yesOrNo.equalsIgnoreCase(YES) && !yesOrNo.equalsIgnoreCase(NO)) {
            throw new IllegalArgumentException(String.format(ANSWER_MUST_BE_YES_OR_NO_ERROR_MSG_FORMAT,YES, NO));
        }
    }

}