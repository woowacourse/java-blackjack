package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 카드를 뽑으면 그 카드가 뽑은 카드에 포함된다.")
    @Test
    void drawn_cards_contains_when_dealer_pick() {
        // given
        Card expectedFirst = new Card(CardType.CLUB, CardValue.KING);
        Card expectedSecond = new Card(CardType.SPADE, CardValue.QUEEN);

        List<Card> emptyCards = new ArrayList<>();
        DrawnCards drawnCards = new DrawnCards(emptyCards);

        Dealer dealer = new Dealer(drawnCards);
        // when
        dealer.drawCard(expectedFirst);
        dealer.drawCard(expectedSecond);
        List<Card> actual = drawnCards.getCards();
        // then
        assertThat(actual).containsExactly(expectedFirst, expectedSecond);
    }

    @DisplayName("딜러가 뽑은 카드의 점수 총합을 반환한다.")
    @Test
    void calculate_dealer_drawn_cards_score() {
        // given
        CardValue expectedA = CardValue.KING;
        CardValue expectedB = CardValue.QUEEN;

        Card cardA = new Card(CardType.CLUB, expectedA);
        Card cardB = new Card(CardType.SPADE, expectedB);

        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Dealer dealer = new Dealer(drawnCards);
        // when
        int actual = dealer.calculateScore();
        // then
        assertThat(actual).isEqualTo(expectedA.getScore() + expectedB.getScore());
    }

    @DisplayName("딜러가 뽑은 카드들을 반환하다.")
    @Test
    void returns_dealer_drawn_cards() {
        // given
        Card given = new Card(CardType.CLUB, CardValue.KING);
        Card expected = new Card(CardType.SPADE, CardValue.QUEEN);

        List<Card> givenCards = List.of(given, expected);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Dealer dealer = new Dealer(drawnCards);
        // when
        List<Card> actual = dealer.openDrawnCards();
        // then
        assertThat(actual).containsExactly(expected);
    }

    @DisplayName("BlackJack이라면 true를 반환한다.")
    @Test
    void is_blackJack() {
        // given
        Card cardA = new Card(CardType.SPADE, CardValue.ACE);
        Card cardB = new Card(CardType.SPADE, CardValue.TEN);

        DrawnCards blackJackCard = new DrawnCards(List.of(cardA, cardB));
        Dealer dealer = new Dealer(blackJackCard);
        // when
        boolean actual = dealer.isBlackJack();
        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점이 아니라면 블랙잭이 아니다.")
    @Test
    void is_not_blackJack_by_score() {
        // given
        Card cardA = new Card(CardType.SPADE, CardValue.TWO);
        Card cardB = new Card(CardType.SPADE, CardValue.TEN);

        DrawnCards notBlackJackCards = new DrawnCards(List.of(cardA, cardB));
        Dealer dealer = new Dealer(notBlackJackCards);
        // when
        boolean actual = dealer.isBlackJack();
        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("카드가 2장이 아니라면 블랙잭이 아니다.")
    @Test
    void is_not_blackJack_by_card_size() {
        // given
        Card cardA = new Card(CardType.SPADE, CardValue.TWO);
        Card cardB = new Card(CardType.SPADE, CardValue.TEN);
        Card cardC = new Card(CardType.SPADE, CardValue.NINE);

        DrawnCards notBlackJackCards = new DrawnCards(List.of(cardA, cardB, cardC));
        Dealer dealer = new Dealer(notBlackJackCards);
        // when
        boolean actual = dealer.isBlackJack();
        // then
        assertThat(actual).isFalse();
    }
}
