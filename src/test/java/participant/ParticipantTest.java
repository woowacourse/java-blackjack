package participant;

import card.Card;
import card.CardNumber;
import card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("21이 넘는 패를 가지면 Bust이다.")
    void test1() {
        // given
        Participant participant = new Player("율무", 10000);

        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.NINE));
        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));

        // when
        boolean result = participant.isBust();

        // then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    @DisplayName("21이 넘지 않으면 Bust가 아니다.")
    void test2() {
        // given
        Participant participant = new Player("율무", 10000);

        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.NINE));
        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TWO));

        // when
        boolean result = participant.isBust();

        // then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    @DisplayName("참가자가 자신 패의 합을 구할 수 있다.")
    void test3() {
        // given
        Participant participant = new Player("율무", 10000);

        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.NINE));
        participant.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TWO));

        // when
        int result = participant.score();

        // then
        Assertions.assertThat(result)
                .isEqualTo(21);
    }
}