package blackjack.domain.card;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("카드 목록 추가 테스트")
    public void createCardsTest(){
        Cards cards = Cards.create();

        Card card5 = Card.of(Denomination.of("5"), Symbol.of("스페이드"));
        Card card6 = Card.of(Denomination.of("6"), Symbol.of("하트"));

        cards.add(card5);
        cards.add(card6);

        assertThat(cards.size()).isEqualTo(2);
    }
}
