package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 카드 뽑기")
    void receiveCard() {
        Player player = Player.from(new Name("joy"), Money.ZERO);
        Card card = new Card(Suit.CLOVER, Rank.ACE);

        player.receiveCard(card);

        assertThat(player.getCards()).containsExactly(card);
    }

    @ParameterizedTest(name = "플레이어 카드 합계 산출")
    @MethodSource("getScoreTestcase")
    void getScore(List<Card> cards, int expectedSum) {
        Player player = new Player(new Name("joy"), new Hand(cards));

        assertThat(player.getScore()).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> getScoreTestcase() {
        return Stream.of(
                Arguments.of(createCards(Rank.TWO, Rank.THREE), 5),
                Arguments.of(createCards(Rank.ACE, Rank.THREE), 14),
                Arguments.of(createCards(Rank.ACE, Rank.KING), 21),
                Arguments.of(createCards(Rank.ACE, Rank.TWO, Rank.KING), 13),
                Arguments.of(createCards(Rank.ACE, Rank.ACE, Rank.TWO), 14)
        );
    }

    @ParameterizedTest(name = "플레이어 패가 버스트했는지 검사")
    @MethodSource("isBustTestcase")
    void isBust(List<Card> cards, boolean expected) {
        Player player = new Player(new Name("joy"), new Hand(cards));

        assertThat(player.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> isBustTestcase() {
        return Stream.of(
                Arguments.of(createCards(Rank.TWO, Rank.KING), false),
                Arguments.of(createCards(Rank.KING, Rank.KING), false),
                Arguments.of(createCards(Rank.KING, Rank.KING, Rank.TWO), true),
                Arguments.of(createCards(Rank.NINE, Rank.KING, Rank.TWO), false),
                Arguments.of(createCards(Rank.ACE, Rank.ACE, Rank.SIX), false)
        );
    }

    private static List<Card> createCards(Rank... ranks) {
        return Arrays.stream(ranks)
                .map(rank -> new Card(Suit.CLOVER, rank))
                .collect(Collectors.toList());
    }
}
