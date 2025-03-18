package blackjack.model.participants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.bettings.Wager;
import blackjack.model.cards.Card;
import blackjack.model.cards.Hand;
import blackjack.model.cards.Rank;
import blackjack.model.cards.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        Dealer dealer = new Dealer();
        hand = dealer.produceHand();
    }

    @DisplayName("플레이어는 2장 묶음의 카드 핸드를 받아 초기화할 수 있다")
    @Test
    void testPlayerHand_Create() {
        Player player = new Player(new Name("player"), new Wager(1000), hand);

        assertThat(player.getHandCards().size()).isEqualTo(2);
    }

    @DisplayName("플레이어는 카드 1장을 추가할 수 있다")
    @Test
    void testPlayerHand_Add() {
        Player player = new Player(new Name("player"), new Wager(1000), hand);

        player.addCardToHand(new Card(Rank.ACE, Suit.SPADES));

        assertThat(player.getHandCards().size()).isEqualTo(3);
    }

    @DisplayName("플레이어는 자신이 가진 카드 덱의 합을 계산할 수 있다")
    @Test
    void testPlayerHandScore() {
        Player player = new Player(new Name("player"), new Wager(1000), hand);

        assertThat(player.getHandScore()).isNotNull();
    }

    @DisplayName("플레이어는 카드의 합이 21 미만인 동안 카드를 히트할 수 있다")
    @Test
    void testPlayerHandScore_Hit() {
        Player player = new Player(new Name("player"), new Wager(1000), hand);

        if (player.getHandScore() < 21) {
            assertThat(player.canHit()).isTrue();
        }
    }

    @DisplayName("플레이어는 카드의 합이 21 이상이면 카드를 히트할 수 없다")
    @Test
    void testPlayerHandScore_Stand() {
        Player player = new Player(new Name("player"), new Wager(1000), hand);

        if (player.getHandScore() > 21) {
            assertThat(player.canHit()).isFalse();
        }
    }

    @DisplayName("플레이어 카드의 합이 21을 넘으면 버스트다")
    @Test
    void testPlayerHandScore_Bust() {
        Player player = new Player(new Name("player"), new Wager(1000), hand);

        if (player.getHandScore() > 21) {
            assertThat(player.isBust()).isTrue();
        }
    }

    @DisplayName("플레이어는 자신의 배팅 금액을 갱신할 수 있다")
    @Test
    void testPlayerWager() {
        Player player = new Player(new Name("player"), new Wager(1000), hand);

        player.updateWager(1.5);

        assertThat(player.getWager()).isEqualTo(2500);
    }
}
