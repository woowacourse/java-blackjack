package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("카드 손패")
public class HandTest {

    @DisplayName("패에 카드를 추가한다.")
    @Test
    void addCard() {
        //given
        Card cardFourDiamond = new Card(Rank.FOUR, Suit.DIAMOND);
        Card cardFiveDiamond = new Card(Rank.FIVE, Suit.DIAMOND);

        //when
        Hand hand = new Hand();
        hand.add(cardFourDiamond);
        hand.add(cardFiveDiamond);

        //then
        assertThat(hand.getCards())
                .contains(cardFourDiamond, cardFiveDiamond);
    }
}
