package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.Pattern;
import blackjack.domain.card.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HandDeckTest {

    @DisplayName("덱에 카드를 추가한다.")
    @Test
    void addCard() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card = new Card(Pattern.CLOVER, Rank.EIGHT);

        //when
        handDeck.addCard(card);
        List<Card> cards = handDeck.getCards();

        //then
        assertThat(cards).containsExactly(card);
    }

    @DisplayName("덱에 카드를 추가할 때 동일한 카드가 있는 경우 예외를 발생시킨다.")
    @Test
    void addCard_duplicateCard() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(Pattern.CLOVER, Rank.EIGHT);
        Card card2 = new Card(Pattern.CLOVER, Rank.EIGHT);

        handDeck.addCard(card1);
        //when, then
        assertThatThrownBy(() -> handDeck.addCard(card2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("덱에 있는 카드가 블랙잭인지 확인한다.")
    @Test
    void isBlackJack() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(Pattern.CLOVER, Rank.KING);
        Card card2 = new Card(Pattern.SPADE, Rank.ACE);

        //when
        handDeck.addCard(card1);
        handDeck.addCard(card2);

        boolean isBlackJack = handDeck.isBlackJack();
        //then
        assertThat(isBlackJack).isTrue();
    }

    @DisplayName("덱에 있는 모든 카드의 합을 계산한다.")
    @Test
    void calculateTotalScore() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(Pattern.CLOVER, Rank.EIGHT);
        Card card2 = new Card(Pattern.SPADE, Rank.EIGHT);

        //when
        handDeck.addCard(card1);
        handDeck.addCard(card2);

        int totalScore = handDeck.calculateTotalScore();

        //then
        assertThat(totalScore).isEqualTo(16);
    }

    @DisplayName("덱에 에이스가 포함되어 있는 경우 모든 카드의 합을 최대한 21을 넘지 않으면서 21에 가까운 수로 계산한다.")
    @Test
    void calculateTotalScore_whenDeckIncludesAceCard_1() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(Pattern.CLOVER, Rank.JACK);
        Card card2 = new Card(Pattern.SPADE, Rank.ACE);
        Card card3 = new Card(Pattern.SPADE, Rank.KING);

        handDeck.addCard(card1);
        handDeck.addCard(card2);
        handDeck.addCard(card3);

        //when
        int sumOfCards = handDeck.calculateTotalScore();

        //then
        assertThat(sumOfCards).isEqualTo(21);
    }

    @DisplayName("덱에 에이스가 포함되어 있는 경우 모든 카드의 합을 최대한 21을 넘지 않으면서 21에 가까운 수로 계산한다.")
    @Test
    void calculateTotalScore_whenDeckIncludesAceCard_2() {
        //given
        HandDeck handDeck = new HandDeck();
        Card card1 = new Card(Pattern.CLOVER, Rank.ACE);
        Card card2 = new Card(Pattern.SPADE, Rank.ACE);

        handDeck.addCard(card1);
        handDeck.addCard(card2);

        //when
        int sumOfCards = handDeck.calculateTotalScore();

        //then
        assertThat(sumOfCards).isEqualTo(12);
    }
}
