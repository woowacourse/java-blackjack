package blackjack.domain.card;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DeckTest {
    
    @Test
    @DisplayName("덱에서 카드 한 장 뽑기 테스트")
    void drawCard() {
        
        // given
        List<Card> cards = new ArrayList<>();
        final Card card = new Card(Suit.DIAMOND, Rank.ACE);
        cards.add(card);
        Deck deck = new Deck(cards);
        
        // when
        Card drewCard = deck.drawCard();
        
        // then
        assertThat(card).isEqualTo(drewCard);
    }
    
    @Test
    @DisplayName("덱에 카드가 존재하지 않을 때 카드를 뽑을 경우 예외 발생")
    void drawCard_DrawAtEmptyDeck_ExceptionThrown() {
        
        // given
        Deck deck = new Deck(new ArrayList<>());
        
        // when
        ThrowableAssert.ThrowingCallable callable = deck::drawCard;
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("덱에 카드가 존재하지 않습니다.");
    }
}