package blackjack.view;

import static org.junit.jupiter.api.Assertions.*;

import blackjack.Player;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
    @DisplayName("출력 테스트")
    @Test
    void checkPrint() {
        Player player = new Player("vkvl");
        OutputView.printShowUsersCardMessage(Collections.singletonList(player));
    }

}