package domain.gamer;

import domain.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    List<Card> unShuffledDeck;

    @BeforeEach
    void setUp() {
        unShuffledDeck = Card.makeCardDeck();
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합을 저장한다.")
    @Test
    void saveTotalCardNumbersTest() {
        Hand hand = new Hand();
        Card twoCard = unShuffledDeck.get(1);
        Card threeCard = unShuffledDeck.get(2);
        Card fourCard = unShuffledDeck.get(3);

        System.out.println(twoCard.getCardNumber() + " " + threeCard.getCardNumber());
        hand.addCard(twoCard);
        hand.addCard(threeCard);
        hand.addCard(fourCard);

        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @DisplayName("A를 1로 계산한 모든 카드의 합에 10을 더해도 21을 넘지 않으면 A는 11로 계산한다.")
    @Test
    void calculateScoreWhenUnderThresholdTest() {

        Card fiveCard = unShuffledDeck.get(4);
        Card aceCard = unShuffledDeck.get(0);

        Hand hand = new Hand();
        hand.addCard(fiveCard);
        hand.addCard(fiveCard);
        hand.addCard(aceCard);

        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A를 1로 계산한 모든 카드의 합에 10을 더해 21을 넘으면 A는 1로 계산한다.")
    @Test
    void calculateScoreWhenOverThresholdTest() {
        Card tenCard = unShuffledDeck.get(9);
        Card aceCard = unShuffledDeck.get(0);

        Hand hand = new Hand();
        hand.addCard(aceCard);
        hand.addCard(aceCard);
        hand.addCard(tenCard);

        assertThat(hand.calculateScore()).isEqualTo(12);
    }
}
