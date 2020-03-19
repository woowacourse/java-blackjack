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
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PlayerTest {

    private List<Card> initCards;
    private Player player;

    @BeforeEach
    void setUp() {
        initCards = new ArrayList<>(Arrays.asList(new Card(Symbol.QUEEN, Type.DIAMOND), new Card(Symbol.QUEEN, Type.CLOVER)));
        PlayingCards playingCards = PlayingCards.of(initCards);
        String name = "player";
        player = new Player(playingCards, name, mock(Money.class));
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

    @Test
    @DisplayName("#confirmCards : should add cards for given cards without return")
    void confirmCardsWithValidCards() {
        //given
        List<Card> cardsToAdd = Collections.singletonList(new Card(Symbol.QUEEN, Type.DIAMOND));
        Cards cards = Cards.of(cardsToAdd);
        int defaultSizeOfCards = player.countCards();
        List<Card> expectedCards = setupExpectedCards(cardsToAdd);

        //when
        player.confirmCards(cards);


        //then
        assertThat(player.getCards()).isEqualTo(PlayingCards.of(expectedCards));
        assertThat(player.countCards()).isEqualTo(defaultSizeOfCards + cards.size());
    }

    private List<Card> setupExpectedCards(List<Card> cardsToAdd) {
        List<Card> expectedCards = new ArrayList<>();
        expectedCards.addAll(initCards);
        expectedCards.addAll(cardsToAdd);
        return expectedCards;
    }

    @ParameterizedTest
    @MethodSource({"getResultsForCalculateProfit"})
    void calculateProfit(Result result) {
        Money bettingMoney = mock(Money.class);
        //todo: refac multiply mocking logic
        when(bettingMoney.multiply(anyDouble())).thenReturn(bettingMoney);
        player = new Player(mock(PlayingCards.class), "testName", bettingMoney);
        //when
        Money profit = player.calculateProfit(result);
        assertThat(profit).isEqualTo(bettingMoney);
        verifyCalculateProfit(result, bettingMoney);
    }

    private void verifyCalculateProfit(Result result, Money bettingMoney) {
        if (result.equals(Result.DEALER_WIN)) {
            verify(bettingMoney, times(2)).multiply(anyDouble());
        } else {
            verify(bettingMoney, times(1)).multiply(anyDouble());
        }
    }
    private static Stream<Arguments> getResultsForCalculateProfit() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN_WITH_BLACKJACK),
                Arguments.of(Result.PLAYER_WIN_WITHOUT_BLACKJACk),
                Arguments.of(Result.DRAW),
                Arguments.of(Result.DEALER_WIN)
        );
    }

}
