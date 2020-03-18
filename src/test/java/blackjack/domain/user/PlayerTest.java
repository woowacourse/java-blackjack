package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    Card card1;
    Card card2;
    List<Card> cards;
    UserCards initialCards;
    Player player;

    @BeforeEach
    void setUp() {
        card1 = new Card(Suit.CLUB, Symbol.SIX);
        card2 = new Card(Suit.HEART, Symbol.KING);
        cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        player = new Player("pobi");
    }

    @Test
    @DisplayName("사용자가 초기 카드를 받는 것을 테스트")
    void receiveInitialCardsTest() {
        player.receiveInitialCards(cards);
        assertThat(player.getCards()).containsExactly(card1, card2);
    }

    @Test
    @DisplayName("사용자가 카드를 한 장 새로 받는 것 테스트")
    void receiveCardTest() {
        player.receiveInitialCards(cards);
        player.receiveCard(new Card(Suit.DIAMOND, Symbol.TWO));
        assertThat(player.getTotalScore()).isEqualTo(18);
    }

    @Test
    @DisplayName("사용자의 UserCards의 합이 21을 초과하는 경우 busted인지 확인")
    void isBustedTest() {
        player.receiveInitialCards(cards);
        player.receiveCard(new Card(Suit.HEART, Symbol.SIX));
        assertThat(player.isBusted()).isTrue();
    }
}
