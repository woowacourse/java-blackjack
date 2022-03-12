package domain.participant;

import static domain.MockCard.CLUB_ACE_CARD;
import static domain.MockCard.HEART_TEN_CARD;
import static domain.MockCard.SPADE_NINE_CARD;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("게임 중에는 한 장의 카드만 반환.")
    void getCards() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(CLUB_ACE_CARD);
        dealer.receiveCard(CLUB_ACE_CARD);
        dealer.receiveCard(CLUB_ACE_CARD);

        assertThat(dealer.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게임이 끝나면 두 장의 카드를 반환한다.")
    void getCards2() {
        //given
        Dealer dealer = new Dealer();
        dealer.receiveCard(CLUB_ACE_CARD);
        dealer.receiveCard(HEART_TEN_CARD);
        dealer.receiveCard(SPADE_NINE_CARD);

        //when
        dealer.stopRunning();

        //then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }
}
