package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import blackjack.domain.game.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    Card cardClubSix, cardHeartKing, cardDiamondAce;
    List<Card> initialCards;
    Player player;

    @BeforeEach
    void setUp() {
        cardClubSix = new Card(Suit.CLUB, Symbol.SIX);
        cardHeartKing = new Card(Suit.HEART, Symbol.KING);
        cardDiamondAce = new Card(Suit.DIAMOND, Symbol.ACE);
        initialCards = Arrays.asList(cardClubSix, cardHeartKing);
        player = new Player("pobi", 10000);
        player.receiveInitialCards(initialCards);
    }

    @Test
    @DisplayName("사용자가 초기 카드를 받는 것을 테스트")
    void receiveInitialCardsTest() {
        assertThat(player.getCards()).isEqualTo(Arrays.asList(cardClubSix, cardHeartKing));
    }

    @Test
    @DisplayName("사용자가 카드를 한 장 새로 받는 것 테스트")
    void receiveCardTest() {
        player.receiveCard(new Card(Suit.DIAMOND, Symbol.TWO));
        assertThat(player.getTotalScore()).isEqualTo(18);
    }

    @Test
    @DisplayName("사용자의 UserCards의 합이 21을 초과하는 경우 busted인지 확인")
    void isBustedTest() {
        player.receiveCard(new Card(Suit.HEART, Symbol.SIX));
        assertThat(player.isBusted()).isTrue();
    }

    @Test
    @DisplayName("사용자가 처음 받은 카드 두장을 출력하는 기능 확인")
    void displayPlayerInitialCardInfoTest() {
        assertThat(player.getInitialCards()).isEqualTo(Arrays.asList(cardClubSix, cardHeartKing));
    }

    @Test
    @DisplayName("사용자가 갖고 있는 카드 정보를 모두 출력하는 기능 확인")
    void displayPlayerCardInfoTest() {
        player.receiveCard(cardDiamondAce);
        assertThat(player.getCards()).isEqualTo(Arrays.asList(cardClubSix, cardHeartKing, cardDiamondAce));
    }

    @Test
    @DisplayName("사용자의 최종 수익 확인")
    void checkFinalProfit() {
        assertThat(player.getProfit(Result.BLACKJACK)).isEqualTo(new Money(15000));
    }
}
