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

    @Test
    @DisplayName("승패 결과 만들기 테스트(딜러 10패, 플레이어 모두 승)")
    public void testMakeResult() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.SPADE, Letter.SIX)));
        Dealer dealer = new Dealer(cards);
        List<Player> players = IntStream.range(0, 10)
            .mapToObj(i -> new Player(cards, "test"))
            .collect(Collectors.toList());

        //when
        Map<Participant, Result> results = blackJackResultMaker.makeResult(dealer, players);

        //then
        for (Participant participant : results.keySet()) {
            if (participant instanceof Dealer) {
                assertThat(results.get(participant).getDefeat()).isEqualTo(10);
                assertThat(results.get(participant).getVictory()).isEqualTo(0);
                continue;
            }
            assertThat(results.get(participant).getVictory()).isEqualTo(1);
            assertThat(results.get(participant).getDefeat()).isEqualTo(0);
        }
    }
}