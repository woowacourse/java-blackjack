package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String PLAYER_NAMES_INPUT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_WANT_MORE_CARD_FORMAT = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오 n)";
    private static final String PLAYER_BET_AMOUNT_FORMAT = "%s의 배팅 금액은?";
    private static final String DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    public List<String> requestPlayerNames() {
        System.out.println(PLAYER_NAMES_INPUT);
        String playerNames = SCANNER.nextLine();

        return Arrays.stream(playerNames.split(DELIMITER))
                .collect(Collectors.toList());
    }

    public String requestMoreCard(String name) {
        String moreCardMessageFormat = String.format(PLAYER_WANT_MORE_CARD_FORMAT, name);
        System.out.println(moreCardMessageFormat);

        return SCANNER.nextLine();
    }


    public String requestBetAmount(String playerName) {
        System.out.println();
        String betAmountMessageFormat = String.format(PLAYER_BET_AMOUNT_FORMAT, playerName);
        System.out.println(betAmountMessageFormat);

        return SCANNER.nextLine();
    }
}
