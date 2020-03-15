package domain;

import domain.card.Cards;
import domain.player.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningResultTest {

    @DisplayName("객체 생성 테스트")
    @Test
    void getterTest() {
        List<String> playerName = new ArrayList<>(Arrays.asList("pobi", "subway"));
        Cards cards = new Cards();
        Users users = new Users(cards,playerName);

        WinningResult winningResult = new WinningResult(users);

        Assertions.assertThat(winningResult.generateWinningUserResult()).size().isEqualTo(3);
    }
}
