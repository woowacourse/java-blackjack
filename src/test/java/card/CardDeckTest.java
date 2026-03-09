package card;

import domain.card.Card;
import domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {
    private static final String CONSUME_ALL_CARD_DECK_ERROR_MESSAGE = "[ERROR] 카드를 다 뽑았습니다.";

    @Test
    void 생성한_카드덱은_카드_52장을_가진다() {
        //given
        CardDeck cardDeck = new CardDeck();
        //when
        int size = cardDeck.getDeckSize();
        //then
        assertThat(size).isEqualTo(52);
    }

    @Test
    void 카드덱의_52장은_중복되지_않는다() {
        //given
        CardDeck cardDeck = new CardDeck();
        Set<Card> notDuplicatedDeck = new HashSet<>();

        //when
        for (int i = 0; i < 52; i++) {
            notDuplicatedDeck.add(cardDeck.drawCard());
        }

        //then
        assertThat(notDuplicatedDeck.size()).isEqualTo(52);
    }

    @Test
    void 카드덱에서_한장을_뽑으면_카드덱_사이즈가_1만큼_감소한다() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        cardDeck.drawCard();

        //then
        assertThat(cardDeck.getDeckSize()).isEqualTo(51);
    }

    @Test
    @DisplayName("카드를 52장을 다 뽑고 또 뽑으면 예외가 발생한다")
    void drawCard_ThrowsException_WhenAllCardsAreUsed() {
        CardDeck cardDeck = new CardDeck();

        for (int i = 0; i < 52; i++) {
            cardDeck.drawCard();
        }

        assertThatThrownBy(cardDeck::drawCard)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(CONSUME_ALL_CARD_DECK_ERROR_MESSAGE);
    }
}
