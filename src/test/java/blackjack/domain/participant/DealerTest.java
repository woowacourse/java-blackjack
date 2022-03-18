package blackjack.domain.participant;

import static blackjack.domain.CardFixtures.JACK_SPACE;
import static blackjack.domain.CardFixtures.KING_SPACE;
import static blackjack.domain.CardFixtures.THREE_SPACE;
import static blackjack.domain.CardFixtures.TWO_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러를 정상적으로 생성한다.")
    @Test
    void 딜러_정상_생성() {
        assertDoesNotThrow(() -> new Dealer());
    }

    @DisplayName("딜러는 현재 상태를 판단하여 준비가 완료되지 않으면 false를 반환한다.")
    @Test
    void 딜러_준비_여부() {
        Dealer dealer = new Dealer();

        boolean result = dealer.isReady();

        assertThat(result).isFalse();
    }

    @DisplayName("딜러는 현재 상태를 판단하여 준비가 완료되면 true를 반환한다.")
    @Test
    void 딜러_준비_완료() {
        Dealer dealer = new Dealer();

        dealer.hit(TWO_SPACE);
        dealer.hit(THREE_SPACE);

        boolean result = dealer.isReady();

        assertThat(result).isTrue();
    }

    @DisplayName("딜러는 현재 상태를 판단하여 hit를 진행한다.")
    @Test
    void 딜러_히트() {
        Dealer dealer = new Dealer();
        dealer.hit(TWO_SPACE);

        List<Card> cards = dealer.getCards();

        assertThat(cards.size()).isEqualTo(1);
    }

    @DisplayName("플레이어는 현재 상태를 판단하여 게임의 종료 여부를 반환한다.")
    @Test
    void 딜러_종료_여부() {
        Dealer dealer = new Dealer();
        dealer.hit(KING_SPACE);
        dealer.hit(JACK_SPACE);

        boolean result = dealer.isFinished();

        assertThat(result).isTrue();
    }

    @DisplayName("보유한 카드의 총점을 반환한다.")
    @Test
    void 딜러_총점() {
        Dealer dealer = new Dealer();
        dealer.hit(KING_SPACE);
        dealer.hit(JACK_SPACE);

        int result = dealer.getTotalScore();

        assertThat(result).isEqualTo(20);
    }
}