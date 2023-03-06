import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private static final Card DIAMOND_TEN = new Card(CardShape.DIAMOND, CardRank.TEN);
    private static final Card DIAMOND_NINE = new Card(CardShape.DIAMOND, CardRank.NINE);
    private static final Card DIAMOND_EIGHT = new Card(CardShape.DIAMOND, CardRank.EIGHT);

    @DisplayName("딜러의 카드 합을 기준으로 플레이어의 승패를 결정한다.")
    @Test
    void resultSuccessTest() {
        Dealer dealer = new Dealer();
        Players players = Players.from(PlayerNames.from(List.of("pobi", "crong")));
        Player pobi = players.getPlayers().get(0);
        Player crong = players.getPlayers().get(1);

        giveCardsTo(dealer, List.of(DIAMOND_TEN, DIAMOND_NINE)); // 19

        giveCardsTo(pobi, List.of(DIAMOND_TEN, DIAMOND_TEN)); // 20
        giveCardsTo(crong, List.of(DIAMOND_TEN, DIAMOND_EIGHT)); // 18

        dealer.decideResults(players);

        assertThat(dealer.getGameResult().values())
                .containsExactly(Result.LOSE, Result.WIN);
    }

    public void giveCardsTo(Participant participant, List<Card> cards) {
        for (Card card : cards) {
            participant.receive(card);
        }
    }
}
