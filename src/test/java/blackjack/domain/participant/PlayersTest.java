package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Betting;
import blackjack.domain.card.CardDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("플레이어 이름이 중복되면 예외를 반환한다.")
    void duplicatedNames() {
        // given
        List<String> nameStrings = List.of("name", "name");
        CardDeck deck = CardDeck.createGameDeck();

        // then
        assertThatThrownBy(() -> new Players(nameStrings, deck, name -> new Betting(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 수가 2인보다 작으면 예외를 반환한다.")
    void understaffed() {
        // given
        List<String> nameStrings = List.of("name");
        CardDeck deck = CardDeck.createGameDeck();

        // then
        assertThatThrownBy(() -> new Players(nameStrings, deck, name -> new Betting(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 2~8인의 플레이어가 참가할 수 있습니다.");
    }

    @Test
    @DisplayName("플레이어의 수가 2인보다 작으면 예외를 반환한다.")
    void overstaffed() {
        // given
        List<String> nameStrings = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        CardDeck deck = CardDeck.createGameDeck();

        // then
        assertThatThrownBy(() -> new Players(nameStrings, deck, name -> new Betting(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 2~8인의 플레이어가 참가할 수 있습니다.");
    }
}
