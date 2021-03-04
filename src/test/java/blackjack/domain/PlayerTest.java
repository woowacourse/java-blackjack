package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    @DisplayName("플레이어는 이름을 받아 생성된다.")
    @Test
    void playerCreationTest() {
        Player player = new Player("youngE");
        assertThat(player).isInstanceOf(Player.class);
    }

    @DisplayName("플레이어 이름에 공백이 입력되는 경우 예외를 발생시킨다.")
    @Test
    void playerCreationErrorTest() {
        assertThatThrownBy(() -> {
            new Player(" ");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("히트 - 플레이어는 카드를 받는다.")
    @Test
    void hitCard() {
        Player player = new Player("Player");
        CardDeck cardDeck = CardDeck.createDeck();
        player.hit(cardDeck.drawCard());
        assertThat(player.getCards()).hasSize(1);
    }

    @DisplayName("isStay:false - 플레이어가 스테이 의사를 밝히지 않으면 계속 게임 진행")
    @Test
    void isStayFalse() {
        Player player = new Player("Player");
        assertFalse(player.isStay());
    }

    @DisplayName("isStay:true - 플레이어가 스테이 의사를 밝히면 해당 플레이어는 게임 중단")
    @Test
    void isStayTrue() {
        Player player = new Player("Player");
        player.stay();
        assertTrue(player.isStay());
    }

    @DisplayName("isStay:true - 플레이어 카드의 총합이 21을 초과하는 경우 자동 중단")
    @Test
    void isStayTrueWhenBust() {
        Player player = new Player("Player");

        Card card1 = new Card(Suit.CLUB, CardNumber.JACK);
        Card card2 = new Card(Suit.CLUB, CardNumber.SEVEN);
        Card card3 = new Card(Suit.CLUB, CardNumber.SIX);

        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertTrue(player.isStay());
    }

}