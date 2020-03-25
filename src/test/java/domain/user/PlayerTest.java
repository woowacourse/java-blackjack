package domain.user;

import domain.card.*;
import domain.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private List<Card> initCards;
    private Player player;

    @BeforeEach
    void setUp() {
        initCards = new ArrayList<>(Arrays.asList(new Card(Symbol.QUEEN, Type.DIAMOND), new Card(Symbol.QUEEN, Type.CLOVER)));
        PlayingCards playingCards = PlayingCards.of(initCards);
        String name = "player";
        player = Player.of(name, playingCards, Money.of(1000));
    }

    @Test
    @DisplayName("카드를 추가 지급 받는다")
    void addCard() {
        int defaultSizeOfCards = initCards.size();
        Card card = new Card(Symbol.QUEEN, Type.CLOVER);
        player.addCard(card);
        assertThat(player.countCards()).isEqualTo(defaultSizeOfCards + 1);
    }

    @Test
    @DisplayName("플레이어가 생성된다")
    void constructor() {
        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인")
    void isNotBust() {
        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 히트를 원함")
    void wantToHit() {
        assertThat(player.wantToHit("y")).isTrue();
    }

    @Test
    @DisplayName("플레이어가 스테이를 원함")
    void wantToStay() {
        assertThat(player.wantToHit("n")).isFalse();
    }

    @Test
    @DisplayName("#hit() : should add card without return")
    void hit() {
        //given
        Card card = new Card(Symbol.QUEEN, Type.SPADE);
        int defaultSizeOfCards = player.countCards();

        //when
        player.hit(card);

        //then
        assertThat(player.countCards()).isEqualTo(defaultSizeOfCards + 1);
    }

    @ParameterizedTest
    @MethodSource({"getResultsForCalculateProfit"})
    void calculateProfit(Result result, int moneyValue, int profitValue) {
        Money bettingMoney = Money.of(moneyValue);
        player = Player.of("testName", PlayingCards.of(initCards), bettingMoney);
        //when
        player.calculateProfit(result);
        int profit = player.getProfit();
        assertThat(profit).isEqualTo(profitValue);
    }

    private static Stream<Arguments> getResultsForCalculateProfit() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN_WITH_BLACKJACK, 1000, 1500),
                Arguments.of(Result.PLAYER_WIN_WITHOUT_BLACKJACk, 1000, 1000),
                Arguments.of(Result.DRAW, 1000, 0),
                Arguments.of(Result.DEALER_WIN, 1000, -1000)
        );
    }

}
