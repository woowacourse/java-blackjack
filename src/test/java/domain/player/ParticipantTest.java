package domain.player;

import domain.card.Card;
import domain.card.CardArea;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.TEN;
import static domain.fixture.CardAreaFixture.equal16CardArea;
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
    };

    @Test
    void 이름과_area_가진다() {
        // when & then
        assertDoesNotThrow(() -> new Participant(코다이름(), equal16CardArea()) {
            @Override
            public boolean canHit() {
                return false;
            }
        });
    }

    @Test
    void hit_시_카드를_추가한다() {
        // when
        final int beforeSize = cardArea.cards().size();
        participant.hit(new Card(SPADE, TEN));

        // then
        assertThat(cardArea.cards().size()).isEqualTo(beforeSize + 1);
    }
}