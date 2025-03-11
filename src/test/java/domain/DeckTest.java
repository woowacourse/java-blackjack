package domain;

import static domain.deck.Rank.ACE;
import static domain.deck.Suit.CLOVER;
import static domain.deck.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.deck.Card;
import domain.deck.Deck;
import fixture.CardFixture;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @DisplayName("카드를 한 장 뽑을 수 있다.")
    void test() {
        // given
        Deck deck = new Deck(List.of(CardFixture.of(ACE, CLOVER)));
        // when
        Card card = deck.drawNewCard();
        // then
        assertThat(card).isEqualTo(CardFixture.of(ACE, CLOVER));
    }

    @Test
    @DisplayName("모든 카드를 뽑으면 예외가 발생한다.")
    void test2() {
        // given
        Deck deck = new Deck(List.of(CardFixture.of(ACE, CLOVER)));
        deck.drawNewCard();
        // when && then
        assertThatThrownBy(deck::drawNewCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 카드를 생성할 수 없습니다.");
    }

    @Test
    @DisplayName("맨 처음 두 장의 카드를 반환할 수 있다.")
    void test4() {
        // given
        List<Card> cards = List.of(CardFixture.of(ACE, CLOVER), CardFixture.of(ACE, HEART));
        Deck deck = new Deck(cards);
        // when
        CardHand initialDeal = deck.getInitialDeal();
        // then
        assertThat(initialDeal).isEqualTo(new CardHand(Set.copyOf(cards)));
    }
}
