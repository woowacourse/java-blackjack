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
    Card clubSix, heartKing, diamondAce;
    List<Card> initialCards;
    Player pobi;

    @BeforeEach
    void setUp() {
        clubSix = new Card(Suit.CLUB, Symbol.SIX);
        heartKing = new Card(Suit.HEART, Symbol.KING);
        diamondAce = new Card(Suit.DIAMOND, Symbol.ACE);
        initialCards = Arrays.asList(clubSix, heartKing);
        pobi = new Player("pobi", 10000);
        pobi.receiveInitialCards(initialCards);
    }

    @Test
    @DisplayName("사용자가 초기 카드를 받는 것을 테스트")
    void receiveInitialCardsTest() {
        assertThat(pobi.getCards()).isEqualTo(Arrays.asList(clubSix, heartKing));
    }

    @Test
    @DisplayName("사용자가 카드를 한 장 새로 받는 것 테스트")
    void receiveCardTest() {
        pobi.receiveCard(new Card(Suit.DIAMOND, Symbol.TWO));
        assertThat(pobi.getTotalScore()).isEqualTo(18);
    }

    @Test
    @DisplayName("사용자의 UserCards의 합이 21을 초과하는 경우 busted인지 확인")
    void isBustedTest() {
        pobi.receiveCard(new Card(Suit.HEART, Symbol.SIX));
        assertThat(pobi.isBusted()).isTrue();
    }

    @Test
    @DisplayName("사용자가 처음 받은 카드 두장을 출력하는 기능 확인")
    void displayPlayerInitialCardInfoTest() {
        assertThat(pobi.getInitialCards()).isEqualTo(Arrays.asList(clubSix, heartKing));
    }

    @Test
    @DisplayName("사용자가 갖고 있는 카드 정보를 모두 출력하는 기능 확인")
    void displayPlayerCardInfoTest() {
        pobi.receiveCard(diamondAce);
        assertThat(pobi.getCards()).isEqualTo(Arrays.asList(clubSix, heartKing, diamondAce));
    }

    @Test
    @DisplayName("사용자의 최종 수익 확인")
    void checkFinalProfit() {
        assertThat(pobi.getProfit(Result.BLACKJACK)).isEqualTo(new Money(15000));
    }
}
