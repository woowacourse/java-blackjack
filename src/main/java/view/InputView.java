package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private final BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(System.in));

    public String inputPlayers() throws IOException {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return bufferedReader.readLine();
    }

    public String inputHitOrStand(String name) throws IOException {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
        return bufferedReader.readLine();
    }
}