package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    Card card1;
    Card card2;
    UserCards userCards;
    Player player;

    @BeforeEach
    void setUp() {
        card1 = new Card(Suit.CLUB, Symbol.SIX);
        card2 = new Card(Suit.HEART, Symbol.KING);
        userCards = new UserCards(Arrays.asList(card1, card2));
        player = new Player("pobi", userCards);
    }

    @Test
    @DisplayName("사용자가 카드를 한 장 새로 받는 것 테스트")
    void receiveCard() {
        player.receiveCard(new Card(Suit.DIAMOND, Symbol.TWO));
        assertThat(player.getTotalScore()).isEqualTo(18);
    }

    @Test
    @DisplayName("사용자의 UserCards의 합이 21을 초과하는 경우 busted인지 확인")
    void isBusted() {
        player.receiveCard(new Card(Suit.HEART, Symbol.SIX));
        assertThat(player.isBusted()).isTrue();
    }

    @Test
    @DisplayName("사용자가 갖고 있는 카드 정보를 모두 출력하는 기능 확인")
    void displayPlayerCardInfo() {
        assertThat(player.showCardInfo()).isEqualTo("클럽 6, 하트 킹");
    }
}
