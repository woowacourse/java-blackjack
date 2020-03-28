package domain.gamer;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @Test
    @DisplayName("딜러가 카드를 뽑을수 있는 경우")
    public void isDrawableTest() {
        Dealer dealer = new Dealer();
        dealer.addCard(Arrays.asList(
                new Card(CardSuit.CLOVER, CardNumber.SIX),
                new Card(CardSuit.CLOVER, CardNumber.TEN))
        );
        assertThat(dealer.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더이상 뽑을수 없는 경우")
    public void isNotDrawableTest() {
        Dealer dealer = new Dealer();
        dealer.addCard(Arrays.asList(
                new Card(CardSuit.CLOVER, CardNumber.SEVEN),
                new Card(CardSuit.CLOVER, CardNumber.TEN))
        );
        assertThat(dealer.isDrawable()).isFalse();
    }

    static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.SIX),
                        new Card(CardSuit.CLOVER, CardNumber.TEN)), 3),
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.KING),
                        new Card(CardSuit.CLOVER, CardNumber.TEN)), 2));
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    @DisplayName("카드 점수가 16이하면 추가 카드 지급")
    void addCardAtDealerTest(List<Card> cards, int expectedSize) {
        Dealer dealer = new Dealer();
        dealer.addCard(cards);
        Deck deck = new Deck(CardsFactory.getCards());
        dealer.addCardAtDealer(deck);
        assertThat(dealer.getCards()).hasSize(expectedSize);
    }

    @Test
    @DisplayName("추가된후 카드끼리 중복이 없어야함")
    void addCardAtDealerTest() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(CardsFactory.getCards());
        while (dealer.isDrawable()) {
            dealer.addCardAtDealer(deck);
        }

        int dealerCardSize = dealer.getCards().size();
        int dealerCardSizeAfterDistinct = (int) dealer.getCards()
                .stream()
                .map(card -> card.getCardNumber().getCardInitial() + card.getCardSuit().getSuit())
                .distinct()
                .count();

        assertThat(dealerCardSize).isEqualTo(dealerCardSizeAfterDistinct);
    }
}