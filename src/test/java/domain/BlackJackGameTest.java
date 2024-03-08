package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class BlackJackGameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<String> names = List.of("위브", "산초");
        PlayerNames playerNames = new PlayerNames(names);
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);

        assertThatCode(() -> new BlackJackGame(playerNames, dealer))
                .doesNotThrowAnyException();
    }
}
