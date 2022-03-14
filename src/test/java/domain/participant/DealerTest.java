package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

public class DealerTest {

    Card card1;
    Card card2;

    @BeforeEach
    void setup() {
        card1 = new Card(Rank.RANK_J, Suit.SPADE);
        card2 = new Card(Rank.RANK_A, Suit.CLOVER);
    }

    @Test
    @DisplayName("딜러가 손패를 더 받아야 하는 경우")
    void isEnoughCard_False() {
        Card card = new Card(Rank.RANK_5, Suit.HEART);
        Dealer dealer = new Dealer(new ArrayList<>(List.of(card, card1, card2)));
        assertThat(dealer.isNeedToDraw()).isTrue();
    }

    @Test
    @DisplayName("딜러가 손패를 더 안 받아야 하는 경우")
    void isEnoughCard_True() {
        Card card = new Card(Rank.RANK_6, Suit.HEART);
        Dealer dealer = new Dealer(new ArrayList<>(List.of(card, card1, card2)));
        assertThat(dealer.isNeedToDraw()).isFalse();
    }

    @Test
    @DisplayName("딜러의 첫번째 손패만 문자열로 반환하기")
    void showOneHand() {
        Dealer dealer = new Dealer(new ArrayList<>(List.of(card1, card2)));
        assertThat(dealer.getFirstHand().toString()).isEqualTo("J스페이드");
    }
}
