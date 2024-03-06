package blackjack.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String IO_ERROR = "입력이 잘못되었습니다.";

    public static List<String> readPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return parseNames(bufferedReader.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException(IO_ERROR);
        }
    }

    private static List<String> parseNames(String names) {
        return Arrays.stream(names.split(",")).toList();
    }

    public static String readMoreCard(String name) throws IOException {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
