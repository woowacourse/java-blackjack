package blackjack.view;

import blackjack.domain.Money;
import blackjack.domain.Name;
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

    public List<Name> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String inputNames = readLine();
        List<String> seperatedNames = Arrays.asList(inputNames.split(","));
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
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public Map<Name, Money> readPlayersBetMoney(List<Name> playerNames) {
        Map<Name, Money> playersBetMoney = new LinkedHashMap<>();
        for (Name playerName : playerNames) {
            System.out.println(String.format("%s의 배팅 금액은?", playerName));
            String inputBetMoney = readLine();
            Integer parsedBetMoney = parseToInt(inputBetMoney);
            playersBetMoney.put(playerName, new Money(parsedBetMoney));
        }
        return playersBetMoney;
    }

    private Integer parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("배팅 금액은 숫자만 가능합니다. 입력값: %s", input));
        }
    }
}
