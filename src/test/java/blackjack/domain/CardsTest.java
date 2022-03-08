package blackjack.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import rentCompany.car.Car;

@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {
    @Test
    public void 카드모음_생성_테스트(){
        Cards cards = Cards.of();
        cards.addCard(Card.of(10, "spade"));
        cards.addCard(Card.of(5, "heart"));
        assertThat(cards.size()).isEqualTo(2);
    }
}
