package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DealerTest {
    @DisplayName("16을 기준으로 카드를 받는지 결정하는 메서드")
    @Test
    void insertCardTest() {
        Card firstCard = new Card(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card secondCard = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card drawCard = new Card(CardNumber.KING, CardSuitSymbol.CLUB);
        List<Card> cards = new ArrayList<>(Arrays.asList(firstCard,secondCard));
        User dealer = new Dealer(cards);
        dealer.drawCard(drawCard);

        Assertions.assertThat(dealer).extracting("cards").asList().size().isEqualTo(3);
    }

    @DisplayName("중복된 카드가 주어지면 에러")
    @Test
    void validateDuplicateCard() {
        Card firstCard = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card secondCard = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        List<Card> cards = new ArrayList<>(Arrays.asList(firstCard,secondCard));

        Assertions.assertThatThrownBy(() -> new Dealer(cards)).isInstanceOf(IllegalArgumentException.class);
    }
}
