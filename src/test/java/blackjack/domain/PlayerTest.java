package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.ErrorMessage;
import blackjack.utils.HandFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PlayerTest {

    private static Stream<Arguments> canHitArgument() {
        return Stream.of(Arguments.arguments(HandFixture.createHandWithOptimisticValue15(), true),
                Arguments.arguments(HandFixture.busted(), false));
    }

    @DisplayName("플레이어는 이름과 카드를 가진다.")
    @Test
    void test1() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = Hand.of(card1, card2);
        Wallet wallet = Wallet.bet(1000);

        PlayerHand playerHand = new PlayerHand(hand, wallet);

        // when & then
        assertThatCode(() -> new Player("히로", playerHand)).doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 이름은 공백일 수 없다.")
    @ParameterizedTest
    @NullAndEmptySource
    void test2(String name) {
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = Hand.of(card1, card2);
        Wallet wallet = Wallet.bet(1000);

        PlayerHand playerHand = new PlayerHand(hand, wallet);

        assertThatThrownBy(() -> new Player(name, playerHand)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.USE_VALID_NAME.getMessage());
    }

    @DisplayName("플레이어의 모든 카드를 가져온다.")
    @Test
    void test3() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = Hand.of(card1, card2);
        Wallet wallet = Wallet.bet(1000);

        PlayerHand playerHand = new PlayerHand(hand, wallet);

        Player player = new Player("꾹이", playerHand);

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

        Hand hand = Hand.of(card1, card2);
        Wallet wallet = Wallet.bet(1000);

        PlayerHand playerHand = new PlayerHand(hand, wallet);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);
        Player player = new Player("꾹이", playerHand);

        List<Card> expect = List.of(card1, card2, newCard);

        // when
        player.takeCard(newCard);

        // then
        assertThat(player.getAllCards()).isEqualTo(expect);
    }

    @DisplayName("플레이어의 카드가 21을 넘지 않는다면 카드를 받을 수 있다.")
    @ParameterizedTest
    @MethodSource("canHitArgument")
    void test8(Hand hand, boolean expect) {
        //given
        Wallet wallet = Wallet.bet(1000);
        PlayerHand playerHand = new PlayerHand(hand, wallet);

        Player player = new Player("꾹이", playerHand);

        // when & then
        assertThat(player.canHit()).isEqualTo(expect);
    }

    @DisplayName("플레이어의 패가 블랙잭이라면 1.5배 더 받을 수 있다.")
    @Test
    void test10() {
        // given
        Hand blackjack = HandFixture.blackjack();
        Wallet wallet = Wallet.bet(1000);

        PlayerHand playerHand = new PlayerHand(blackjack, wallet);

        Player player = new Player("꾹이", playerHand);

        // when
        player.adjustBalance(GameResultType.WIN);

        // then
        assertThat(player.getRevenue()).isEqualTo(1500);

    }
}
