package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.type.Letter;
import domain.type.Suit;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultMakerTest {

    private final BlackJackResultMaker blackJackResultMaker = new BlackJackResultMaker();
    private final Cards dealerCards = new Cards(Set.of(new Card(Suit.SPADE, Letter.TWO)));
    private final Dealer dealer = new Dealer(dealerCards);
    private final Cards playerCards = new Cards(Set.of(new Card(Suit.SPADE, Letter.SIX)));
    private final List<Player> players = IntStream.range(0, 10)
        .mapToObj(i -> new Player(playerCards, "test"))
        .collect(Collectors.toList());

    @Test
    @DisplayName("플레이어들 승패 결과 만들기 테스트(플레이어 모두 승)")
    public void testMakeParticipantsResult() {
        //given
        //when
        Map<Player, Result> results = blackJackResultMaker.makePlayersResult(dealer, players);

        //then
        for (Player player : results.keySet()) {
            assertThat(results.get(player).getVictory()).isEqualTo(1);
            assertThat(results.get(player).getDefeat()).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("딜러 승패 결과 만들기 테스트(딜러 10패, 플레이어 모두 승)")
    public void testMakeDealerResult() {
        //given
        //when
        Result result = blackJackResultMaker.makeDealerResult(dealer, players);

        //then
        assertThat(result.getDefeat()).isEqualTo(10);
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDraw()).isEqualTo(0);
    }
}
