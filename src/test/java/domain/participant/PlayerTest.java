package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardHand;
import domain.game.GamblingMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class PlayerTest {

    final GamblingMoney betMoney = GamblingMoney.bet(10000);

    @ParameterizedTest
    @ValueSource(strings = {"a", "abcdeabcde"})
    void 한자리에서_열자리사이의_닉네임과_배팅금으로_된_플레이어를_만들수_있다(String name) {
        //given
        //when
        //then
        assertThatCode(
            () -> new Player(name, new CardHand(), betMoney)
        ).doesNotThrowAnyException();
    }

    @Test
    void 플레이어의_닉네임이_11자_이상이면_예외발생() {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Player("asdfghjjklz", new CardHand(), betMoney))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ",})
    @NullSource
    @EmptySource
    void 플레이어의_닉네임이_공백이면_예외발생(String name) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Player(name, new CardHand(), betMoney))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("player", new CardHand(), betMoney);
    }

    @Test
    void 플레이어는_카드를_받을_수_있다() {
        //given
        //when
        player.takeCards(Card.SPADE_2, Card.HEART_3);
        //then
        assertThat(player.getCards()).contains(Card.SPADE_2, Card.HEART_3);
    }

    @Test
    void 플레이어의_카드의_점수_합을_구할_수_있다() {
        //given
        player.takeCards(Card.SPADE_2, Card.HEART_3);

        //when
        int score = player.calculateScore();

        assertThat(score).isEqualTo(5);
    }

    @Test
    void 플레이어가_블랙잭인지_알_수_있다() {
        //given
        player.takeCards(Card.SPADE_A, Card.HEART_10);

        //when
        boolean blackJack = player.isBlackJack();

        //then
        assertThat(blackJack).isTrue();
    }

    @Test
    void 점수가_21점을_넘으면_버스트이다() {
        player.takeCards(Card.SPADE_10, Card.HEART_10, Card.SPADE_2);
        assertThat(player.isBust()).isTrue();
    }

    @Test
    void 플레이어의_점수가_21점_미만일_때_카드를_더_받을수있다() {
        //given
        player.takeCards(Card.SPADE_J, Card.SPADE_K);

        //when
        boolean canTakeMore = player.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isTrue();
    }

    @Test
    void 플레이어의_점수가_21점_이상이면_카드를_더_받을수없다() {
        //given
        player.takeCards(Card.SPADE_J, Card.SPADE_A);

        //when
        boolean canTakeMore = player.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isFalse();
    }

    @ParameterizedTest
    @CsvSource({
        "SPADE_10,SPADE_10,10000",
        "SPADE_10,DIAMOND_9,0",
        "SPADE_10,SPADE_8,-10000"
    })
    void 딜러와_비교하여_플레이어의_수익금을_계산한다(Card playerCard1, Card playerCard2, int expectedProfit) {
        final var player = new Player("player", new CardHand(), GamblingMoney.bet(10000));
        final var dealer = new Dealer(new CardHand());

        dealer.takeCards(Card.CLOVER_10, Card.CLOVER_9);
        player.takeCards(playerCard1, playerCard2);

        final var profit = player.calculateProfit(dealer);

        assertThat(profit).isEqualTo(new GamblingMoney(expectedProfit));
    }

    @DisplayName("플레이어만 블랙잭이면 수익금은 1.5배이다")
    @Test
    void onceHalfProfitWhenOnlyPlayerBlackjack() {
        //given
        player.takeCards(Card.SPADE_J, Card.SPADE_A);
        final var dealer = new Dealer(new CardHand());
        dealer.takeCards(Card.HEART_10, Card.SPADE_10);

        //when
        final var profit = player.calculateProfit(dealer);

        //then
        assertThat(profit).isEqualTo(new GamblingMoney(+15000));
    }
}
