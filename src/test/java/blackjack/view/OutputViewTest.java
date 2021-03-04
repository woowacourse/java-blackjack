package blackjack.view;

import blackjack.domain.user.Player;
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