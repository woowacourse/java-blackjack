package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    public InputView() {
    }

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public String inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return input();
    }

    public String inputBetMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");
        return input();
    }

    public String inputAdditionalCard(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return input();
    }

    private String input() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
