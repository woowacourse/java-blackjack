package blackjack.domain.participant;

import static blackjack.domain.TestCardFixture.aceCard;
import static blackjack.domain.TestCardFixture.fiveCard;
import static blackjack.domain.TestCardFixture.jackCard;
import static blackjack.domain.TestCardFixture.kingCard;
import static blackjack.domain.TestCardFixture.sevenCard;
import static blackjack.domain.TestCardFixture.sixCard;
import static blackjack.domain.TestCardFixture.tenCard;
import static blackjack.domain.TestCardFixture.threeCard;
import static blackjack.domain.TestCardFixture.twoCard;
import static org.assertj.core.api.Assertions.*;

import blackjack.domain.state.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.from("seung", "10000");
    }

    @Test
    @DisplayName("플레이어가 정상적으로 생성되는지 확인")
    void create() {
        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받는지 확인")
    void receiveCard() {
        player.hit(aceCard);

        assertThat(player.getCards()).containsExactly(aceCard);
    }

    @Test
    @DisplayName("카드를 받을 수 있는 상태인지 확인")
    void isFinished() {
        player.hit(kingCard);
        player.hit(tenCard);
        player.hit(jackCard);

        assertThat(player.isFinished()).isTrue();
    }

    @Test
    @DisplayName("카드를 받을 수 없는 상태인지 확인")
    void doesNotFinished() {
        player.hit(aceCard);
        player.hit(twoCard);

        assertThat(player.isFinished()).isFalse();
    }

    @Test
    @DisplayName("스테이 상태인지 확인")
    void stay() {
        player.hit(aceCard);
        player.hit(fiveCard);
        player.stay();
        Participant participant = player.getParticipant();

        assertThat(participant.getState()).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("준비 상태가 아닌지 확인")
    void doesNotReady() {
        assertThat(player.isReady()).isFalse();
    }

    @Test
    @DisplayName("준비 상태인지 확인")
    void isReady() {
        player.hit(kingCard);
        player.hit(tenCard);

        assertThat(player.isReady()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 finished 상태이면 hit해도 카드를 더이상 받을 수 없다.")
    void doesNotHit() {
        player.hit(aceCard);
        player.hit(tenCard);
        player.hit(threeCard);
        Participant participant = player.getParticipant();

        assertThat(participant.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("이익을 계산한다.")
    void calculateProfit() {
        player.hit(kingCard);
        player.hit(aceCard);
        Dealer dealer = Dealer.create();
        dealer.hit(sixCard);
        dealer.hit(sevenCard);

        assertThat(player.calculateProfit(dealer)).isEqualTo(15000);
    }
}
