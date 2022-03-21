package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    Card card1 = Card.getCard(Rank.RANK_J, Suit.SPADE);
    Card card2 = Card.getCard(Rank.RANK_A, Suit.CLOVER);

    @Test
    @DisplayName("딜러가 손패를 더 받아야 하는 경우")
    void isNeedToDraw_True() {
        // given
        Card card = Card.getCard(Rank.RANK_5, Suit.HEART);

        // when
        Dealer dealer = new Dealer(new ArrayList<>(List.of(card, card1, card2)));

        // then
        assertThat(dealer.isNeedToDraw()).isTrue();
    }

    @Test
    @DisplayName("딜러가 손패를 더 안 받아야 하는 경우")
    void isNeedToDraw_False() {
        // given
        Card card = Card.getCard(Rank.RANK_6, Suit.HEART);

        // when
        Dealer dealer = new Dealer(new ArrayList<>(List.of(card, card1, card2)));

        // then
        assertThat(dealer.isNeedToDraw()).isFalse();
    }

    @Test
    @DisplayName("딜러의 첫번째 손패만 문자열로 반환하기")
    void showOneHand() {
        // when
        Dealer dealer = new Dealer(new ArrayList<>(List.of(card1, card2)));

        // then
        assertThat(dealer.getFirstCardOfHand().combineRankAndSuit()).isEqualTo("J♠️");
    }
}
