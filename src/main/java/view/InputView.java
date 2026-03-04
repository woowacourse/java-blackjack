package view;

import static view.reader.Console.readLine;

import java.util.Arrays;
import java.util.List;

public class InputView {

    private InputView() {
    }

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(readLine().split(","))
                .toList();
    }
}
