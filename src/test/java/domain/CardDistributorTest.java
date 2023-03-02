package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.CardDeckMaker;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDistributorTest {

    @Test
    @DisplayName("카드는 한 번 뽑으면 삭제된다.")
    void removeCardWhenPicked() {
        CardDistributor cardDistributor = new CardDistributor(CardDeckMaker.generate());

        Card card = cardDistributor.pick();

        assertThat(cardDistributor.getDeckSize()).isEqualTo(51);
        assertThat(card).isNotNull();
    }

}
