package domain.player;

import domain.area.CardArea;
import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static domain.card.CardValue.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Participant 은")
class ParticipantTest {

    final Name name = Name.of("name");

    final CardArea cardArea = new CardArea(
            new Card(CardShape.SPADE, CardValue.TEN),
            new Card(CardShape.SPADE, CardValue.TEN));

    final Participant participant = new Participant(name, cardArea) {
        @Override
        public boolean canHit() {
            return false;
        }
    };

    @Test
    void 추상클래스다() {
        //when & then
        assertThat(Participant.class).isAbstract();
    }

    @Test
    void 이름과_area_가진다() {
        // when & then
        assertDoesNotThrow(() -> new Participant(name, cardArea) {
            @Override
            public boolean canHit() {
                return false;
            }
        });
    }

    @Test
    void 카드를_추가할_수_있다() {
        // when
        final int beforeSize = cardArea.cards().size();
        participant.hit(new Card(CardShape.SPADE, TEN));

        // then
        assertThat(cardArea.cards().size()).isEqualTo(beforeSize + 1);
    }
}