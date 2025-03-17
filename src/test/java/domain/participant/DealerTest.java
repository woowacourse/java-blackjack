package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardHand;
import domain.game.GamblingMoney;
import domain.game.Winning;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
class DealerTest {

    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("이름", new CardHand(), GamblingMoney.bet(10000));
        dealer = new Dealer(new CardHand());
    }

    @Test
    void 딜러는_카드를_받을_수_있다() {
        //given
        //when
        dealer.takeCards(Card.SPADE_2, Card.HEART_3);
        //then
        assertThat(dealer.getCards()).contains(Card.SPADE_2, Card.HEART_3);
    }

    @Test
    void 딜러는_오픈카드는_가장_처음_받은_카드이다() {
        //given
        dealer.takeCards(Card.SPADE_2, Card.HEART_3);

        //when
        var card = dealer.getOpenCard();

        //then
        assertThat(card).isEqualTo(Card.SPADE_2);
    }

    @Test
    void 딜러의_카드의_점수_합을_구할_수_있다() {
        //given
        dealer.takeCards(Card.SPADE_2, Card.HEART_3);

        //when
        int score = dealer.calculateScore();

        //then
        assertThat(score).isEqualTo(5);
    }

    @Test
    void 딜러가_블랙잭인지_알_수_있다() {
        //given
        dealer.takeCards(Card.SPADE_A, Card.HEART_10);

        //when
        boolean blackJack = dealer.isBlackJack();

        //then
        assertThat(blackJack).isTrue();
    }

    @Test
    void 점수가_21점을_넘으면_버스트이다() {
        dealer.takeCards(Card.SPADE_10, Card.HEART_10, Card.SPADE_2);
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    void 딜러의_점수가_16점_이하일_때_카드를_더_받을수있다() {
        //given
        dealer.takeCards(Card.SPADE_J, Card.SPADE_6);

        //when
        boolean canTakeMore = dealer.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isTrue();
    }

    @Test
    void 딜러의_점수가_17점_이상이면_카드를_더_받을수없다() {
        //given
        dealer.takeCards(Card.SPADE_J, Card.SPADE_7);

        //when
        boolean canTakeMore = dealer.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isFalse();
    }

    @ParameterizedTest
    @MethodSource("providePlayerCards")
    void 딜러에게_비교할_플레이어를_주고_플레이어의_승무패를_알_수_있다(List<Card> playerCards, Winning expectedPlayerWinning) {
        //given
        dealer.takeCards(Card.SPADE_10, Card.DIAMOND_10);
        player.takeCards(playerCards.toArray(Card[]::new));

        //when
        Winning winning = dealer.judgeWinningForPlayer(player);

        //then
        assertThat(winning).isEqualTo(expectedPlayerWinning);
    }

    public static Stream<Arguments> providePlayerCards() {
        return Stream.of(
            Arguments.of(List.of(Card.HEART_10, Card.HEART_9), Winning.LOSE),
            Arguments.of(List.of(Card.HEART_10, Card.DIAMOND_10), Winning.DRAW),
            Arguments.of(List.of(Card.HEART_10, Card.DIAMOND_A), Winning.WIN)
        );
    }
}
