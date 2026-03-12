package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    private final Cards deck = Cards.of();
    private final List<Card> initialCards = new ArrayList<>(List.of(
            Card.of(Rank.ACE, Suit.DIAMOND),
            Card.of(Rank.FOUR, Suit.CLOVER)));

    private final List<String> userNames = List.of("pobi", "jason");
    private final Players players = Players.of(deck, userNames);
    private final Dealer dealer = Dealer.of(initialCards);
    private final BlackjackResult blackjackResult = BlackjackResult.of(dealer, players);

    @Test
    @DisplayName("BlackjackResult 객체 생성 시 플레이어들의 승패 저장 확인")
    void player_result_test() {
        assertThat(blackjackResult.getPlayersResult()).hasSize(userNames.size());
    }

    @Test
    @DisplayName("플레이어들 승리 결과, 딜러는 플레이어 승/패 횟수의 반대 결과")
    void player_isBust_result_lose() {
        assertThat(blackjackResult.getPlayersResult()).isEqualTo(List.of(GameResult.LOSE, GameResult.LOSE));
    }

    @Test
    @DisplayName("딜러의 결과는 플레이어들 승/패 여부 횟수의 반대이다.")
    void dealer_result() {
        assertThat(blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.LOSE)).isEqualTo(2);
    }
}