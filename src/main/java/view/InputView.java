package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public List<String> readPlayerNames() throws IOException {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = bufferedReader.readLine();
        validateNotBlank(input);
        input = removeBlank(input);
        return Arrays.stream(input.split(",")).toList();
    }

    public String readCommand(String playerName) throws IOException {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerName));
        String command = bufferedReader.readLine();
        validateNotBlank(command);
        command = removeBlank(command);
        return command;
    }

    private void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }

    private String removeBlank(String input) {
        input = input.replace(" ", "");
        return input;
    }
}
