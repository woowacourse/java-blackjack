package view;

import domain.player.PlayerName;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InputView {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public List<String> readPlayerNames() throws IOException {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = bufferedReader.readLine();
        validateNotBlank(input);
        input = removeBlank(input);

        return Arrays.stream(input.split(",")).toList();
    }

    public Map<PlayerName, Integer> readBettingMoney(final List<PlayerName> playerNames) throws IOException {
        Map<PlayerName, Integer> inputs = new LinkedHashMap<>();

        for (PlayerName playerName : playerNames) {
            System.out.println(playerName.name() + "의 배팅 금액은?");
            String input = bufferedReader.readLine();
            validateNotBlank(input);
            inputs.put(playerName, parseInt(input));
        }
        return inputs;
    }

    public String readCommand(final String playerName) throws IOException {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        String command = bufferedReader.readLine();
        validateNotBlank(command);
        command = removeBlank(command);

        return command;
    }

    private void validateNotBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }

    private String removeBlank(final String input) {
        return input.replace(" ", "");
    }

    private int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }
    }
}
