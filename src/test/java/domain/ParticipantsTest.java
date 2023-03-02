package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantsTest {

    @Test
    @DisplayName("참여자들의 초기 카드 목록을 제공한다.")
    void getInitialTest() {
        Dealer dealer = new Dealer();
        Players players = new Players("pobi,jason");
        Participants participants = new Participants(dealer, players);

        dealer.addCard(new Card(CardNumber.ACE, CardPattern.SPADE));

        players.addCard(0, new Card(CardNumber.KING, CardPattern.SPADE));
        players.addCard(1, new Card(CardNumber.SEVEN, CardPattern.SPADE));

        Map<String, List<String>> result = new HashMap<>();
        result.put("딜러", List.of("A스페이드"));
        result.put("pobi", List.of("K스페이드"));
        result.put("jason", List.of("7스페이드"));

        Assertions.assertThat(participants.getInitial()).usingRecursiveComparison().isEqualTo(result);
    }
}
