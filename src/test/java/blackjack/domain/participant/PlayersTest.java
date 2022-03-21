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
}
