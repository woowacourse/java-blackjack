package blackjack.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {
    @Test
    public void 카드모음_생성_테스트(){
        Cards cards = Cards.of();

        Card card5 = Card.of(Denomination.of("5"), Symbol.of("스페이드"));
        Card card6 = Card.of(Denomination.of("6"), Symbol.of("하트"));

        cards.add(card5);
        cards.add(card6);

        assertThat(cards.size()).isEqualTo(2);
    }
}
