package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardPackTest {

    @Test
    @DisplayName("카드팩의 맨 뒤에서 카드를 한장 뽑는다")
    void shuffle_and_deal_test() {
        CardPack cardPack = CardPack.createShuffled();
        List<Card> cards = cardPack.getDealCards(1);
        assertThat(cards).hasSize(1);
    }
}
