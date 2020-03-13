package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.WinningResult;

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
                createCardsAndWinningResult(Type.FIVE, Type.FIVE, WinningResult.DRAW),
                createCardsAndWinningResult(Type.FIVE, Type.FOUR, WinningResult.WIN),
                createCardsAndWinningResult(Type.FIVE, Type.SIX, WinningResult.WIN),

                //플레이어가 버스트인 경우
                createCardsAndWinningResult(Type.SIX, Type.SIX, WinningResult.LOSE),
                createCardsAndWinningResult(Type.SIX, Type.FIVE, WinningResult.LOSE),
                createCardsAndWinningResult(Type.SIX, Type.FOUR, WinningResult.LOSE),

                //플레이어가 21미만인 경우
                createCardsAndWinningResult(Type.THREE, Type.TWO, WinningResult.WIN),
                createCardsAndWinningResult(Type.THREE, Type.THREE, WinningResult.DRAW),
                createCardsAndWinningResult(Type.THREE, Type.FOUR, WinningResult.LOSE),
                createCardsAndWinningResult(Type.THREE, Type.FIVE, WinningResult.LOSE),
                createCardsAndWinningResult(Type.THREE, Type.SIX, WinningResult.WIN)
        );
    }

    @ParameterizedTest
    @DisplayName("블랙잭일 경우 승자 확인")
    @MethodSource("createOtherCardSet")
    void winIfBlackJack(Card playerCard, Card dealerCard, WinningResult expected) {
        player.draw(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(playerCard);

        Dealer dealer = Dealer.appoint();
        dealer.draw(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(dealerCard);

        assertThat(player.win(dealer)).isEqualTo(expected);
    }

    private static Stream<Arguments> createOtherCardSet() {
        return Stream.of(
                createCardsAndWinningResult(Type.ACE, Type.ACE, WinningResult.DRAW),
                createCardsAndWinningResult(Type.ACE, Type.FIVE, WinningResult.WIN),
                createCardsAndWinningResult(Type.THREE, Type.ACE, WinningResult.LOSE)
        );
    }

    @ParameterizedTest
    @DisplayName("승패 결과")
    @MethodSource("createCardsAndResult")
    void getWinningResult(Card card, String expected) {
        player.draw(new Card(Symbol.DIAMOND, Type.SIX));
        player.draw(card);

        Dealer dealer = Dealer.appoint();
        dealer.draw(new Card(Symbol.CLOVER, Type.KING));

        player.win(dealer);

        assertThat(player.getTotalWinningResult()).isEqualTo(expected);
    }

    private static Stream<Arguments> createCardsAndResult() {
        return Stream.of(
                Arguments.of(new Card(Symbol.HEART, Type.FIVE), "이름: 승"),
                Arguments.of(new Card(Symbol.HEART, Type.TWO), "이름: 패"),
                Arguments.of(new Card(Symbol.HEART, Type.FOUR), "이름: 무승부")
        );
    }

    private static Arguments createCardsAndWinningResult(Type player, Type dealer, WinningResult winningResult) {
        return Arguments.of(
                new Card(Symbol.HEART, player),
                new Card(Symbol.SPADE, dealer),
                winningResult
        );
    }
}