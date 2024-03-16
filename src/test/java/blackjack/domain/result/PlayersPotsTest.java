package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.testutil.CustomDeck;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersPotsTest {
    @DisplayName("승패 결과로 베팅 수익을 계산한다.")
    @Test
    void calculatePlayersPots() {
        //given
        Player player = generatePlayer();
        RoundResult roundResult = generateRoundResult(player);
        PlayersPots roundPlayersPots = generatePlayersPots(player);

        //when
        roundPlayersPots = roundPlayersPots.calculatePlayersPots(roundResult);
        BetAmount calculatedAmount = roundPlayersPots.getBetAmount(player);

        //then
        assertThat(calculatedAmount.amount()).isEqualTo(15000);
    }

    private Player generatePlayer() {
        Deck deck = new CustomDeck(List.of(Number.ACE, Number.KING));
        return new Player(new Name("mason"), deck);
    }

    private RoundResult generateRoundResult(Player player) {
        Map<Player, HandResult> playersResult = new LinkedHashMap<>();
        playersResult.put(player, HandResult.BLACKJACK);
        return new RoundResult(playersResult);
    }

    private PlayersPots generatePlayersPots(Player player) {
        Map<Player, BetAmount> playersPots = new LinkedHashMap<>();
        playersPots.put(player, new BetAmount(10000));
        return new PlayersPots(playersPots);
    }
}
