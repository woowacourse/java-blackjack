package blackjack.domain.betting;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.hand.CardHand;
import blackjack.domain.hand.OneCard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerBettingTest {

    private static final int BETTING_AMOUNT = 1000;

    private static final CardHand cardHand17 = new OneCard(CLOVER7).hit(CLOVER10).stay();
    private static final CardHand cardHand20 = new OneCard(CLOVER10).hit(CLOVER_KING).stay();
    private static final CardHand blackjack = new OneCard(CLOVER_ACE).hit(CLOVER_KING);
    private static final CardHand bust = new OneCard(CLOVER10).hit(CLOVER_KING).hit(CLOVER7);

    @DisplayName("플레이어가 블랙잭으로 승리한 경우, 베팅금액의 1.5배를 획득한다.")
    @Test
    void blackjackWin() {
        PlayerBetting playerBetting = getPlayerOf(blackjack);
        Participant dealer = new Dealer(cardHand17);

        int actual = playerBetting.profit(dealer);
        int expected = (int) (BETTING_AMOUNT * 1.5);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 승리한 경우, 베팅금액만큼 얻는다.")
    @Test
    void win() {
        PlayerBetting playerBetting = getPlayerOf(cardHand20);
        Participant dealer = new Dealer(cardHand17);

        int actual = playerBetting.profit(dealer);

        assertThat(actual).isEqualTo(BETTING_AMOUNT);
    }

    @DisplayName("무승부인 경우 플레이어는 돈을 얻지도 잃지도 않는다.")
    @Test
    void draw() {
        PlayerBetting playerBetting = getPlayerOf(cardHand17);
        Participant dealer = new Dealer(cardHand17);

        int actual = playerBetting.profit(dealer);
        int expected = 0;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("서로 블랙잭인 경우, 무승부로 간주한다.")
    @Test
    void blackjackDraw() {
        PlayerBetting playerBetting = getPlayerOf(blackjack);
        Participant dealer = new Dealer(blackjack);

        int actual = playerBetting.profit(dealer);
        int expected = 0;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 패배한 경우, 베팅금액만큼 잃는다.")
    @Test
    void lose() {
        PlayerBetting playerBetting = getPlayerOf(cardHand17);
        Participant dealer = new Dealer(cardHand20);

        int actual = playerBetting.profit(dealer);
        int expected = BETTING_AMOUNT * -1;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("딜러와 플레이어 모두 버스트인 경우 플레이어가 패배한 것으로 간주한다.")
    @Test
    void loseOnBothBust() {
        PlayerBetting playerBetting = getPlayerOf(bust);
        Participant dealer = new Dealer(bust);

        int actual = playerBetting.profit(dealer);
        int expected = BETTING_AMOUNT * -1;

        assertThat(actual).isEqualTo(expected);
    }

    private PlayerBetting getPlayerOf(CardHand cardHand) {
        Player player = new Player("jeong", cardHand);
        return new PlayerBetting(player, BETTING_AMOUNT);
    }
}
