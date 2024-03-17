package blackjack.domain.profit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.entry;

import java.util.List;
import java.util.Map;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Participants participants = new Participants(List.of("pobi"), List.of(new PlayerMoney(1000)));

        assertThatCode(() -> new Profit(participants))
                .doesNotThrowAnyException();
    }

    @DisplayName("전체 참여자들의 수익금을 계산할 수 있다.")
    @Test
    void calculateProfit() {
        Participants allPlayerBustParticipants = createAllPlayerBustParticipants();
        Profit profit = new Profit(allPlayerBustParticipants);

        Map<String, Double> result = profit.createAllProfit();

        assertThat(result)
                .hasSize(2)
                .containsExactly(entry("딜러", 1000.0), entry("pobi", -1000.0));
    }

    private Participants createAllPlayerBustParticipants() {
        Participants participants = new Participants(
                List.of("pobi"),
                List.of(new PlayerMoney(1000)));
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));
            player.hit(new Card(CardRank.JACK, CardShape.DIAMOND));
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        }

        return participants;
    }
}
