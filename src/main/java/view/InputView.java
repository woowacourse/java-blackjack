package view;

import static view.InputValidator.validateDuplicate;
import static view.InputValidator.validateInputFormat;
import static view.InputValidator.validateInputMoney;
import static view.InputValidator.validateUserResponse;

import domain.BettingMoney;
import domain.PlayerName;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import util.Parser;

public class InputView {
    private static final String DELIMITER_COMMA = ",";
    protected static final Map<String, Boolean> UserResponses = Map.of(
            "y", true,
            "n", false
    );

    public List<PlayerName> insertUsernames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리, 한글 영문 공백 쉼표만 입력 가능)");
        String rawNames = readLine();
        validateInputFormat(rawNames);
        List<String> splittedNames = Parser.splitByDelimiter(rawNames, DELIMITER_COMMA);
        validateDuplicate(splittedNames);
        return splittedNames.stream()
                .map(PlayerName::new)
                .toList();
    }

    private String readLine() {
        return new Scanner(System.in).nextLine();
    }

    public boolean isYes(String username) {
        System.out.printf("\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", username);
        String rawUserResponse = readLine();
        validateUserResponse(rawUserResponse);
        return UserResponses.get(rawUserResponse);
    }

    public BettingMoney insertBettingMoney(PlayerName playerName) {
        System.out.printf("%s의 배팅 금액은?\n", playerName.username());
        String rawBettingMoney = readLine();
        validateInputMoney(rawBettingMoney);

        return new BettingMoney(Integer.parseInt(rawBettingMoney));
    }
}
