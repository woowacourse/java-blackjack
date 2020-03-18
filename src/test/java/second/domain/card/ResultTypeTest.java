package second.domain.card;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import second.domain.player.Dealer;
import second.domain.player.Player;
import second.domain.result.ResultType;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTypeTest {
    @ParameterizedTest
    @MethodSource("generateCards")
    void from_테스트(Rank[] ranks, Rank[] anotherRanks, ResultType expected) {
        Player player = new Player("pobi");
        for (Rank rank : ranks) {
            player.draw(Card.of(rank, Suit.CLOVER));
        }

        Dealer dealer= new Dealer();
        for (Rank rank : anotherRanks) {
            dealer.draw(Card.of(rank, Suit.CLOVER));
        }

        assertThat(ResultType.from(player, dealer)).isEqualTo(expected);
    }

    private static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(new Rank[]{Rank.TWO, Rank.THREE}, new Rank[]{Rank.FIVE, Rank.SIX}, ResultType.LOSE),
                Arguments.of(new Rank[]{Rank.K, Rank.THREE}, new Rank[]{Rank.FIVE, Rank.SIX}, ResultType.WIN),
                Arguments.of(new Rank[]{Rank.K, Rank.J, Rank.NINE}, new Rank[]{Rank.FIVE, Rank.SIX}, ResultType.LOSE),
                Arguments.of(new Rank[]{Rank.TWO, Rank.THREE}, new Rank[]{Rank.K, Rank.J, Rank.NINE}, ResultType.WIN)
        );
    }
}
