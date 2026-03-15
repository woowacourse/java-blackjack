package presentation.ui;

import domain.member.BettingAmount;
import domain.member.Name;
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

    public BettingAmount readBettingAmount(String playerName) {
        try {
            System.out.printf("%s의 배팅 금액은?\n", playerName);
            return new BettingAmount(Integer.parseInt(bufferedReader.readLine()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Name> readPlayerNames() {
        return parsePlayerNames().stream()
                .map(Name::new)
                .toList();
    }

    public List<String> parsePlayerNames() {
        try {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            return Stream.of(bufferedReader.readLine().split(","))
                    .map(String::trim)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean playContinue(String playerName) {
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
