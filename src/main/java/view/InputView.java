package view;

import java.util.List;
import java.util.Scanner;
import util.Parser;

public class InputView {
    public static final String DELIMITER_COMMA = ",";

    public List<String> insertUsernames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리, 한글 영문 공백 쉼표만 입력 가능)");
        String rawNames = readLine();
        InputValidator.validateInputFormat(rawNames);
        List<String> splittedNames = Parser.splitByDelimiter(rawNames, DELIMITER_COMMA);
        InputValidator.validateDuplicate(splittedNames);
        return splittedNames;
    }

    public Response getUserResponse(String username) {
        System.out.printf("\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", username);
        return Response.findAnswer(readLine());
    }

    private String readLine() {
        return new Scanner(System.in).nextLine();
    }
}
