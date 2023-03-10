package domain.card;

import static domain.Fixtures.KING_HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.shuffler.CardsShuffler;
import domain.shuffler.FixedCardsShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CardDeckTest {

    private final CardsShuffler fixedShuffler = new FixedCardsShuffler();
    private final CardDeck cardDeck = new CardDeck(fixedShuffler);

    @DisplayName("처음에 2장의 카드를 받을 수 있다.")
    @Test
    void createCardsSizeTest() {
        assertThat(cardDeck.giveInitialCards().size()).isEqualTo(2);
    }

    @DisplayName("카드를 뒤에서 1장 받을 수 있다.")
    @Test
    void getOneCardTest() {
        assertThat(cardDeck.getCard()).isEqualTo(KING_HEART);
    }
}
