package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

//    @Test
//    @DisplayName("카드의 합을 계산해 반환한다")
//    void calculate_calculation_check_rot() {
//        // given
//        Player player = new Gambler("두리", 0);
//        player.addCards(new CardPack(new SortedBlackjackShuffle()), 3);
//
//        // when
//        int result = player.getCardScore();
//
//        // then
//        assertThat(result).isEqualTo(30);
//    }
//
//    @DisplayName("에이스가 2장이면 12로 계산한다")
//    @Test
//    void ace_test() {
//        Player player = new Gambler("두리", 0);
//        player.addCards(new CardPack(new ReversedBlackjackShuffle()), 2);
//
//        // when
//        int result = player.getCardScore();
//
//        // then
//        assertThat(result).isEqualTo(12);
//    }

    @ParameterizedTest
    @MethodSource("blackJackHandTestCases")
    @DisplayName("플레이어의 블랙잭을 판단해 결과를 반환한다")
    void 플레이어가_블랙잭인지_판단해_반환한다(List<Card> cards, boolean excepted) {
        Player player = new TestPlayer();
        player.addCards(cards);

        boolean result = player.isBlackJack();

        assertThat(result).isEqualTo(excepted);
    }

    static class TestPlayer extends Player {

        public TestPlayer() {
            super("Test", 0);
        }

        @Override
        public List<Card> getOpenedCards() {
            return this.getCards().getCards();
        }
    }

    private static Stream<Arguments> blackJackHandTestCases() {
        Card ace = new Card(CardNumber.ACE, CardShape.CLOVER);
        Card five = new Card(CardNumber.FIVE, CardShape.CLOVER);
        Card ten = new Card(CardNumber.TEN, CardShape.CLOVER);

        return Stream.of(
                Arguments.of(List.of(ace), false),
                Arguments.of(List.of(ace, five), false),
                Arguments.of(List.of(ace, five, five), false),
                Arguments.of(List.of(ace, ten), true)
        );
    }
}
