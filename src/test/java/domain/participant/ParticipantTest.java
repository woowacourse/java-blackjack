package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("성공: 카드를 한 장 받을 수 있다.")
    void receive_NoException_OneCard() {
        Participant participant = new Player(new Name("name"));
        participant.receive(new Card(Rank.ACE, Symbol.CLUB));
        assertThat(participant.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("성공: 카드를 여러 장 받을 수 있다.")
    void receive_NoException_SeveralCards() {
        Participant participant = new Player(new Name("name"));
        participant.receive(List.of(
            new Card(Rank.ACE, Symbol.CLUB),
            new Card(Rank.ACE, Symbol.HEART)
        ));
        assertThat(participant.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("성공: 버스트 여부를 알 수 있다(경계값 22점)")
    void isBusted_NoException_OverScore() {
        Participant participant = new Player(new Name("name"));
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
        Participant participant = new Player(new Name("name"));
        participant.receive(new Card(Rank.NINE, Symbol.CLUB));
        assertThat(participant.score()).isEqualTo(9);
    }
}
