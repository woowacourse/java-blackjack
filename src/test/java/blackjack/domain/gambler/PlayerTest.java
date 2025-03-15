package blackjack.domain.gambler;

import static blackjack.domain.card.CardType.ACE;
import static blackjack.domain.card.CardType.EIGHT;
import static blackjack.domain.card.CardType.TEN;
import static blackjack.domain.fixture.GamblerFixture.createDealerWithCards;
import static blackjack.domain.fixture.GamblerFixture.createPlayerWithCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {
    private final Name name = new Name("라젤");

    @DisplayName("플레이어의_패에_카드를_추가한다")
    @Test
    void addCardTest() {
        // given
        Player player = createPlayerWithCards(name, TEN, EIGHT);

        // when
        int result = player.calculateScore();

        // then
        assertThat(result).isEqualTo(18);
    }

    @DisplayName("패에_카드가_2개이고_합이_21인지_여부를_반환한다")
    @CsvSource(value = {"TEN:TEN:2", "TEN:EIGHT:0", "TEN:SIX:-2"}, delimiterString = ":")
    @ParameterizedTest
    void isBlackjack() {
        // given
        Player player = createPlayerWithCards(name, TEN, ACE);

        // when
        boolean result = player.isBlackjack();

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("카드의_합이_특정 값 이하인지 확인한지 테스트")
    @CsvSource(value = {"21:True", "16:False"}, delimiterString = ":")
    @ParameterizedTest
    void isBelowTest(int criteria, boolean expected) {
        // given
        Player player = createPlayerWithCards(name, TEN, EIGHT);

        // when
        boolean result = player.isScoreBelow(criteria);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("처음_카드를_받은_후_플레이어는_두개의_카드를_오픈한다")
    @Test
    void getInitialCardsTest() {
        // given
        Player player = new Player(name);
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);
        player.addCard(card1);
        player.addCard(card2);

        // when
        List<Card> result = player.getInitialCards();

        // then
        assertAll(
                () -> assertThat(result).hasSize(2),
                () -> assertThat(result).contains(card1, card2)
        );
    }
}
