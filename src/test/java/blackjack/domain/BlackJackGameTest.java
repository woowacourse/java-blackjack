package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.*;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.state.BlackJack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackJackGameTest {

    @Test
    @DisplayName("수익 계산 테스트")
    void calculateProfit() {
        Dealer dealer = new Dealer();
        Player player1 = new Player(new Nickname("air"), new BlackJack(new Cards()));
        player1.betting(new BettingMoney("10000"));
        Player player2 = new Player(new Nickname("curry"), new Bust(new Cards()));
        player2.betting(new BettingMoney("20000"));
        Player player3 = new Player(new Nickname("jordan"), new Stay(new Cards()));
        player3.betting(new BettingMoney("30000"));

        Players players = new Players(
                Arrays.asList(player1, player2, player3)
        );

        Map<Player, MatchResult> result = new LinkedHashMap<>();
        result.put(player1, MatchResult.WIN);
        result.put(player2, MatchResult.LOSE);
        result.put(player3, MatchResult.DRAW);

        BlackJackGame blackJackGame = new BlackJackGame(players);
        ProfitResult profitResult = blackJackGame.calculateProfit(result);
        Map<Participant, BigDecimal> finalResult = profitResult.getProfitResult();
        assertEquals(finalResult.get(dealer), new BigDecimal("5000"));
        assertEquals(finalResult.get(player1), new BigDecimal("15000"));
        assertEquals(finalResult.get(player2), new BigDecimal("-20000"));
        assertEquals(finalResult.get(player3), new BigDecimal("0"));
    }
}
