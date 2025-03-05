package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("카드를 받아 수중에 카드를 추가한다")
    void should_add_Card_card() {
        // given
        Card card = new Card(Shape.HEART, Rank.A);
        Participant dealer = new Dealer("a");

        // when
        dealer.addCard(card);

        // then
        List<Card> cards = dealer.getCards();
        assertThat(cards).hasSize(1);
    }

    @Test
    @DisplayName("모두에게 보여줄 딜러의 카드를 가져온다")
    void should_return_public_able_cards() {
        //given
        Participant dealer = new Dealer("james");
        dealer.addCard(new Card(Shape.HEART, Rank.A));
        dealer.addCard(new Card(Shape.HEART, Rank.ONE));

        //when
        List<Card> shownCard = dealer.getShownCard();

        //then
        assertThat(shownCard).hasSize(1);
    }

}
