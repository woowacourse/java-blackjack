package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardDeckTest {
    @Test
    @DisplayName("카드덱을 생성하면 카드 타입과 값 별로 52장의 카드가 만들어진다")
    void generateCardDeck() {

        CardDeck cardDeck = CardDeck.generateCardDeck();

        assertThat(cardDeck.getCardDeck().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("pick 메서드를 실행하면 카드덱 스택에서 맨 뒤의 카드가 제거된 후 출력된다")
    void pickCard() {

        CardDeck cardDeck = CardDeck.generateCardDeck();

        Card canPoppedCard = cardDeck.getCardDeck().peek();
        Card poppedCard = cardDeck.pickCard();
        System.out.println(canPoppedCard);
        System.out.println(poppedCard);

        assertAll(
                () -> assertThat(poppedCard).isSameAs(canPoppedCard),
                () -> assertThat(cardDeck.getCardDeck()).doesNotContain(poppedCard)
        );
    }

//
//    @Test
//    @DisplayName("카드 덱에서 카드를 뽑는다")
//    void pickCard() {
//        //given
//        CardDeck cardDeck = new CardDeck();
//
//        //when
//        Suit[] suits = Suit.values();
//        Suit lastSuit = suits[suits.length - 1];
//
//        Number[] numbers = Number.values();
//        Number lastNumber = numbers[numbers.length - 1];
//
//        //then
//        Assertions.assertThat(cardDeck.pick())
//            .isEqualTo(new Card(lastSuit, lastNumber));
//    }
}
