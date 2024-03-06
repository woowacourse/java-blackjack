package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackJackGameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<String> names = List.of("위브", "산초");
        PlayerNames playerNames = new PlayerNames(names);

        Dealer dealer = new Dealer(new CardDeck());

        Assertions.assertThatCode(() -> new BlackJackGame(playerNames, dealer))
                .doesNotThrowAnyException();
    }
}
