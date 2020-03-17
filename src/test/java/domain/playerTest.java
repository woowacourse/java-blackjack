package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.card.Cards;
import domain.player.Player;
import domain.player.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class playerTest {

    @DisplayName(" 카드를 받는지 결정하는 메서드")
    @Test
    void yes_insertCard() {
        Cards cards = new Cards();
        Player player = new Player("pobi", cards.giveTwoCardStartGame());
        player.drawCard(cards.giveCard());

        Assertions.assertThat(player).extracting("cards").asList().size().isEqualTo(3);
    }

    @DisplayName("중복된 카드가 주어지면 에러")
    @Test
    void validateDuplicateCard() {
        Card card1 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card2 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        List<Card> cards = new ArrayList<>(Arrays.asList(card1,card2));

        Assertions.assertThatThrownBy(() -> new Player("pobi", cards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 카드가 주어지면 에러")
    @Test
    void insertValidateDuplicateCard() {
        Card card1 = new Card(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card2 = new Card(CardNumber.FOUR, CardSuitSymbol.CLUB);
        Card card3 = new Card(CardNumber.FOUR, CardSuitSymbol.CLUB);
        List<Card> cards = new ArrayList<>(Arrays.asList(card1,card2));
        User user = new Player("pobi", cards);

        Assertions.assertThatThrownBy(() -> user.drawCard(card3))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
