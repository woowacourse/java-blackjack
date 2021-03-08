package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        dealer = new Dealer(new Cards(cardList));
    }

    @Test
    @DisplayName("딜러의 점수가 17 이상이면, false를 반환한다.")
    void isEnoughScore() {
        assertThat(dealer.canDraw()).isFalse();
    }
}