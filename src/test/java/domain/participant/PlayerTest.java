package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.Symbol;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ExceptionMessages;

class PlayerTest {

    private Player player;
    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void init() {
        player = Player.of("test", 3000);
        dealer = new Dealer();
        deck = Deck.initDeck(Card.values());
    }


    @Test
    @DisplayName("이름이 공백인 경우 예외를 발생한다.")
    void playerEmptyNameTest() {
        String name = "";

        assertThatThrownBy(() -> Player.of(name, 2000 ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("21이 넘은 상태에서 카드를 뽑을 경우 에러를 발생시킨다.")
    void hitCardOverLimitError() {
        Deck testDeck = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.SIX),
            Card.valueOf(Symbol.HEART, Denomination.SIX),
            Card.valueOf(Symbol.DIAMOND, Denomination.SIX),
            Card.valueOf(Symbol.SPADE, Denomination.SIX),
            Card.valueOf(Symbol.HEART, Denomination.FOUR)));

        for (int i = 0; i < 4; i++) {
            player.hit(testDeck);
        }

        assertThatThrownBy(() -> player.hit(deck))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(ExceptionMessages.PLAYER_CAN_NOT_HIT_ERROR);
    }

    @Test
    @DisplayName("블랙잭이거나 숫자가 21이 넘는 경우 False를 반환한다.")
    void canDrawCardFalseTest() {
        while (player.canHit()) {
            player.hit(deck);
        }

        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("숫자가 21이 넘지 않는 경우 True를 반환한다.")
    void canDrawCardTrueTest() {
        player.hit(deck);

        assertThat(player.canHit()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideDeckAndResult")
    @DisplayName("Player만 블랙잭인 경우 BlackJack을 반환하고, 딜러만 블랙잭인 경우 Lose를 반환한다")
    void judgeResultWhenBlackJack(List<Card> playerCardPack, List<Card> dealerCardPack,
        Result result) {
        Deck deck1 = Deck.initDeck(playerCardPack);
        Deck deck2 = Deck.initDeck(dealerCardPack);

        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);

        assertThat(player.receiveResult(dealer)).isEqualTo(result);
    }

    private static Stream<Arguments> provideDeckAndResult() {
        return Stream.of(
            Arguments.of(List.of(Card.valueOf(Symbol.CLOVER, Denomination.ACE),
                    Card.valueOf(Symbol.HEART, Denomination.QUEEN)),
                List.of(Card.valueOf(Symbol.CLOVER, Denomination.QUEEN),
                    Card.valueOf(Symbol.HEART, Denomination.THREE)), Result.BLACKJACK),
            Arguments.of(List.of(Card.valueOf(Symbol.CLOVER, Denomination.QUEEN),
                    Card.valueOf(Symbol.HEART, Denomination.THREE)),
                List.of(Card.valueOf(Symbol.CLOVER, Denomination.ACE),
                    Card.valueOf(Symbol.HEART, Denomination.QUEEN)), Result.LOSE)
        );
    }


    @Test
    @DisplayName("Player가 BlackJack인 경우, 자신의 베팅 금액의 1.5배를 반환한다")
    void caclulateIncomeWhenBlackJack() {
        int actual = player.calculateIncome(Result.BLACKJACK);
        int expected = 4500;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Player가 Lose인 경우, 자신의 베팅 금액의 -1배를 반환한다.")
    void calculateIncomeWhenLose() {
        int actual = player.calculateIncome(Result.LOSE);
        int expected = -3000;
        assertThat(actual).isEqualTo(expected);
    }
}