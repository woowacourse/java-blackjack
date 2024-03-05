package model.card;

import model.card.Card;
import model.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("램덤하게 카드를 선택한다.")
    @Test
    void selectRandomCard() {
        Cards cards = new Cards();
        Assertions.assertThat(cards.selectRandomCard()).isInstanceOf(Card.class);
    }

    @DisplayName("선택된 카드는 삭제된다.")
    @Test
    void deleteSelectedCard() {
        Cards cards = new Cards();
        Card card = cards.selectRandomCard();
        Assertions.assertThat(cards.contains(card)).isFalse();
    }


}
