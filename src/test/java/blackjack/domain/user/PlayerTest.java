package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardpack.CardPack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void 카드를_뽑을_수_있다() {
        // given
        Player player = new Player("주노");
        CardPack cardPack = new CardPack();

        // when
        player.drawCard(cardPack);

        // then
        Assertions.assertThat(player.showCards())
                .containsExactly(new Card(CardNumber.KING, CardShape.CLOVER));
    }

    @Nested
    class 플레이어_이름은 {

        @Test
        void _이름을_가진다() {
            //given
            String name = "junho";

            //when
            Player player = new Player(name);

            //then
            Assertions.assertThat(player.getName())
                    .isEqualTo(name);
        }

        @ParameterizedTest(name = "{1}")
        @CsvSource(value = {"junho주노너무긴이름입니다:10자 이상의 이름을 가지면 예외", "'     ': 공백만 가지면 예외",
                "딜러:딜러 라는 이름을 가지면 예외"}, delimiter = ':')
        void _10자_이상의_이름을_가지면_예외(final String name, final String message) {
            //then
            Assertions.assertThatThrownBy(() -> new Player(name))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
