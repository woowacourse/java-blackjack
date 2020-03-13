package blackjack.card.domain;

import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {

    @DisplayName("CardDeck 에 더 이상 없을때 뽑으려하면 Exception 발생")
    @Test
    void drawCardException() {
        CardDeck cardDeck = CardDeck.getInstance(new CardCreateStrategy() {
            @Override
            public List<Card> getCards() {
                return Collections.emptyList();
            }
        });

        assertThatThrownBy(cardDeck::draw)
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("CardDeck에서 가장 위에 있는 한장을 뽑아온다.")
    @Test
    void testDrawCard() {
        //given
        CardDeck cardDeck = CardDeck.getInstance(new CardCreateStrategy() {
            @Override
            public List<Card> getCards() {
                return Arrays.asList(Card.of(Symbol.DIAMOND, CardNumber.ACE));
            }
        });

        //when
        Card expect = cardDeck.draw();

        //then
        assertThat(expect).isEqualTo(Card.of(Symbol.DIAMOND, CardNumber.ACE));
    }

    @DisplayName("카드 생성 전략이 없을 경우 인스턴스화 불가능 Exception")
    @Test
    void getInstance() {
        assertThatThrownBy(() -> CardDeck.getInstance(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}