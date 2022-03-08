package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDistributorTest {

    @Test
    @DisplayName("카드 두 장 배분")
    void distributeCardToDealer() {
        CardDistributor cardDistributor = new CardDistributor();

        Gamer gamer = new Gamer();
        cardDistributor.distribute(gamer, 2);

        assertThat(gamer.getCards().size()).isEqualTo(2);
    }
}
