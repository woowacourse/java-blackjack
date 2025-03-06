import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @Test
    void 카드_덱에서_카드_두_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Hand hand = new Hand();

        //when
        hand.drawCardWhenStart(cardDeck);

        //then
        assertThat(hand.getCards()).hasSize(2);
    }

    @Test
    void 카드_덱에서_카드_한_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Hand hand = new Hand();

        //when
        hand.drawCard(cardDeck);

        //then
        assertThat(hand.getCards()).hasSize(1);
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다_21초과_케이스() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when & then
        assertThat(hand.checkBurst()).isTrue();
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다_21이하_케이스() {
        //given
        Hand hand = new Hand();
        List<Card> drawnCards = hand.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.NINE));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when & then
        assertThat(hand.checkBurst()).isFalse();
    }
}
