package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.participant.Player;
import dto.domain.PlayerNameAndBettingDto;
import error.ErrorMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputViewTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("중복 이름 입력 시 에러 메시지를 출력하고 재입력 받는다")
    void retryWhenDuplicatedNames() {
        final String input = "pobi,pobi\npobi,jason\n";
        final ByteArrayOutputStream out = setInputOutput(input);
        final InputView inputView = new InputView();

        final List<String> playerNames = inputView.getPlayerNames();

        assertEquals(List.of("pobi", "jason"), playerNames);
        assertTrue(readOutput(out).contains(ErrorMessage.DUPLICATED_PLAYER_NAME.message()));
    }

    @Test
    @DisplayName("배팅 금액이 숫자가 아니거나 1 미만이면 재입력 받는다")
    void retryWhenInvalidBettingInput() {
        final String input = "abc\n0\n1000\n";
        final ByteArrayOutputStream out = setInputOutput(input);
        final InputView inputView = new InputView();

        final List<PlayerNameAndBettingDto> bettingDtos = inputView.getPlayerBetting(List.of("pobi"));

        assertEquals(1, bettingDtos.size());
        assertEquals("pobi", bettingDtos.get(0).name());
        assertEquals(1000, bettingDtos.get(0).betting());
        final String output = readOutput(out);
        assertTrue(output.contains(ErrorMessage.INVALID_BETTING_FORMAT.message()));
        assertTrue(output.contains(ErrorMessage.INVALID_BETTING_AMOUNT.message()));
    }

    @Test
    @DisplayName("Hit or Stand 입력이 잘못되면 에러 메시지를 출력하고 재입력 받는다")
    void retryWhenInvalidHitOrStandInput() {
        final String input = "x\ny\n";
        final ByteArrayOutputStream out = setInputOutput(input);
        final InputView inputView = new InputView();
        final Player player = new Player("pobi", 1000);

        final boolean result = inputView.askHitOrStand(player);

        assertTrue(result);
        assertTrue(readOutput(out).contains(ErrorMessage.INVALID_HIT_OR_STAND.message()));
    }

    private ByteArrayOutputStream setInputOutput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        return out;
    }

    private String readOutput(ByteArrayOutputStream out) {
        return out.toString(StandardCharsets.UTF_8);
    }
}
