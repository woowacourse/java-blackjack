package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.result.BlackJackResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("엘리");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("플레이어는 이름을 입력받아 생성")
    void player() {
        assertThat(player.getName()).isEqualTo("엘리");
    }

    @ParameterizedTest
    @MethodSource("createCards")
    @DisplayName("bust되었는지 확인")
    void isBusted(List<Card> cards, boolean isBusted) {
        for (Card card : cards) {
            player.draw(card);
        }

        assertThat(player.isBusted()).isEqualTo(isBusted);
    }

    @ParameterizedTest
    @MethodSource("matchCards")
    @DisplayName("player에게 dealer를 가져와 승패 반환")
    void playerBusted(List<Card> playerCards, List<Card> dealerCards, BlackJackResult result) {
        for (Card card : playerCards) {
            player.draw(card);
        }
        for (Card card : dealerCards) {
            dealer.draw(card);
        }
        assertThat(player.match(dealer)).isEqualTo(result);
    }

    private static Stream<Arguments> createCards() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card queenClover = new Card(CardSymbol.QUEEN, CardType.CLOVER);
        Card kingClover = new Card(CardSymbol.KING, CardType.CLOVER);
        List<Card> notBustedCards = Arrays.asList(aceSpade, queenClover, kingClover);
        List<Card> bustedCards = Arrays.asList(kingClover, kingClover, kingClover);
        return Stream.of(
                Arguments.of(notBustedCards, false),
                Arguments.of(bustedCards, true));
    }

    private static Stream<Arguments> matchCards() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card fiveHeart = new Card(CardSymbol.FIVE, CardType.HEART);
        Card kingClover = new Card(CardSymbol.KING, CardType.CLOVER);
        List<Card> bustedCards = Arrays.asList(kingClover, kingClover, kingClover);
        List<Card> blackJackCards = Arrays.asList(aceSpade, kingClover);
        List<Card> normalCards = Arrays.asList(aceSpade, fiveHeart);
        return Stream.of(
                Arguments.of(bustedCards, bustedCards, BlackJackResult.LOSE),
                Arguments.of(normalCards, bustedCards, BlackJackResult.WIN),
                Arguments.of(blackJackCards, normalCards, BlackJackResult.WIN),
                Arguments.of(normalCards, normalCards, BlackJackResult.DRAW),
                Arguments.of(normalCards, blackJackCards, BlackJackResult.LOSE));
    }

}