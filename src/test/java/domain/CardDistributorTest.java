package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CardDistributorTest {

    @Test
    @DisplayName("카드 분배기가 카드를 분배해준다.")
    void pickSuccess() {
        CardDeck cardDeck = new CardDeck();
        CardDistributor cardDistributor = new CardDistributor(cardDeck);

        Card card = cardDistributor.pick();

        assertThat(card).isNotNull();
    }
}
