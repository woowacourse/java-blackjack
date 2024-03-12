package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantCardsTest {

    @Test
    @DisplayName("성공: 카드를 한 장 받을 수 있다")
    void receive_NoException() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(new Card(Rank.KING, Symbol.DIAMOND));
        assertThat(participantCards.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("성공: 카드를 여러장 받을 수 있다")
    void receive_NoException_SeveralCards() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.KING, Symbol.DIAMOND),
            new Card(Rank.KING, Symbol.HEART)
        ));
        assertThat(participantCards.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("성공: Ace 여부 반환(Ace 있음)")
    void hasAce_True() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.ACE, Symbol.HEART),
            new Card(Rank.KING, Symbol.CLUB)
        ));
        assertThat(participantCards.hasAce()).isTrue();
    }

    @Test
    @DisplayName("실패: Ace 여부 반환(Ace 없음)")
    void hasAce_False() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.TWO, Symbol.HEART),
            new Card(Rank.KING, Symbol.CLUB)
        ));
        assertThat(participantCards.hasAce()).isFalse();
    }

    @Test
    @DisplayName("성공: 카드 점수의 합을 계산(경계값 21점)")
    void calculateScore_NoException_OneKingOneSevenOneFour() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.KING, Symbol.DIAMOND),
            new Card(Rank.SEVEN, Symbol.HEART),
            new Card(Rank.FOUR, Symbol.CLUB)
        ));
        assertThat(participantCards.totalScore()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("성공: 카드 점수의 합을 계산(경계값 22점)")
    void calculateScore_NoException_OneKingOneSevenOneFive() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.KING, Symbol.DIAMOND),
            new Card(Rank.SEVEN, Symbol.HEART),
            new Card(Rank.FIVE, Symbol.CLUB)
        ));
        assertThat(participantCards.totalScore()).isEqualTo(new Score(22));
    }

    // 12, 22로 계산될 수 있으나, 21이하 점수 중 최고점인 12을 반환한다.
    @Test
    @DisplayName("성공: 점수 합 계산(ACE 2장 -> 12점)")
    void calculateScore_NoException_TwoAces() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.ACE, Symbol.DIAMOND),
            new Card(Rank.ACE, Symbol.HEART)
        ));
        assertThat(participantCards.totalScore()).isEqualTo(new Score(12));
    }

    // 11, 21, 31으로 계산될 수 있으나, 21이하 점수 중 최고점인 21을 반환한다.
    @Test
    @DisplayName("성공: 점수 합 계산(ACE 2장, NINE 1장 -> 21점)")
    void calculateScore_NoException_TwoAcesOneNine() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.ACE, Symbol.DIAMOND),
            new Card(Rank.ACE, Symbol.HEART),
            new Card(Rank.NINE, Symbol.CLUB)
        ));
        assertThat(participantCards.totalScore()).isEqualTo(new Score(21));
    }
}
