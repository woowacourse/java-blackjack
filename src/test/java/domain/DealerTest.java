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
        Participant dealer = new Dealer();

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
        Participant dealer = new Dealer();
        dealer.addCard(new Card(Shape.HEART, Rank.A));
        dealer.addCard(new Card(Shape.HEART, Rank.ONE));

        //when
        List<Card> shownCard = dealer.getShownCard();

        //then
        assertThat(shownCard).hasSize(1);
    }

    @Test
    @DisplayName("현재 카드의 합이 카드를 뽑을 수 있는 조건인 16 이하일 경우, true를 반환한다")
    void should_return_true_when_can_pick() {
        //given
        List<Card> cards = List.of(new Card(Shape.HEART, Rank.EIGHT),
            new Card(Shape.CLUB, Rank.EIGHT));
        Participant dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }

        //when
        boolean canPick = dealer.canPick();

        //then
        assertThat(canPick).isTrue();
    }

    @Test
    @DisplayName("현재 카드의 합이 카드를 뽑을 수 있는 조건인 17 이상일 경우, false를 반환한다")
    void should_return_false_when_cannot_pick() {
        //given
        List<Card> cards = List.of(new Card(Shape.HEART, Rank.EIGHT),
            new Card(Shape.CLUB, Rank.NINE));
        Participant dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }

        //when
        boolean canPick = dealer.canPick();

        //then
        assertThat(canPick).isFalse();
    }

}
