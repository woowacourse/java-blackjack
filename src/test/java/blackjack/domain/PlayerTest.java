package blackjack.domain;

import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Value.ACE;
import static blackjack.domain.card.Value.FOUR;
import static blackjack.domain.card.Value.JACK;
import static blackjack.domain.card.Value.QUEEN;
import static blackjack.domain.card.Value.THREE;
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    @Test
    @DisplayName("덱으로 부터 카드 한장을 받아올 수 있다.")
    void drawCardTest() {
        List<Card> cards = List.of(new Card(DIAMOND, TWO), new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR));
        Deck deck = new Deck(cards);

        Player player = new Player("pedro");
        player.draw(deck);

        List<Card> playerCards = player.getCards();
        assertThat(playerCards).hasSize(1);
    }

    @Test
    @DisplayName("자신의 점수를 계산할 수 있다.")
    void calculateScoreTest() {
        List<Card> cards = List.of(new Card(DIAMOND, TWO), new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR));
        Deck deck = new Deck(cards);

        Player player = new Player("pedro");
        for (int i = 0; i < cards.size(); i++) {
            player.draw(deck);
        }

        Score score = player.getScore();

        Score expected = new Score(9);
        assertThat(score).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("cardsAndBustStatus")
    @DisplayName("자신의 버스트 여부를 판단할 수 있다.")
    void checkBustTest(List<Card> cards, boolean expected) {
        Deck deck = new Deck(cards);

        Player player = new Player("pedro");
        for (int i = 0; i < cards.size(); i++) {
            player.draw(deck);
        }

        boolean isBusted = player.isBusted();

        assertThat(isBusted).isEqualTo(expected);
    }

    static Stream<Arguments> cardsAndBustStatus() {
        return Stream.of(
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)), false),
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, TWO)), true)
        );
    }
}
