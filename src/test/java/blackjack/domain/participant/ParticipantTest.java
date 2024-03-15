package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ParticipantTest {

    @Test
    @DisplayName("성공: 카드를 한 장 받을 수 있다.")
    void receive_NoException_OneCard() {
        Participant participant = Player.nameOf("name");
        participant.receive(new Card(Rank.ACE, Symbol.CLUB));
        assertThat(participant.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("실패: 카드를 받을 수 없는 상태에서 카드 한 장 받기.")
    void receive_Exception_OneCard() {
        Participant participant = Player.nameOf("name");
        participant.receive(new Card(Rank.TEN, Symbol.CLUB));
        participant.receive(new Card(Rank.TEN, Symbol.DIAMOND));
        participant.receive(new Card(Rank.TEN, Symbol.HEART));

        System.out.println(participant.isReceivable());

        assertThatCode(() -> participant.receive(new Card(Rank.TEN, Symbol.SPADE)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("성공: 카드를 여러 장 받을 수 있다.")
    void receive_NoException_SeveralCards() {
        Participant participant = Player.nameOf("name");
        participant.receive(List.of(
                new Card(Rank.ACE, Symbol.CLUB),
                new Card(Rank.ACE, Symbol.HEART)
        ));
        assertThat(participant.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("실패: 카드를 받을 수 없는 상태에서 카드를 여러 장 받기")
    void receive_Exception_SeveralCards() {
        Participant participant = Player.nameOf("name");
        participant.receive(List.of(
                new Card(Rank.KING, Symbol.CLUB),
                new Card(Rank.KING, Symbol.HEART),
                new Card(Rank.KING, Symbol.DIAMOND)));

        assertThatCode(() -> participant.receive(List.of(
                new Card(Rank.QUEEN, Symbol.CLUB),
                new Card(Rank.QUEEN, Symbol.HEART)
        ))).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("성공: 버스트 여부를 알 수 있다(경계값 22점)")
    void isBusted_NoException_OverScore() {
        Participant participant = Player.nameOf("name");
        participant.receive(List.of(
                new Card(Rank.KING, Symbol.CLUB),
                new Card(Rank.QUEEN, Symbol.HEART),
                new Card(Rank.TWO, Symbol.DIAMOND)
        ));
        assertThat(participant.isBusted()).isTrue();
    }

    @Test
    @DisplayName("성공: 점수를 반환할 수 있다")
    void score_NoException() {
        Participant participant = Player.nameOf("name");
        participant.receive(new Card(Rank.NINE, Symbol.CLUB));
        assertThat(participant.calculateScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("성공: 객체간 점수를 비교할 수 있다")
    void isBiggerThan() {
        Participant bigParticipant = Player.nameOf("big");
        Participant smallParticipant = Player.nameOf("small");

        bigParticipant.receive(new Card(Rank.KING, Symbol.CLUB));
        smallParticipant.receive(new Card(Rank.TWO, Symbol.CLUB));

        assertThat(bigParticipant.isBiggerThan(smallParticipant)).isTrue();
        assertThat(smallParticipant.isBiggerThan(bigParticipant)).isFalse();
    }
}
