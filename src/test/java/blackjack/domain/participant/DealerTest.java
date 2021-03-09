package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러의 점수가 17 이상이면, true를 반환한다.")
    void isEnoughScore() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        Dealer dealer = new Dealer(new Cards(cardList));

        assertThat(dealer.doneReceiving()).isTrue();
    }

    @Test
    @DisplayName("딜러의 점수가 17 미만이면, false를 반환한다.")
    void isNotEnoughScore() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.THREE));
        Dealer dealer = new Dealer(new Cards(cardList));

        assertThat(dealer.doneReceiving()).isFalse();
    }
}