package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("Cards 를 생성한다.")
    void createCardsSuccess() {
        List<Card> initialCards = List.of(Card.of(Shape.DIAMOND, Letter.TWO), Card.of(Shape.HEART, Letter.ACE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("Player 의 카드 점수를 계산한다.")
    void calculateCardScorePlayer() {
        List<Card> initialCards = List.of(Card.of(Shape.DIAMOND, Letter.TWO), Card.of(Shape.HEART, Letter.ACE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.getPlayerScore().getValue()).isEqualTo(13);
    }

    @Test
    @DisplayName("Player 의 Ace 를 2개 낮춰야 하는 경우 카드 점수를 계산한다.")
    void calculateCardScoreWhenAceDecreaseTwice() {
        List<Card> initialCards = List.of(Card.of(Shape.DIAMOND, Letter.ACE), Card.of(Shape.DIAMOND, Letter.JACK), Card.of(Shape.HEART, Letter.ACE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.getPlayerScore().getValue()).isEqualTo(12);
    }

    @Test
    @DisplayName("Dealer 의 카드 점수를 계산한다.")
    void calculateCardScoreOfDealer() {
        List<Card> initialCards = List.of(Card.of(Shape.DIAMOND, Letter.ACE), Card.of(Shape.DIAMOND, Letter.NINE));

        Cards cards = new Cards(initialCards);

        assertThat(cards.getDealerScore().getValue()).isEqualTo(10);
    }

    @Test
    @DisplayName("블랙잭일 때 Dealer 의 카드 점수를 계산한다.")
    void calculateCardScoreOfDealerWhenBlackJack() {
        List<Card> initialCards = List.of(Card.of(Shape.DIAMOND, Letter.ACE), Card.of(Shape.DIAMOND, Letter.JACK));

        Cards cards = new Cards(initialCards);

        assertThat(cards.getDealerScore().getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드의 마지막 요소를 반환한다.")
    void getCard() {
        Card inputCard = Card.of(Shape.DIAMOND, Letter.ACE);
        List<Card> initialCards = List.of(inputCard);
        Cards cards = new Cards(initialCards);

        Card card = cards.getCard();

        assertThat(card).isEqualTo(inputCard);
    }

    @Test
    @DisplayName("카드의 마지막 요소를 삭제한다.")
    void removeCard() {
        Card inputCard = Card.of(Shape.DIAMOND, Letter.ACE);
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(inputCard);
        Cards cards = new Cards(initialCards);

        cards.removeCard();

        assertThat(cards.getSize()).isEqualTo(0);
    }

}
