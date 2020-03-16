package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class CardsTest {
    @DisplayName("입력한 카드 묶음이 생성되는지 테스트")
    @Test
    void ofTest() {
        Cards cards = Cards.of(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.DIAMOND));

        Assertions.assertThat(cards).isInstanceOf(Cards.class);
        String cardsString = cards.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
        Assertions.assertThat(cardsString).isEqualTo(
                "1클로버, 10다이아몬드"
        );
    }

    @DisplayName("16을 초과하는지 판단하는 메서드 테스트")
    @Test
    void isCardsSumUnderSixteenTest() {
        Cards overSixteen = Cards.of(Card.of(CardNumber.KING, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB));
        Cards underSixteen = Cards.of(Card.of(CardNumber.KING, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SIX, CardSuitSymbol.CLUB));

        Assertions.assertThat(overSixteen.isCardsSumUnderSixteen()).isFalse();
        Assertions.assertThat(underSixteen.isCardsSumUnderSixteen()).isTrue();
    }

    @DisplayName("Cards 가 ace 를 갖고 있는지 판단하는 메서드 테스트")
    @Test
    void containAceTest() {
        Cards containAceCards = Cards.of(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB));
        Cards notContainAceCards = Cards.of(Card.of(CardNumber.KING, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB));

        Assertions.assertThat(containAceCards.containAce()).isTrue();
        Assertions.assertThat(notContainAceCards.containAce()).isFalse();
    }

    @DisplayName("입력한 카드를 더하는지 테스트")
    @Test
    void addCardTest() {
        Cards cards = Cards.of(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB));
        Card card = Card.of(CardNumber.JACK, CardSuitSymbol.SPACE);
        cards.addCard(card);

        Assertions.assertThat(cards.getCards().size()).isEqualTo(3);
        String cardsString = cards.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
        Assertions.assertThat(cardsString).isEqualTo(
                "1클로버, 7클로버, 10스페이스"
        );
    }

    @DisplayName("현재 카드 묶음이 블랙잭인지 테스트")
    @Test
    void isBlackJackTest() {
        Cards blackJack = Cards.of(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB));
        Cards notBlackJack = Cards.of(Card.of(CardNumber.KING, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB),
                Card.of(CardNumber.ACE, CardSuitSymbol.DIAMOND));

        Assertions.assertThat(blackJack.isBlackJack()).isTrue();
        Assertions.assertThat(notBlackJack.isBlackJack()).isFalse();
    }
}
