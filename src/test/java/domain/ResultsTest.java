package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ResultsTest {

    @Test
    @DisplayName("딜러의 점수와 참가자를 입력했을 때, 승자와 패자를 나눈다.")
    void createResults_thenClassifyParticipants() {
        //given
        final Participants participants = Participants.from(List.of("준팍", "파워", "범블비", "서브웨이"));
        final Deck deck = Deck.from(new TestCardGenerator());
        participants.drawCard(deck);

        //when
        final Results results = Results.of(12, participants.getParticipants());

        //then
        Assertions.assertThat(results.getLosers()).isEqualTo(List.of("파워", "범블비"));
    }
}
