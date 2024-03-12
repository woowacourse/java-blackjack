package domain.cards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardPackTest {

    @DisplayName("카드가 모두 소진됐는지 확인한다.")
    @Test
    void allCardsUsedTest() {
        CardPack cardPack = new CardPack();
        for (int i = 0; i < 52; i++) {
            cardPack.pickOneCard();
        }
        assertThat(cardPack.isUsedAll()).isTrue();
    }
}
