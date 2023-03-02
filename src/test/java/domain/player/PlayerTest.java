package domain.player;

import domain.area.CardArea;
import domain.card.Card;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardValue.*;
import static domain.fixture.CardAreaFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Player 은")
class PlayerTest {

    final CardArea cardArea = new CardArea(
            new Card(CardShape.CLOVER, TEN),
            new Card(CardShape.CLOVER, SEVEN)
    );

    static Stream<Arguments> canNotMoreCard() {


        return Stream.of(
                Arguments.of(under21CardArea(), HitState.STAY),
                Arguments.of(over21CardArea(), HitState.INIT),
                Arguments.of(over21CardArea(), HitState.STAY),
                Arguments.of(over21CardArea(), HitState.HIT)
        );
    }

    @Test
    void 참가자는_상태를_바꿀_수_있다() {
        // given
        final Player player = new Player(Name.of("player1"), equal16CardArea());

        // when
        assertDoesNotThrow(() -> player.changeState(HitState.HIT));
    }

    @ParameterizedTest(name = "참가자는 버스트되지 않았으면서, STAY 원하지 않을 때(ex: {0}) 카드를 더 받을 수 있다.")
    @EnumSource(mode = EXCLUDE, names = {"STAY"})
    void 참가자는_버스트되지_않았으면서_STAY_를_원하지_않을_때_카드를_더_받을_수_있다(final HitState hitState) {
        // given
        final Player player = new Player(Name.of("player1"), equal16CardArea());
        player.changeState(hitState);

        // when & then
        assertTrue(player.canHit());
    }

    @ParameterizedTest(name = "참가자는 버스트되었거나, STAY 를 원한다면 카드를 더 받을 수 없다")
    @MethodSource("canNotMoreCard")
    void 참가자는_버스트되었거나_STAY_를_원한다면_카드를_더_받을_수_없다(final CardArea cardArea, final HitState hitState) {
        // given
        final Player player = new Player(Name.of("player1"), cardArea);
        player.changeState(hitState);

        // when & then
        assertFalse(player.canHit());
    }
}