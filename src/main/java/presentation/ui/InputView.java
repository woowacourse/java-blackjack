package presentation.ui;

import static presentation.ui.ViewMessage.CARD_MORD_MESSAGE;
import static presentation.ui.ViewMessage.PLAYER_NAME_MESSAGE;

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

    public List<String> readPlayerNames() {
        try {
            System.out.println(PLAYER_NAME_MESSAGE.format());
            return Stream.of(bufferedReader.readLine().split(","))
                    .map(String::trim)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean playContinue(String playerName) {
        System.out.println(CARD_MORD_MESSAGE.format(playerName));
        try {
            String answer = bufferedReader.readLine();
            return answer.equals("y") || answer.equals("Y");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
