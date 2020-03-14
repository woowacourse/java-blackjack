package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("중복된 카드가 주어지면 에러")
    @Test
    void validateDuplicateCard() {
        Card card1 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);

        Assertions.assertThatThrownBy(() -> new Dealer(card1, card2)).isInstanceOf(IllegalArgumentException.class);
    }
}