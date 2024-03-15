package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {

    //@formatter:off
    /**
     * 카드 저장 순서
     * Ace카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * 2카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * ...
     * KING카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     *
     * 카드 pop 순서는 카드 저장 순서의 역순이다.
     */
    //@formatter:on
    @DisplayName("플레이어와 딜러가 모두 버스트된 경우 플레이어의 패배이다.")
    @Test
    void pushWhenBothBust() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();
        dealer.draw();
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();

        assertThat(playerOutcome).isEqualTo(PlayerOutcome.LOSE);
    }

    @DisplayName("플레이어만 버스트했다면 플레이어의 패배이다.")
    @Test
    void playerLoseWhenOnlyBust() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();


        assertThat(playerOutcome).isEqualTo(PlayerOutcome.LOSE);
    }

    @DisplayName("플레이어가 블랙잭이 아니고, 딜러만 버스트했다면 플레이어의 일반승리이다.")
    @Test
    void dealerLoseWhenOnlyBust() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();
        dealer.draw();
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();


        assertThat(playerOutcome).isEqualTo(PlayerOutcome.NORMAL_WIN);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 무승부이다.")
    @Test
    void pushWhenBothBlackJack() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        for (int i = 0; i < 48; i++) {
            dealer.drawPlayerCard();
        }
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();

        assertThat(playerOutcome).isEqualTo(PlayerOutcome.PUSH);
    }

    @DisplayName("플레이어만 블랙잭이라면 플레이어의 블랙잭 승리이다.")
    @Test
    void playerWinWhenOnlyBlackJack() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        for (int i = 0; i < 48; i++) {
            dealer.drawPlayerCard();
        }
        player.draw(dealer.drawPlayerCard());
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();


        assertThat(playerOutcome).isEqualTo(PlayerOutcome.BLACKJACK_WIN);
    }

    @DisplayName("딜러만 블랙잭이라면 플레이어의 패배이다.")
    @Test
    void dealerWinWhenOnlyBlackJack() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        for (int i = 0; i < 48; i++) {
            dealer.drawPlayerCard();
        }
        dealer.draw();
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();


        assertThat(playerOutcome).isEqualTo(PlayerOutcome.LOSE);
    }

    @DisplayName("버스트나 블랙잭이 없을 때, 플레이어 점수가 딜러보다 높으면 플레이어의 일반승리이다.")
    @Test
    void playerWinWhenBiggerThanDealer() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();


        assertThat(playerOutcome).isEqualTo(PlayerOutcome.NORMAL_WIN);
    }

    @DisplayName("버스트나 블랙잭이 없을 때, 플레이어 점수가 딜러보다 낮으면 플레이어의 패배이다.")
    @Test
    void playerLoseWhenLessThanDealer() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();


        assertThat(playerOutcome).isEqualTo(PlayerOutcome.LOSE);
    }

    @DisplayName("버스트가 없을 때, 플레이어 점수와 딜러의 점수가 같으면 무승부이다.")
    @Test
    void pushWhenSame() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();
        final Judge judge = new Judge(dealer, player);

        final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome();


        assertThat(playerOutcome).isEqualTo(PlayerOutcome.PUSH);
    }
}
