package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.DrawnCards;
import domain.card.Type;
import domain.card.Value;
import domain.participants.Dealer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 카드를 뽑으면 그 카드가 뽑은 카드에 포함된다.")
    @Test
    void drawn_cards_contains_when_dealer_pick() {
        // given
        Card cardA = new Card(Type.CLUB, Value.KING);
        Card cardB = new Card(Type.SPADE, Value.QUEEN);

        List<Card> emptyCards = new ArrayList<>();
        DrawnCards drawnCards = new DrawnCards(emptyCards);

        Dealer dealer = new Dealer(drawnCards);

        // when
        dealer.pickCard(cardA);
        dealer.pickCard(cardB);

        // then
        assertThat(drawnCards.getCards()).containsExactly(cardA, cardB);
    }

    @DisplayName("딜러가 뽑은 카드의 점수 총합을 반환한다.")
    @Test
    void calculate_dealer_drawn_cards_score() {
        // given
        Value king = Value.KING;
        Value queen = Value.QUEEN;

        Card cardA = new Card(Type.CLUB, king);
        Card cardB = new Card(Type.SPADE, queen);

        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Dealer dealer = new Dealer(drawnCards);

        // when
        int expectedCardScore = dealer.calculateCardScore();

        // then
        assertThat(expectedCardScore).isEqualTo(king.getScore() + queen.getScore());
    }

    @DisplayName("딜가 뽑은 카드들을 반환하다.")
    @Test
    void returns_dealer_drawn_cards() {
        // given
        Card cardA = new Card(Type.CLUB, Value.KING);
        Card cardB = new Card(Type.SPADE, Value.QUEEN);

        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Dealer dealer = new Dealer(drawnCards);

        // when
        List<Card> expectedDrawnCards = dealer.openDrawnCards();

        // then
        assertThat(expectedDrawnCards).containsExactly(cardB);
    }

    @Test
    @DisplayName("딜러 우승시 상금을 얻는다.")
    void earn_winning_price_when_dealer_win_game() {
        // given
        Dealer dealer = new Dealer(new DrawnCards(new ArrayList<>()));
        int winningPrice = 1000;

        // when
        dealer.winGame(winningPrice);

        // then
        assertThat(dealer.getAccount()).isEqualTo(winningPrice);
    }

    @Test
    @DisplayName("딜러 패배시 플레이어의 상금만큼 돈을 잃는다.")
    void lose_money_when_dealer_lose_game() {
        // given
        Dealer dealer = new Dealer(new DrawnCards(new ArrayList<>()));
        int playerAccount = 1000;

        // when
        dealer.loseGame(playerAccount);

        // then
        assertThat(dealer.getAccount()).isEqualTo(-500);
    }
}
