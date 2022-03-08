package blackjack.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {
    @Test
    public void 카드모음_생성_테스트(){
        Cards cards = Cards.of();
        cards.add(Card.of(10, "spade"));
        cards.add(Card.of(5, "heart"));
        assertThat(cards.size()).isEqualTo(2);
    }
}
