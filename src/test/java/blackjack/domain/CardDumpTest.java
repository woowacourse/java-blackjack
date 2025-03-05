package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDumpTest {

    @DisplayName("카드 덤프에서 카드를 뽑아 반환한다.")
    @Test
    void testDrawRandomCardFromCardDump() {
        CardDump cardDump = new CardDump();
        Card card = cardDump.drawCard();

        assertThat(card).isNotNull();
    }

    @DisplayName("52장의 카드가 중복이 없다는 것을 확인한다.")
    @Test
    void testCardDumpSize() {
        CardDump cardDump = new CardDump();

        Set<Card> cardSet = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            Card card = cardDump.drawCard();
            cardSet.add(card);
        }
        assertThat(cardSet).hasSize(52);
    }
}
