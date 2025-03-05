import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("플레이어는 이름으로 구별된다")
    @Test
    void playerName() {
        //given
        String name1 = "ad";
        String name2 = "dogy";

        //when
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        Player player3 = new Player(name1);

        //then
        assertThat(player1).isNotEqualTo(player2).isEqualTo(player3);
    }

    @DisplayName("카드는 문양과 숫자를 가진다.")
    @Test
    void card() {
        //given
        final var symbol = Symbol.SPADE;
        final var number = Number.ACE;

        //when //then
        assertThatCode(() -> new Card(symbol, number))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드의 문양은 4가지다.")
    @Test
    void cardSymbol() {
        //given

        //when
        Symbol[] values = Symbol.values();

        //then
        assertThat(values).hasSize(4);
    }

    @DisplayName("카드의 숫자는 1부터 k까지 13개다.")
    @Test
    void cardNumber() {
        //given

        //when
        Number[] values = Number.values();

        //then
        assertThat(values).hasSize(13);

    }
}
