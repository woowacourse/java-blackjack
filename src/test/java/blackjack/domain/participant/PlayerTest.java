package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Denomination;
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
        Card card = new Card(Suit.CLOVER, Denomination.ACE);

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
                Arguments.of(createCards(Denomination.TWO, Denomination.THREE), 5),
                Arguments.of(createCards(Denomination.ACE, Denomination.THREE), 14),
                Arguments.of(createCards(Denomination.ACE, Denomination.KING), 21),
                Arguments.of(createCards(Denomination.ACE, Denomination.TWO, Denomination.KING), 13),
                Arguments.of(createCards(Denomination.ACE, Denomination.ACE, Denomination.TWO), 14)
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
                Arguments.of(createCards(Denomination.TWO, Denomination.KING), false),
                Arguments.of(createCards(Denomination.KING, Denomination.KING), false),
                Arguments.of(createCards(Denomination.KING, Denomination.KING, Denomination.TWO), true),
                Arguments.of(createCards(Denomination.NINE, Denomination.KING, Denomination.TWO), false),
                Arguments.of(createCards(Denomination.ACE, Denomination.ACE, Denomination.SIX), false)
        );
    }

    private static List<Card> createCards(Denomination... denominations) {
        return Arrays.stream(denominations)
                .map(rank -> new Card(Suit.CLOVER, rank))
                .collect(Collectors.toList());
    }
}
