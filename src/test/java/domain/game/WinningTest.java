package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardHand;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class WinningTest {

    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new CardHand());
        player = new Player("이름", new CardHand(), GamblingMoney.bet(10000));
    }

    @Test
    void 딜러가_블랙잭이고_플레이어가_블랙잭이면_무승부이다() {
        dealer.takeCards(Card.DIAMOND_J, Card.DIAMOND_A);
        player.takeCards(Card.SPADE_J, Card.SPADE_A);

        Winning result = Winning.determineForPlayer(player, dealer);

        assertThat(result).isEqualTo(Winning.DRAW);
    }

    @Test
    void 딜러가_블랙잭이고_플레이어가_블랙잭이_아니면_플레이어는_패배한다() {
        dealer.takeCards(Card.DIAMOND_J, Card.DIAMOND_A);
        player.takeCards(Card.SPADE_J, Card.SPADE_10);

        Winning result = Winning.determineForPlayer(player, dealer);

        assertThat(result).isEqualTo(Winning.LOSE);
    }

    @Test
    void 딜러가_블랙잭이_아니고_플레이어가_블랙잭이면_플레이어는_승리한다() {
        dealer.takeCards(Card.DIAMOND_10, Card.DIAMOND_J);
        player.takeCards(Card.SPADE_A, Card.SPADE_J);

        Winning result = Winning.determineForPlayer(player, dealer);

        assertThat(result).isEqualTo(Winning.WIN);
    }

    @Test
    void 플레이어가_버스트이면_넘으면_딜러의_패와_상관없이_패배한다() {
        dealer.takeCards(Card.SPADE_3, Card.SPADE_4);
        player.takeCards(Card.SPADE_J, Card.SPADE_K, Card.SPADE_2);

        Winning result = Winning.determineForPlayer(player, dealer);

        assertThat(result).isEqualTo(Winning.LOSE);
    }

    @Test
    void 딜러가_버스트이고_플레이어가_버스트가_아니면_플레이어가_승리한다() {
        dealer.takeCards(Card.DIAMOND_10, Card.HEART_10, Card.HEART_2);
        player.takeCards(Card.SPADE_2, Card.SPADE_3);

        Winning result = Winning.determineForPlayer(player, dealer);
        assertThat(result).isEqualTo(Winning.WIN);
    }

    @Test
    void 둘다_버스트가_아니고_블랙잭이_아니면_점수가_높은쪽이_승리한다_플레이어_승() {
        dealer.takeCards(Card.SPADE_10, Card.SPADE_9);
        player.takeCards(Card.DIAMOND_10, Card.HEART_10);

        Winning result = Winning.determineForPlayer(player, dealer);

        assertThat(result).isEqualTo(Winning.WIN);
    }

    @Test
    void 둘다_버스트가_아니고_블랙잭이_아니면_점수가_높은쪽이_승리한다_무승부() {
        dealer.takeCards(Card.SPADE_10, Card.SPADE_J);
        player.takeCards(Card.DIAMOND_10, Card.HEART_10);

        Winning result = Winning.determineForPlayer(player, dealer);

        assertThat(result).isEqualTo(Winning.DRAW);
    }

    @Test
    void 둘다_버스트가_아니고_블랙잭이_아니면_점수가_높은쪽이_승리한다_플레이어_패() {
        dealer.takeCards(Card.SPADE_10, Card.SPADE_J);
        player.takeCards(Card.DIAMOND_10, Card.HEART_9);

        Winning result = Winning.determineForPlayer(player, dealer);

        assertThat(result).isEqualTo(Winning.LOSE);
    }
}
