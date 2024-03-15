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

class PotTest {
    @DisplayName("승패 결과로 베팅 수익을 계산한다.")
    @Test
    void calculatePot() {
        //given
        Player player = generatePlayer();
        RoundResult roundResult = generateRoundResult(player);
        Pot roundPot = generatePot(player);

        //when
        roundPot = roundPot.calculatePlayerPot(roundResult);
        BetAmount calculatedAmount = roundPot.getBetAmount(player);

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

    private Pot generatePot(Player player) {
        Map<Player, BetAmount> pot = new LinkedHashMap<>();
        pot.put(player, new BetAmount(10000));
        return new Pot(pot);
    }
}
