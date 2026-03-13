package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {

    private void command(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    @DisplayName("입력은 y와 n만 허용한다.")
    void askContinue_InputOtherThanYAndN_ThrowsException() {
        command("dongkey");
        String player = "JeongKong";
        assertThrows(IllegalArgumentException.class, () -> {
            InputView.askContinue(player);
        });
    }

    @Test
    @DisplayName("입력은 정수만 허용한다.")
    void askBettingAmount_ReturnInteger() {
        command("1000");
        String player = "JeongKong";
        Integer result = InputView.askBettingAmount(player);
        assertEquals(1000, result);
    }
}

