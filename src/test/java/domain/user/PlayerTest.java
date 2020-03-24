package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.PrizeRatio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PlayerTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.appoint();
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
        MockitoAnnotations.initMocks(this);
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
    void reflect(Card playerCard, Card dealerCard, PrizeRatio expected) {
        player.draw(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(new Card(Symbol.DIAMOND, Type.SIX));
        dealer.draw(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(new Card(Symbol.CLOVER, Type.SIX));

        player.draw(playerCard);
        dealer.draw(dealerCard);

        assertThat(player.decideRatio(dealer)).isEqualTo(expected);
    }

    private static Stream<Arguments> createCardSet() {
        return Stream.of(
                //플레이어가 21점인 경우
                createCardsAndWinningResult(Type.FIVE, Type.FIVE, PrizeRatio.DRAW),
                createCardsAndWinningResult(Type.FIVE, Type.FOUR, PrizeRatio.WIN),
                createCardsAndWinningResult(Type.FIVE, Type.SIX, PrizeRatio.WIN),

                //플레이어가 버스트인 경우
                createCardsAndWinningResult(Type.SIX, Type.SIX, PrizeRatio.LOSE),
                createCardsAndWinningResult(Type.SIX, Type.FIVE, PrizeRatio.LOSE),
                createCardsAndWinningResult(Type.SIX, Type.FOUR, PrizeRatio.LOSE),

                //플레이어가 21미만인 경우
                createCardsAndWinningResult(Type.THREE, Type.TWO, PrizeRatio.WIN),
                createCardsAndWinningResult(Type.THREE, Type.THREE, PrizeRatio.DRAW),
                createCardsAndWinningResult(Type.THREE, Type.FOUR, PrizeRatio.LOSE),
                createCardsAndWinningResult(Type.THREE, Type.FIVE, PrizeRatio.LOSE),
                createCardsAndWinningResult(Type.THREE, Type.SIX, PrizeRatio.WIN)
        );
    }

    private static Arguments createCardsAndWinningResult(Type player, Type dealer, PrizeRatio prizeRatio) {
        return Arguments.of(
                new Card(Symbol.HEART, player),
                new Card(Symbol.SPADE, dealer),
                prizeRatio
        );
    }

    @Test
    @DisplayName("점수가 같고 모두 블랙잭인 경우")
    void drawIfBothBlackJack() {
        player.draw(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(new Card(Symbol.HEART, Type.ACE));

        dealer.draw(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(new Card(Symbol.SPADE, Type.ACE));

        assertThat(player.decideRatio(dealer)).isEqualTo(PrizeRatio.DRAW);
    }

    @Test
    @DisplayName("점수가 같고 플레이어만 블랙잭인 경우")
    void drawIfPlayerBlackJack() {
        player.draw(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(new Card(Symbol.HEART, Type.ACE));

        dealer.draw(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(new Card(Symbol.SPADE, Type.SIX));
        dealer.draw(new Card(Symbol.SPADE, Type.FIVE));

        assertThat(player.decideRatio(dealer)).isEqualTo(PrizeRatio.BLACKJACK);
    }

    @Test
    @DisplayName("점수가 같고 딜러만 블랙잭인 경우")
    void drawIfDealerBlackJack() {
        player.draw(new Card(Symbol.DIAMOND, Type.KING));
        player.draw(new Card(Symbol.HEART, Type.SIX));
        player.draw(new Card(Symbol.CLOVER, Type.FIVE));

        dealer.draw(new Card(Symbol.CLOVER, Type.KING));
        dealer.draw(new Card(Symbol.SPADE, Type.ACE));

        assertThat(player.decideRatio(dealer)).isEqualTo(PrizeRatio.LOSE);
    }
}