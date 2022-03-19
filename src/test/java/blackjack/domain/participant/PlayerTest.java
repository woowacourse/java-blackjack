package blackjack.domain.participant;

import static blackjack.domain.CardFixtures.ACE_SPACE;
import static blackjack.domain.CardFixtures.JACK_SPACE;
import static blackjack.domain.CardFixtures.KING_SPACE;
import static blackjack.domain.CardFixtures.QUEEN_SPACE;
import static blackjack.domain.CardFixtures.THREE_SPACE;
import static blackjack.domain.CardFixtures.TWO_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("이름과 배팅 금액이 주어지면 정상적으로 생성된다.")
    @Test
    void 플레이어_정상_생성() {
        assertDoesNotThrow(() -> new Player("mat", 10000));
    }

    @DisplayName("플레이어는 현재 상태를 판단하여 준비가 완료되지 않으면 false를 반환한다.")
    @Test
    void 플레이어_준비_여부() {
        Player player = new Player("mat", 10000);

        boolean result = player.isReady();

        assertThat(result).isFalse();
    }

    @DisplayName("플레이어는 현재 상태를 판단하여 준비 완료이면 true를 반환한다.")
    @Test
    void 플레이어_준비_완료() {
        Player player = new Player("mat", 10000);

        player.hit(TWO_SPACE);
        player.hit(THREE_SPACE);

        boolean result = player.isReady();

        assertThat(result).isTrue();
    }
    
    @DisplayName("플레이어는 현재 상태를 판단하여 hit를 진행한다.")
    @Test
    void 플레이어_히트() {
        Player player = new Player("mat", 10000);
        player.hit(TWO_SPACE);

        List<Card> cards = player.getCards();

        assertThat(cards.size()).isEqualTo(1);
    }
    
    @DisplayName("플레이어가 stay를 진행할 경우 더 이상 게임을 진행하지 않는다.")
    @Test
    void 플레이어_스테이() {
        Player player = new Player("mat", 10000);
        player.hit(KING_SPACE);
        player.hit(JACK_SPACE);

        player.stay();

        assertThat(player.isFinished()).isTrue();
    }
    
    @DisplayName("플레이어는 현재 상태를 판단하여 게임의 종료 여부를 반환한다.")
    @Test
    void 플레이어_종료_여부() {
        Player player = new Player("mat", 10000);
        player.hit(KING_SPACE);
        player.hit(JACK_SPACE);
        player.hit(QUEEN_SPACE);

        boolean result = player.isFinished();

        assertThat(result).isTrue();
    }

    @DisplayName("보유한 카드의 총점을 반환한다.")
    @Test
    void 플레이어_총점() {
        Player player = new Player("mat", 10000);
        player.hit(KING_SPACE);
        player.hit(JACK_SPACE);

        int result = player.getTotalScore();

        assertThat(result).isEqualTo(20);
    }

    @DisplayName("플레이어가 블랙잭으로 승리한 경우 배팅 금액의 1.5배를 얻는다.")
    @Test
    void 플레이어_블랙잭_승() {
        Dealer dealer = new Dealer();
        dealer.hit(JACK_SPACE);
        dealer.hit(KING_SPACE);

        Player player = new Player("mat", 10000);
        player.hit(ACE_SPACE);
        player.hit(KING_SPACE);

        int result = player.calculateProfit(dealer);

        assertThat(result).isEqualTo(15000);
    }

    @DisplayName("플레이어가 블랙잭으로 무승부 인경우 배팅 금액의 0배를 얻는다.")
    @Test
    void 플레이어_블랙잭_무승부() {
        Dealer dealer = new Dealer();
        dealer.hit(ACE_SPACE);
        dealer.hit(KING_SPACE);

        Player player = new Player("mat", 10000);
        player.hit(ACE_SPACE);
        player.hit(KING_SPACE);

        int result = player.calculateProfit(dealer);

        assertThat(result).isZero();
    }

    @DisplayName("플레이어가 블랙잭과 버스트가 아니고 점수를 비교하여 무승부인 경우 배팅 금액의 0배를 얻는다.")
    @Test
    void 플레이어_무승부() {
        Dealer dealer = new Dealer();
        dealer.hit(JACK_SPACE);
        dealer.hit(KING_SPACE);

        Player player = new Player("mat", 10000);
        player.hit(JACK_SPACE);
        player.hit(KING_SPACE);
        player.stay();

        int result = player.calculateProfit(dealer);

        assertThat(result).isZero();
    }

    @DisplayName("플레이어가 블랙잭과 버스트가 아니고 점수를 비교하여 승인 경우 배팅 금액의 1배를 얻는다.")
    @Test
    void 플레이어_승() {
        Dealer dealer = new Dealer();
        dealer.hit(TWO_SPACE);
        dealer.hit(KING_SPACE);

        Player player = new Player("mat", 10000);
        player.hit(JACK_SPACE);
        player.hit(KING_SPACE);
        player.stay();

        int result = player.calculateProfit(dealer);

        assertThat(result).isEqualTo(10000);
    }

    @DisplayName("플레이어가 버스트인 경우 무조건 패이므로 배팅 금액의 -1배를 얻는다.")
    @Test
    void 플레이어_버스트() {
        Dealer dealer = new Dealer();
        dealer.hit(TWO_SPACE);
        dealer.hit(KING_SPACE);

        Player player = new Player("mat", 10000);
        player.hit(JACK_SPACE);
        player.hit(QUEEN_SPACE);
        player.hit(KING_SPACE);

        int result = player.calculateProfit(dealer);

        assertThat(result).isEqualTo(-10000);
    }

    @DisplayName("플레이어와 딜러가 모두 버스트인 경우 무조건 플레이어 패이므로 배팅 금액의 -1배를 얻는다.")
    @Test
    void 플레이어_딜러_버스트() {
        Dealer dealer = new Dealer();
        dealer.hit(JACK_SPACE);
        dealer.hit(THREE_SPACE);
        dealer.hit(KING_SPACE);

        Player player = new Player("mat", 10000);
        player.hit(JACK_SPACE);
        player.hit(QUEEN_SPACE);
        player.hit(KING_SPACE);

        int result = player.calculateProfit(dealer);

        assertThat(result).isEqualTo(-10000);
    }

    @DisplayName("플레이어가 블랙잭과 버스트가 아니고 점수를 비교하여 패인 경우 배팅 금액의 -1배를 얻는다.")
    @Test
    void 플레이어_패() {
        Dealer dealer = new Dealer();
        dealer.hit(JACK_SPACE);
        dealer.hit(THREE_SPACE);

        Player player = new Player("mat", 10000);
        player.hit(JACK_SPACE);
        player.hit(TWO_SPACE);
        player.stay();

        int result = player.calculateProfit(dealer);

        assertThat(result).isEqualTo(-10000);
    }
}
