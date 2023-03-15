package domain.player.participant;

import domain.card.Card;
import domain.card.CardShape;
import domain.player.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.ACE;
import static domain.card.CardValue.SEVEN;
import static domain.card.CardValue.TEN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Participant 은")
class ParticipantTest {

    final Participant participant = new Participant(new Name("name"), Money.wons(0));

    @BeforeEach
    void makeCardScoreTwenty() {
        participant.hit(new Card(CLOVER, TEN));
        participant.hit(new Card(SPADE, TEN));
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 미만일 경우 카드를 더 받을 수 있다.")
    void test_canHit_underScore21() {
        // when & then
        assertTrue(participant.canHit());
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 이상일 경우 카드를 더 받을 수 없다.")
    void test_canHit_overScore21() {
        // when
        participant.hit(new Card(CLOVER, ACE));

        // then
        assertFalse(participant.canHit());
    }

    @Test
    @DisplayName("faceUpFirstDeal() : participant는 처음 받은 카드 두 장을 모두 보여줘야한다.")
    void test_faceUpFirstDeal() throws Exception {
        // given
        final Participant participant = new Participant(new Name("name1"), Money.wons(0));
        participant.hit(new Card(CardShape.CLOVER, TEN));
        participant.hit(new Card(CardShape.CLOVER, SEVEN));

        // when
        final List<Card> cards = participant.faceUpFirstDeal();

        // then
        Assertions.assertThat(cards).containsExactly(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );
    }
}
