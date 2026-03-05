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
    @DisplayName("플레이어의 수는 8명을 넘을 수 없다.")
    void player_limit() {
        command("가,나,다,라,마,바,사,아,차");
        assertThrows(IllegalArgumentException.class, () -> {
            InputView.askPlayerNames();
        });
    }

    @Test
    @DisplayName("입력은 y와 n만 허용한다.")
    void yes_or_no(){
        command("dongkey");
        String player = "JeongKong";
        assertThrows(IllegalArgumentException.class, () -> {
            InputView.askContinue(player);
        });
    }
}