package view;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

    private void command(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    @DisplayName("입력은 y와 n만 허용한다.")
    void yes_or_no() {
        command("dongkey");
        String player = "JeongKong";
        assertThrows(IllegalArgumentException.class, () -> {
            InputView.askContinue(player);
        });
    }
}
