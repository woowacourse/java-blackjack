package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Betting;
import blackjack.domain.participant.Dealer;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.dto.BlackjackResult;
import blackjack.dto.NamesInput;
import blackjack.dto.PlayerInfos;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들")
public class PlayersTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Dealer dealer;
    private Players players;
    private final String bettingAmountA = "10000";
    private final String bettingAmountB = "200000";

    private final PlayerInfos playerInfos = PlayerInfos.of(
            NamesInput.from(List.of("a", "b")),
            List.of(new Betting(bettingAmountA), new Betting(bettingAmountB))
    );

    @BeforeEach
    void setUp() {
        dealer = Dealer.from(shuffleStrategy);
        players = Players.from(playerInfos);
    }

    @DisplayName("플레이어들의 승패를 계산한다.")
    @Test
    void decideResult() {
        //given
        dealer.initialDeal();
        players.initialDeal(dealer::draw);
        String expectedProfitSum = String.valueOf(Integer.parseInt(bettingAmountA) + Integer.parseInt(bettingAmountB));

        //when
        new Judge().judge(dealer, players);

        //then
        assertThat(BlackjackResult.of(dealer, players).playerProfits().get(0).profit()).isEqualTo("-" + bettingAmountA);
        assertThat(BlackjackResult.of(dealer, players).playerProfits().get(1).profit()).isEqualTo("-" + bettingAmountB);
        assertThat(BlackjackResult.of(dealer, players).dealerProfit()).isEqualTo(expectedProfitSum);
    }

    @DisplayName("딜러가 버스트된 경우 버스트 안된 참가자는 승리한다.")
    @Test
    void dealerBustPlayerWins() {
        //given
        players.initialDeal(dealer::draw);
        Dealer bustedDealer = Dealer.from(shuffleStrategy);

        //when
        IntStream.range(0, 9)
                .forEach(i -> players.getPlayers().get(1).draw(dealer.draw()));
        bustedDealer.initialDeal();
        bustedDealer.draw();

        //then
        new Judge().judge(dealer, players);
        assertThat(Double.parseDouble(BlackjackResult.of(dealer, players).playerProfits().get(0).profit()))
                .isEqualTo(Double.parseDouble(bettingAmountA));
    }
}
