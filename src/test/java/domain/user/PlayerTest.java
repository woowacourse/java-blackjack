package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.WinningResult;
import domain.deck.Card;
import domain.deck.Symbol;
import domain.deck.Type;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("이름");
    }

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThatCode(() -> new Player("이름"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("플레이어가 드로우 가능한지 확인")
    @MethodSource("createOption")
    void isAvailableToDraw(Card card, boolean expected) {
        player.draw(new Card(Symbol.CLOVER, Type.TEN));
        player.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        player.draw(card);

        assertThat(player.isAvailableToDraw()).isEqualTo(expected);
    }

    private static Stream<Arguments> createOption() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.TWO), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.THREE), false),
                Arguments.of(new Card(Symbol.DIAMOND, Type.FIVE), false)
        );
    }

    @ParameterizedTest
    @DisplayName("포인트 비교로 승자 확인")
    @MethodSource("createCardSet")
    void win(Card playerCard, Card dealerCard, WinningResult expected) {
        player.draw(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(new Card(Symbol.DIAMOND, Type.SIX));
        player.draw(playerCard);

        Dealer dealer = Dealer.appoint();
        dealer.draw(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(new Card(Symbol.CLOVER, Type.SIX));
        dealer.draw(dealerCard);

        assertThat(player.win(dealer)).isEqualTo(expected);
    }

    private static Stream<Arguments> createCardSet() {
        return Stream.of(
                //플레이어가 21점인 경우
                createCardsAndResult(Type.FIVE, Type.FIVE, WinningResult.DRAW),
                createCardsAndResult(Type.FIVE, Type.FOUR, WinningResult.WIN),
                createCardsAndResult(Type.FIVE, Type.SIX, WinningResult.WIN),

                //플레이어가 버스트인 경우
                createCardsAndResult(Type.SIX, Type.SIX, WinningResult.LOSE),
                createCardsAndResult(Type.SIX, Type.FIVE, WinningResult.LOSE),
                createCardsAndResult(Type.SIX, Type.FOUR, WinningResult.LOSE),

                //플레이어가 21미만인 경우
                createCardsAndResult(Type.THREE, Type.TWO, WinningResult.WIN),
                createCardsAndResult(Type.THREE, Type.THREE, WinningResult.DRAW),
                createCardsAndResult(Type.THREE, Type.FOUR, WinningResult.LOSE),
                createCardsAndResult(Type.THREE, Type.FIVE, WinningResult.LOSE),
                createCardsAndResult(Type.THREE, Type.SIX, WinningResult.WIN)
                );
    }

    private static Arguments createCardsAndResult(Type player, Type dealer, WinningResult winningResult) {
        return Arguments.of(
                new Card(Symbol.HEART, player),
                new Card(Symbol.SPADE, dealer),
                winningResult
        );
    }
}