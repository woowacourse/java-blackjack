package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.state.HitStand;
import dto.domain.PlayerNameAndBettingDto;
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
    private static final String INVALID_HIT_OR_STAND = "y 또는 n만 입력해주세요.";
    private static final String INVALID_BETTING_FORMAT = "숫자만 입력해주세요.";
    private static final String INVALID_BETTING_AMOUNT = "배팅 금액은 10 이상이며 10원 단위여야 합니다.";
    private static final String DUPLICATED_PLAYER_NAME = "중복된 플레이어 이름은 허용되지 않습니다.";

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
        assertTrue(readOutput(out).contains(DUPLICATED_PLAYER_NAME));
    }

    @Test
    @DisplayName("배팅 금액이 숫자가 아니거나 10원 단위가 아니면 재입력 받는다")
    void retryWhenInvalidBettingInput() {
        final String input = "abc\n0\n15\n1000\n";
        final ByteArrayOutputStream out = setInputOutput(input);
        final InputView inputView = new InputView();

        final List<PlayerNameAndBettingDto> bettingDtos = inputView.getPlayerBetting(List.of("pobi"));

        assertEquals(1, bettingDtos.size());
        assertEquals("pobi", bettingDtos.get(0).name());
        assertEquals(1000, bettingDtos.get(0).betting());
        final String output = readOutput(out);
        assertTrue(output.contains(INVALID_BETTING_FORMAT));
        assertTrue(output.contains(INVALID_BETTING_AMOUNT));
    }

    @Test
    @DisplayName("Hit or Stand 입력이 잘못되면 에러 메시지를 출력하고 재입력 받는다")
    void retryWhenInvalidHitOrStandInput() {
        final String input = "x\ny\n";
        final ByteArrayOutputStream out = setInputOutput(input);
        final InputView inputView = new InputView();

        final HitStand result = inputView.askHitOrStand("pobi");

        assertEquals(HitStand.HIT, result);
        assertTrue(readOutput(out).contains(INVALID_HIT_OR_STAND));
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
