package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.CardRank;
import blackjack.common.CardSuit;
import blackjack.common.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PlayerTest {

    @DisplayName("플레이어는 이름과 카드를 가진다.")
    @Test
    void test1() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        // when & then
        assertThatCode(() -> new Player("히로", hand))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 이름은 공백일 수 없다.")
    @ParameterizedTest
    @NullAndEmptySource
    void test2(String name) {
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        assertThatThrownBy(() -> new Player(name, hand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.USE_VALID_NAME.getMessage());
    }

    @DisplayName("플레이어의 모든 카드를 가져온다.")
    @Test
    void test3() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();

        hand.takeCard(card1);
        hand.takeCard(card2);

        Player player = new Player("꾹이", hand);

        List<Card> expect = List.of(card1, card2);

        // when & then
        assertThat(player.getAllCards()).isEqualTo(expect);
    }

    @DisplayName("플레이어는 카드를 가져올 수 있다.")
    @Test
    void test4() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();

        hand.takeCard(card1);
        hand.takeCard(card2);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);
        Player player = new Player("꾹이", hand);

        List<Card> expect = List.of(card1, card2, newCard);

        // when
        player.takeCard(newCard);

        // then
        assertThat(player.getAllCards()).isEqualTo(expect);
    }
}
