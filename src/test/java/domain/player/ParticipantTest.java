package domain.player;

import domain.card.Card;
import domain.card.CardArea;
import domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.*;
import static domain.fixture.CardAreaFixture.equal16CardArea;
import static domain.fixture.CardAreaFixture.withTwoCard;
import static domain.fixture.GamblerFixture.말랑;
import static domain.fixture.GamblerFixture.코다;
import static domain.fixture.NameFixture.코다이름;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Participant 은")
class ParticipantTest {

    final CardArea cardArea = equal16CardArea();
    final Participant participant = new Participant(코다이름(), cardArea) {
        @Override
        public boolean canHit() {
            return false;
        }

        @Override
        public boolean hitOrStay(final CardDeck cardDeck) {
            return false;
        }
    };

    @Test
    void 이름과_area_를_가진다() {
        // when & then
        assertDoesNotThrow(() -> new Participant(코다이름(), equal16CardArea()) {
            @Override
            public boolean canHit() {
                return false;
            }

            @Override
            public boolean hitOrStay(final CardDeck cardDeck) {
                return false;
            }
        });
    }

    @Test
    void hit_시_카드를_추가한다() {
        // when
        final int beforeSize = cardArea.cards().size();
        participant.hit(CardDeck.shuffledFullCardDeck());

        // then
        assertThat(cardArea.cards().size()).isEqualTo(beforeSize + 1);
    }

    @ParameterizedTest(name = "버스트에 상관없이 점수가 큰 수가 더 크다. 예를 들어 {0} 은 {1} 보다 높은 점수이다")
    @MethodSource("scoreCardArea")
    void 점수_비교를_할_수_있다(final CardArea large, final CardArea small) {
        final Participant 말랑 = 말랑(large);
        final Participant 코다 = 코다(small);

        // when & then
        assertThat(말랑.isLargerScoreThan(코다)).isTrue();
    }

    static Stream<Arguments> scoreCardArea() {
        final CardArea cardArea25 = withTwoCard(TEN, TWO);
        cardArea25.addCard(new Card(DIAMOND, FIVE));

        return Stream.of(
                Arguments.of(
                        Named.of("5", withTwoCard(TWO, FIVE)),
                        Named.of("4", withTwoCard(TWO, TWO))
                ),
                Arguments.of(
                        Named.of("21", withTwoCard(TEN, ACE)),
                        Named.of("20", withTwoCard(TEN, TEN))
                ),
                Arguments.of(
                        Named.of("25", cardArea25),
                        Named.of("21", withTwoCard(TWO, ACE))
                )
        );
    }
}
