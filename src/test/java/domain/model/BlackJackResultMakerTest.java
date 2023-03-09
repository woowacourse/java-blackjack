package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.type.Denomination;
import domain.type.Suit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultMakerTest {

    private final BlackJackResultMaker blackJackResultMaker = new BlackJackResultMaker();
    private Dealer dealer;
    private Cards playerCards;
    private List<Player> players;

    @BeforeEach
    public void beforeEach() {
        Cards dealerCards = new Cards(List.of(new Card(Suit.SPADE, Denomination.TWO)));
        dealer = new Dealer(dealerCards);
        playerCards = new Cards(List.of(new Card(Suit.SPADE, Denomination.SIX)));
        players = IntStream.range(0, 10)
            .mapToObj(i -> new Player(playerCards, "test"))
            .collect(Collectors.toList());
    }

    @Test
    @DisplayName("플레이어들 승패 결과 만들기 테스트(플레이어 모두 승)")
    public void testMakeParticipantsResult() {
        //given
        //when
        Map<Player, Result> results = blackJackResultMaker.makePlayersResult(dealer, players);

        //then
        for (Player player : results.keySet()) {
            assertThat(results.get(player).getVictory()).isOne();
            assertThat(results.get(player).getDraw()).isZero();
            assertThat(results.get(player).getDefeat()).isZero();
        }
    }

    @Test
    @DisplayName("딜러 승패 결과 만들기 테스트(딜러 10패, 플레이어 모두 승)")
    public void testMakeDealerResult() {
        //given
        //when
        Result result = blackJackResultMaker.makeDealerResult(dealer, players);

        //then
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDraw()).isEqualTo(0);
        assertThat(result.getDefeat()).isEqualTo(10);
    }

    @Test
    @DisplayName("무승부 결과 만들기 테스트(딜러 10무, 플레이어 모두 무)")
    public void testPlayerDealerDraw() {
        //given
        dealer = new Dealer(playerCards);

        //when
        Result dealerResult = blackJackResultMaker.makeDealerResult(dealer, players);
        Map<Player, Result> playerResults = blackJackResultMaker.makePlayersResult(dealer, players);

        //then
        assertThat(dealerResult.getVictory()).isEqualTo(0);
        assertThat(dealerResult.getDraw()).isEqualTo(10);
        assertThat(dealerResult.getDefeat()).isEqualTo(0);

        for (Player player : playerResults.keySet()) {
            assertThat(playerResults.get(player).getVictory()).isZero();
            assertThat(playerResults.get(player).getDraw()).isOne();
            assertThat(playerResults.get(player).getDefeat()).isZero();
        }
    }
}
