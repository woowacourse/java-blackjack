package blackjack.domain.gambler;

import static blackjack.domain.card.CardShape.CLOVER;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardType.ACE;
import static blackjack.domain.card.CardType.EIGHT;
import static blackjack.domain.card.CardType.TEN;
import static blackjack.domain.fixture.CardFixture.createCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.fixture.GamblerFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {
    @DisplayName("이름을 통해 플레이어를 생성한다.")
    @Test
    void createTest() {
        Player player = new Player(new Name("라젤"));

        assertThat(player.getName()).isEqualTo(new Name("라젤"));
    }

    @DisplayName("패에 카드를 추가한다")
    @Test
    void addCardTest() {
        Player player = new Player(new Name("라젤"));
        Card card1 = new Card(CLOVER, TEN);
        Card card2 = new Card(HEART, CardType.EIGHT);

        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.calculateScore()).isEqualTo(18);
    }

    /**
     * TODO
     * 테스트 케이스 늘리기
     */
    @DisplayName("패에 카드가 2개이고, 합이 21인지 여부를 반환한다")
    @Test
    void isBlackjack() {
        // given
        Player player = new Player(new Name("레오"));
        for (Card card : createCards(TEN, ACE)) {
            player.addCard(card);
        }

        // when
        boolean result = player.isBlackjack();

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("카드의 합이 특정 값 이하인지 확인한지 테스트")
    @CsvSource(value = {"21:True", "16:False"}, delimiterString = ":")
    @ParameterizedTest
    void isBelowTest(int criteria, boolean expected) {
        // given
        Player player = new Player(new Name("라젤"));
        Card card1 = new Card(CLOVER, TEN);
        Card card2 = new Card(HEART, CardType.EIGHT);

        player.addCard(card1);
        player.addCard(card2);

        // when
        boolean result = player.isScoreBelow(criteria);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주어진_Gambler_와의_점수_차이를_반환한다")
    @CsvSource(value = {"TEN:TEN:2", "TEN:EIGHT:0", "TEN:SIX:-2"}, delimiterString = ":")
    @ParameterizedTest
    void calculateScoreDifference(CardType firstType, CardType secondType, int expected) {
        // given
        Dealer dealer = GamblerFixture.createDealerWithCards(TEN, EIGHT);
        Player player = GamblerFixture.createPlayerWithCards(new Name("레오"), firstType, secondType);

        // when
        int result = player.calculateScoreDifference(dealer);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
