package blackjack.view;

import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerBetAmounts;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private static final String NAME_DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public List<Name> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String inputNames = readLine();
        List<String> seperatedNames = Arrays.asList(inputNames.split(NAME_DELIMITER));
        return convertToName(seperatedNames);
    }

    private String readLine() {
        try {
            return BUFFERED_READER.readLine();
        } catch (IOException e) {
            throw new IllegalStateException("입력 처리중 에러가 발생했습니다.");
        }
    }

    private List<Name> convertToName(List<String> seperatedNames) {
        return seperatedNames.stream()
                .map(String::trim)
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public PlayerBetAmounts readBetAmount(List<Name> playerNames) {
        Map<Name, BetAmount> playersBetAmounts = new LinkedHashMap<>();
        for (Name playerName : playerNames) {
            System.out.println(String.format(LINE_SEPARATOR + "%s의 베팅 금액은?", playerName.getName()));
            String inputBetAmount = readLine();
            Integer parseBetAmount = parseToInt(inputBetAmount);
            playersBetAmounts.put(playerName, new BetAmount(parseBetAmount));
        }
        return new PlayerBetAmounts(playersBetAmounts);
    }

    private Integer parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("베팅 금액은 숫자만 가능합니다. 입력값: %s", input));
        }
    }

    public boolean readHitOrStand(Player player) {
        System.out.println(
                String.format("%s는 한장의 카드를 더 받겠습니까?(예는 " + YES + ", 아니오는 " + NO + ")", player.getName().getName()));
        String input = readLine();
        if (YES.equals(input)) {
            return true;
        }
        if (NO.equals(input)) {
            return false;
        }
        throw new IllegalArgumentException("베팅은 " + YES + " 또는 " + NO + "만 입력 가능합니다.");
    }
}
