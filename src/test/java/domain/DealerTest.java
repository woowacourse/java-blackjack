package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.card.Cards;
import domain.player.Dealer;
import domain.player.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("16을 기준으로 카드를 받는지 결정하는 메서드")
    @Test
    void insertCardTest() {
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card3 = Card.of(CardNumber.TEN, CardSuitSymbol.CLUB);
        User dealer = new Dealer(card1, card2);
        Cards cards = new Cards();
        dealer.drawCard(card3);

        Assertions.assertThat(dealer).extracting("cards").asList().size().isEqualTo(3);
    }

    @DisplayName("중복된 카드가 주어지면 에러")
    @Test
    void validateDuplicateCard() {
        Card card1 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);

        Assertions.assertThatThrownBy(() -> new Dealer(card1, card2)).isInstanceOf(IllegalArgumentException.class);
    }
}
