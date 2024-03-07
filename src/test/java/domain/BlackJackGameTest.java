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

        assertThatCode(() -> new BlackJackGame(playerNames, cardDeck))
                .doesNotThrowAnyException();
    }

    @DisplayName("게임에서 참가자에게 카드를 Hit한다.")
    @Test
    void hitPlayer() {
        List<String> names = List.of("산초", "위브");
        PlayerNames playerNames = new PlayerNames(names);
        CardDeck cardDeck = new CardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(playerNames, cardDeck);

        assertThatCode(() -> blackJackGame.hitPlayer("산초"))
                .doesNotThrowAnyException();
        /*TODO: 플레이어가 할당 받는 카드를 지정해준다. e.g. 10, K
         * 하나 더 받는 카드도 지정한다.
         * true, false 테스트 가능
         */
    }

    @DisplayName("게임에서 딜러에게 카드를 hit한다.")
    @Test
    void hitDealer() {
        List<String> names = List.of("산초", "위브");
        PlayerNames playerNames = new PlayerNames(names);
        CardDeck cardDeck = new CardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(playerNames, cardDeck);

        assertThatCode(() -> blackJackGame.hitDealer())
                .doesNotThrowAnyException();
    }
}
