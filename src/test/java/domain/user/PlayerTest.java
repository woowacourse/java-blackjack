package domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.ResultType;

class PlayerTest {

    private Player player;
    private Dealer dealer;

    @Mock
    private Deck deck;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dealer = Dealer.appoint();
        player = new Player("이름", 1000);
    }

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThatCode(() -> new Player("이름", 1000))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("플레이어가 드로우 가능한지 확인")
    @MethodSource("createOption")
    void isAvailableToDraw(Card card, boolean expected) {
        MockitoAnnotations.initMocks(this);
        given(deck.dealOut()).willReturn(new Card(Symbol.CLOVER, Type.TEN));
        player.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.CLOVER, Type.EIGHT));
        player.draw(deck);
        given(deck.dealOut()).willReturn(card);
        player.draw(deck);

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
    void reflect(Card playerCard, Card dealerCard, ResultType expected) {
        Queue<Card> cards = new LinkedList<>(Arrays.asList(
                new Card(Symbol.DIAMOND, Type.KING),
                new Card(Symbol.DIAMOND, Type.SIX),
                new Card(Symbol.CLOVER, Type.KING),
                new Card(Symbol.CLOVER, Type.SIX))
        );
        given(deck.dealOut()).will(invocation -> cards.poll());
        player.draw(deck);
        player.draw(deck);
        dealer.draw(deck);
        dealer.draw(deck);

        given(deck.dealOut()).willReturn(playerCard);
        player.draw(deck);
        given(deck.dealOut()).willReturn(dealerCard);
        dealer.draw(deck);

        assertThat(player.decideResultType(dealer)).isEqualTo(expected);
    }

    private static Stream<Arguments> createCardSet() {
        return Stream.of(
                //플레이어가 21점인 경우
                createCardsAndWinningResult(Type.FIVE, Type.FIVE, ResultType.DRAW),
                createCardsAndWinningResult(Type.FIVE, Type.FOUR, ResultType.WIN),
                createCardsAndWinningResult(Type.FIVE, Type.SIX, ResultType.WIN),

                //플레이어가 버스트인 경우
                createCardsAndWinningResult(Type.SIX, Type.SIX, ResultType.LOSE),
                createCardsAndWinningResult(Type.SIX, Type.FIVE, ResultType.LOSE),
                createCardsAndWinningResult(Type.SIX, Type.FOUR, ResultType.LOSE),

                //플레이어가 21미만인 경우
                createCardsAndWinningResult(Type.THREE, Type.TWO, ResultType.WIN),
                createCardsAndWinningResult(Type.THREE, Type.THREE, ResultType.DRAW),
                createCardsAndWinningResult(Type.THREE, Type.FOUR, ResultType.LOSE),
                createCardsAndWinningResult(Type.THREE, Type.FIVE, ResultType.LOSE),
                createCardsAndWinningResult(Type.THREE, Type.SIX, ResultType.WIN)
        );
    }

    private static Arguments createCardsAndWinningResult(Type player, Type dealer, ResultType resultType) {
        return Arguments.of(
                new Card(Symbol.HEART, player),
                new Card(Symbol.SPADE, dealer),
                resultType
        );
    }

    @Test
    @DisplayName("점수가 같고 모두 블랙잭인 경우")
    void drawIfBothBlackJack() {
        given(deck.dealOut()).willReturn(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.HEART, Type.ACE));
        player.draw(deck);

        given(deck.dealOut()).willReturn(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.SPADE, Type.ACE));
        dealer.draw(deck);

        assertThat(player.decideResultType(dealer)).isEqualTo(ResultType.DRAW);
    }

    @Test
    @DisplayName("점수가 같고 플레이어만 블랙잭인 경우")
    void drawIfPlayerBlackJack() {
        given(deck.dealOut()).willReturn(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.HEART, Type.ACE));
        player.draw(deck);

        given(deck.dealOut()).willReturn(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.SPADE, Type.SIX));
        dealer.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.SPADE, Type.FIVE));
        dealer.draw(deck);

        assertThat(player.decideResultType(dealer)).isEqualTo(ResultType.WIN);
    }

    @Test
    @DisplayName("점수가 같고 딜러만 블랙잭인 경우")
    void drawIfDealerBlackJack() {
        given(deck.dealOut()).willReturn(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.HEART, Type.SIX));
        player.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.CLOVER, Type.FIVE));
        player.draw(deck);

        given(deck.dealOut()).willReturn(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(deck);
        given(deck.dealOut()).willReturn(new Card(Symbol.SPADE, Type.ACE));
        dealer.draw(deck);

        assertThat(player.decideResultType(dealer)).isEqualTo(ResultType.LOSE);
    }
}