package blackjack.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String PLAYER_NAME_INPUT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_SEPARATOR = ",";
    private static final String IOEXCEPTION_ERROR = "입력 과정 도중 에러가 발생했습니다.";

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final InputView instance = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public List<String> readPlayersName() {
        System.out.println(PLAYER_NAME_INPUT);
        try {
            final List<String> names = Arrays.stream(bufferedReader.readLine().split(NAME_SEPARATOR))
                    .map(String::trim)
                    .toList();
            return names;
        } catch (IOException exception) {
            throw new IllegalArgumentException(IOEXCEPTION_ERROR);
        }
    }

}
