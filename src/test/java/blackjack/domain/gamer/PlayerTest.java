package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
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

    @BeforeEach
    void setUp() {
        player = new Player("엘리");
    }

    @Test
    @DisplayName("플레이어는 이름을 입력받아 생성")
    void player() {
        assertThat(player.getName()).isEqualTo("엘리");
    }

    @Test
    @DisplayName("카드를 더 추가할 수 있는지 확인")
    void canDrawCard() {
        player.draw(new Card(CardSymbol.KING, CardType.SPADE));
        player.draw(new Card(CardSymbol.KING, CardType.SPADE));
        assertThat(player.canDrawCard()).isTrue();
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
}