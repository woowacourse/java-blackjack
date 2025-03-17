package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("카드를 받아 수중에 카드를 추가한다")
    void should_add_Card_card() {
        // given
        Card cardOfHeartAce = new Card(Shape.HEART, Rank.ACE);
        Participant dealer = new Dealer();

        // when
        dealer.addCard(cardOfHeartAce);

        // then
        List<Card> cards = dealer.getCards();
        assertAll(
                () -> assertThat(cards).hasSize(1),
                () -> assertThat(cards).contains(cardOfHeartAce)
        );
    }

    @Test
    @DisplayName("모두에게 보여줄 딜러의 카드를 가져온다")
    void should_return_cards_to_show_when_blackjack_started() {
        //given
        Participant dealer = new Dealer();
        Card cardOfHeartAce = new Card(Shape.HEART, Rank.ACE);
        Card cardOfHeartTwo = new Card(Shape.HEART, Rank.TWO);
        dealer.addCard(cardOfHeartAce);
        dealer.addCard(cardOfHeartTwo);

        //when
        List<Card> shownCard = dealer.getShownCard();

        //then
        assertThat(shownCard).hasSize(1);
    }

    @Test
    @DisplayName("현재 딜러가 카드를 받을 수 있는지 여부를 반환한다")
    void should_return_true_when_can_pick() {
        //given
        Participant dealer = new Dealer();
        Card cardOfHeartEight = new Card(Shape.HEART, Rank.EIGHT);
        Card cardOfClubEight = new Card(Shape.CLUB, Rank.EIGHT);
        dealer.addCard(cardOfHeartEight);
        dealer.addCard(cardOfClubEight);

        //when
        boolean canPick = dealer.canPick();

        //then
        assertThat(canPick).isEqualTo(true);
    }

    @Test
    @DisplayName("현재 딜러가 카드를 받을 수 있는지 여부를 반환한다")
    void should_return_false_when_can_not_pick() {
        //given
        Participant dealer = new Dealer();
        Card cardOfHeartEight = new Card(Shape.HEART, Rank.EIGHT);
        Card cardOfClubNine = new Card(Shape.CLUB, Rank.NINE);
        dealer.addCard(cardOfHeartEight);
        dealer.addCard(cardOfClubNine);

        //when
        boolean canPick = dealer.canPick();

        //then
        assertThat(canPick).isEqualTo(false);
    }
}
