package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    @Test
    @DisplayName("카드 숫자 합으로 승패를 결정한다.")
    void determineWinStatus() {
        // TODO: 리팩토링 시급
        ParticipantName kirby = new ParticipantName("kirby");
        ParticipantName baekho = new ParticipantName("baekho");
        ParticipantName pobi = new ParticipantName("pobi");

        Score dealerScore = new Score(20);
        Participant participant1 = Participant.from("kirby");
        List<Card> cards1 = List.of(new Card(CardNumber.TEN, CardShape.CLOVER), new Card(CardNumber.ACE, CardShape.CLOVER));
        cards1.forEach(participant1::addCard);

        Participant participant2 = Participant.from("baekho");
        List<Card> card2 = List.of(new Card(CardNumber.TEN, CardShape.SPADE), new Card(CardNumber.NINE, CardShape.SPADE));
        card2.forEach(participant2::addCard);

        Participant participant3 = Participant.from("pobi");
        List<Card> card3 = List.of(new Card(CardNumber.TEN, CardShape.DIA), new Card(CardNumber.KING, CardShape.DIA));
        card3.forEach(participant3::addCard);

        Participants participants = new Participants(List.of(participant1, participant2, participant3));
        Map<ParticipantName, WinStatus> finalResult = participants.determineWinStatus(dealerScore);

        // TODO: 순서대로 검증도 되게
        assertThat(finalResult).containsExactlyInAnyOrderEntriesOf(Map.of(
                kirby, WinStatus.WIN,
                baekho, WinStatus.LOSE,
                pobi, WinStatus.DRAW));
    }

    @Test
    @DisplayName("중복된 플레이어를 생성할 수 없다.")
    void validateDuplicate() {
        // given
        ParticipantName participantName = new ParticipantName("kirby");
        Hands hands = new Hands(new Card(CardNumber.TEN, CardShape.SPADE), new Card(CardNumber.NINE, CardShape.SPADE));
        Participant participant = Participant.from("kirby");
        List<Participant> participants = List.of(participant, participant);

        // when & then
        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름의 참여자는 참여할 수 없습니다.");
    }
}
