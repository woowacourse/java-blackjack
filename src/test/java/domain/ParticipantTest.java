package domain;

import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("딜러는 참가자에게 카드를 분배한다.")
    void dealCardToPlayerTest() {
        Player participant = new Player("echo");
        participant.dealt(new Card(Denomination.ACE, Suit.SPADE));
        Assertions.assertThat(participant)
            .extracting("hand")
            .asList()
            .hasSize(1);
        Assertions.assertThat(participant)
            .extracting("hand")
            .asList()
            .first()
            .isEqualTo(new Card(Denomination.ACE, Suit.SPADE));
    }

    @Test
    @DisplayName("처음에 카드를 지급받지 않은 경우 카드 조회시 오류를 던진다.")
    void getReadyCardsTestFailed() {
        Player participant = new Player("echo");
        Assertions.assertThatThrownBy(participant::faceUpInitialHand)
            .isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("처음에 지급받은 카드를 반환한다.")
    void getReadyCardsTestSuccess() {
        Player participant = new Player("echo");
        participant.dealt(new Card(Denomination.ACE, Suit.SPADE));
        participant.dealt(new Card(Denomination.THREE, Suit.HEART));
        Assertions.assertThat(participant.faceUpInitialHand())
            .containsExactly(new Card(Denomination.ACE, Suit.SPADE), new Card(Denomination.THREE, Suit.HEART));
    }

    @Test
    @DisplayName("현재의 점수를 반환한다.")
    void calculateScore() {
        Player participant = new Player("echo");
        participant.dealt(new Card(Denomination.ACE, Suit.SPADE));
        participant.dealt(new Card(Denomination.THREE, Suit.HEART));
        Assertions.assertThat(participant.calculatePoint()).isEqualTo(4);
    }
}
