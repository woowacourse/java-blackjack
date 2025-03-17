package view;

import java.util.List;
import java.util.Scanner;
import util.Parser;

public class InputView {
    public static final String DELIMITER_COMMA = ",";

    public List<String> insertUsernames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = readLine();
        InputValidator.validateUsernames(rawNames);
        return Parser.splitByDelimiter(rawNames, DELIMITER_COMMA);
    }

    public int insertBettingAmount(String username) {
        System.out.printf("%s의 배팅 금액은?\n", username);
        String rawMoney = readLine();
        InputValidator.validateNumberType(rawMoney);
        return Integer.parseInt(rawMoney);
    }

    public Response getUserResponse(String username) {
        System.out.printf("\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", username);
        return Response.findAnswer(readLine());
    }

    private String readLine() {
        return new Scanner(System.in).nextLine();
    }

    private static class InputValidator {
        private static final String PATTERN = "^[가-힣a-zA-Z, ]+$";
        private static final String NUMBER_PATTERN = "\\d+";

        public static void validateUsernames(String names) {
            if (!names.matches(PATTERN)) {
                throw new IllegalArgumentException("한글, 숫자, 공백, 쉼표만 입력가능합니다.");
            }
        }

        public static void validateNumberType(String money) {
            if (!money.matches(NUMBER_PATTERN)) {
                throw new IllegalArgumentException("숫자를 입력해주세요");
            }
        }
    }
}
