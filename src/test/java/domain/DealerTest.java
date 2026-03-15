package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("딜러가 카드를 받으면 카드 개수가 1 증가한다")
    void drawCardByDealer() {
        Dealer dealer = new Dealer();
        Cards cards = new Cards(new Random(1));

        dealer.drawCard(cards);

        assertEquals(1, dealer.getCardList().size());
    }
}
