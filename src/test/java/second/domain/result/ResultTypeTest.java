package second.domain.result;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import second.domain.card.Card;
import second.domain.card.Rank;
import second.domain.card.Suit;
import second.domain.gamer.Dealer;
import second.domain.gamer.Player;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTypeTest {
    @ParameterizedTest
    @MethodSource("generate")
    void of(Rank[] ranks, Rank[] anotherRanks, ResultType expected) {
        Player player = new Player("pobi");
        for (Rank rank : ranks) {
            player.draw(Card.of(rank, Suit.CLOVER));
        }

        Dealer dealer = new Dealer();
        for (Rank rank : anotherRanks) {
            dealer.draw(Card.of(rank, Suit.CLOVER));
        }

        assertThat(ResultType.of(player, dealer)).isEqualTo(expected);
    }

    private static Stream<Arguments> generate() {
        return Stream.of(
                Arguments.of(new Rank[]{Rank.ACE, Rank.K}, new Rank[]{Rank.FIVE, Rank.SIX}, ResultType.ONLY_PLAYER_BLACK_JACK),
                Arguments.of(new Rank[]{Rank.ACE, Rank.K}, new Rank[]{Rank.ACE, Rank.K}, ResultType.DRAW),
                Arguments.of(new Rank[]{Rank.TWO, Rank.THREE}, new Rank[]{Rank.K, Rank.J, Rank.NINE}, ResultType.WIN),
                Arguments.of(new Rank[]{Rank.TWO, Rank.THREE}, new Rank[]{Rank.TWO, Rank.TWO}, ResultType.WIN),
                Arguments.of(new Rank[]{Rank.K, Rank.J, Rank.NINE}, new Rank[]{Rank.FIVE, Rank.SIX}, ResultType.LOSE),
                Arguments.of(new Rank[]{Rank.TWO, Rank.THREE}, new Rank[]{Rank.K, Rank.J}, ResultType.LOSE)
        );
    }
}

