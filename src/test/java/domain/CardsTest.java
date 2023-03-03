package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("Cards 를 생성한다.")
    void createCardsSuccess() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.TWO), new Card(Shape.HEART, Letter.ACE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("Player 의 카드 점수를 계산한다.")
    void calculateCardScorePlayer() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.TWO), new Card(Shape.HEART, Letter.ACE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.calculateScore(21)).isEqualTo(13);
    }

    @Test
    @DisplayName("Player 의 Ace 를 2개 낮춰야 하는 경우 카드 점수를 계산한다.")
    void calculateCardScoreWhenAceDecreaseTwice() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.ACE), new Card(Shape.DIAMOND, Letter.JACK),new Card(Shape.HEART, Letter.ACE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.calculateScore(21)).isEqualTo(12);
    }

    @Test
    @DisplayName("Dealer 의 카드 점수를 계산한다.")
    void calculateCardScoreOfDealer() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.ACE), new Card(Shape.DIAMOND, Letter.NINE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.calculateScore(16)).isEqualTo(10);
    }

    @Test
    @DisplayName("블랙잭일 때 Dealer 의 카드 점수를 계산한다.")
    void calculateCardScoreOfDealerWhenBlackJack() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.ACE), new Card(Shape.DIAMOND, Letter.JACK));

        Cards cards = new Cards(initialCards);

        assertThat(cards.calculateScore(16)).isEqualTo(21);
    }

}
