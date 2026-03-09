package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    private final List<String> userNames = List.of("pobi", "jason");

    @Test
    @DisplayName("BlackjackResult 객체 생성 시 플레이어들의 승패 저장 확인")
    void player_result_test() {
        Cards cards = Cards.of();
        Dealer dealer = Dealer.of(cards.drawInitialHand());
        Players players = Players.of(cards, userNames);

        BlackjackResult result = BlackjackResult.of(dealer, players);

        int expect = userNames.size();
        assertThat(result.getPlayersResult()).hasSize(expect);
    }
}