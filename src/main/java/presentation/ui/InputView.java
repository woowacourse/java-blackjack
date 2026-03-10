package presentation.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

public class InputView {

    private final BufferedReader bufferedReader;

    public InputView() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int readBettingAmount(String playerName) {
        try {
            System.out.printf("%s의 배팅 금액은?\n", playerName);
            return Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readPlayerNames() {
        try {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            return Stream.of(bufferedReader.readLine().split(","))
                    .map(String::trim)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean playContinue(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
        try {
            String answer = bufferedReader.readLine();
            if (answer.equals("y") || answer.equals("Y")) {
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
