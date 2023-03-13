package blackjack.domain.user;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.user.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static blackjack.domain.fixture.FixtureCard.스페이드_10;
import static blackjack.domain.fixture.FixtureCard.스페이드_8;
import static blackjack.domain.fixture.FixtureCard.하트_10;

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
                .isNotEmpty();
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
        @CsvSource(value = {"junho주노너무긴이름입니다:10자 이상의 이름을 가지면 예외", "'     ': 공백만 가지면 예외", "딜러:딜러 라는 이름을 가지면 예외"}, delimiter = ':')
        void _10자_이상의_이름을_가지면_예외(final String name, final String message) {
            //then
            Assertions.assertThatThrownBy(() -> new Player(name))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 플레이어는_22점_이상이면_Bust_상태다() {
        // given
        CardPack cardPack = new CardPack(List.of(스페이드_8, 스페이드_10, 하트_10));
        Player player = new Player("dummy");

        // when
        player.drawCard(cardPack);
        player.drawCard(cardPack);
        player.drawCard(cardPack);

        // then
        Assertions.assertThat(player.isBust()).isTrue();
    }
}
