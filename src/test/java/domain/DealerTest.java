package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.shuffler.FixedCardsShuffler;
import domain.participant.Dealer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러 카드의 합계가 16점 이하면 카드를 추가로 받아야 한다.")
    @Test
    void fillCardsTest() {
        ArrayList<Card> initialCards = new ArrayList<>(List.of(new Card("3", "스페이드"), new Card("K", "스페이드")));
        Dealer dealer = new Dealer(initialCards);

        Cards cards = new Cards(new FixedCardsShuffler());

        dealer.fillCards(cards);
        assertThat(dealer.calculateScore()).isGreaterThan(16)
                .isEqualTo(23);
    }
}
