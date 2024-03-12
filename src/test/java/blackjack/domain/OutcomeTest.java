package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {

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
    @DisplayName("플레이어와 딜러가 모두 버스트된 경우 무승부이다.")
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

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
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

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러만 버스트했다면 딜러의 패배이다.")
    @Test
    void dealerLoseWhenOnlyBust() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();
        dealer.draw();

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.WIN);
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

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("플레이어만 블랙잭이라면 플레이어의 승리이다.")
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

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("딜러만 블랙잭이라면 딜러의 승리이다.")
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

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수가 딜러보다 높으면 승리한다.")
    @Test
    void playerWinWhenBiggerThanDealer() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수가 딜러보다 낮으면 패배한다.")
    @Test
    void playerLoseWhenLessThanDealer() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("플레이어 또는 딜러 패에 에이스가 포함되지 않은 경우, 플레이어 점수와 딜러의 점수가 같으면 무승부이다.")
    @Test
    void pushWhenSame() {
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();

        final Outcome outcome = Outcome.doesPlayerWin(dealer, player);

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("승을 패로 반전한다.")
    @Test
    void convertWinToLose() {
        final Outcome convertedOutcome = Outcome.reverse(Outcome.WIN);

        assertThat(convertedOutcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("패를 승으로 반전한다.")
    @Test
    void convertLoseToWin() {
        final Outcome convertedOutcome = Outcome.reverse(Outcome.LOSE);

        assertThat(convertedOutcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("무는 반전해도 무다.")
    @Test
    void convertPushToPush() {
        final Outcome convertedOutcome = Outcome.reverse(Outcome.PUSH);

        assertThat(convertedOutcome).isEqualTo(Outcome.PUSH);
    }
}
