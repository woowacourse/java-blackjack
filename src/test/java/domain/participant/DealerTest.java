package domain.participant;

import static domain.Fixtures.KING_HEART;
import static domain.Fixtures.KING_SPADE;
import static domain.Fixtures.THREE_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드를 추가로 받고, 점수를 계산할 수 있다.")
    @Test
    void fillCardsTest() {
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(new Cards(List.of(KING_SPADE, KING_HEART)));
        dealer.receiveCard(THREE_SPADE);
        assertThat(dealer.getScore()).isEqualTo(23);
    }
}
