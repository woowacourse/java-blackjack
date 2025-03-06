package view;

import java.util.List;
import java.util.Scanner;
import util.Parser;

public class InputView {
    public List<String> insertUsernames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = readLine();
        return Parser.splitByDelimiter(rawNames, ",");
    }

    public Response getUserResponse(String username) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", username);
        return Response.findAnswer(readLine());
    }

    private String readLine() {
        return new Scanner(System.in).nextLine();
    }
}
