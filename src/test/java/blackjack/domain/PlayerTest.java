package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    @DisplayName("히트 - 플레이어는 카드를 받는다.")
    @Test
    void hitCard() {
        Player player = new Player();
        CardDeck cardDeck = CardDeck.createDeck();
        player.hit(cardDeck.getDeck().pop());
        assertThat(player.getCards()).hasSize(1);
    }

    @DisplayName("isStay:false - 플레이어가 스테이 의사를 밝히지 않으면 계속 게임 진행")
    @Test
    void isStayFalse() {
        Player player = new Player();
        assertFalse(player.isStay());
    }

    @DisplayName("isStay:true - 플레이어가 스테이 의사를 밝히면 해당 플레이어는 게임 중단")
    @Test
    void isStayTrue() {
        Player player = new Player();
        player.stay();
        assertTrue(player.isStay());
    }

    @DisplayName("isStay:true - 플레이어 카드의 총합이 21을 초과하는 경우 자동 중단")
    @Test
    void isStayTrueWhenBust() {
        Player player = new Player();

        Card card1 = new Card(Suit.CLUB, CardNumber.JACK);
        Card card2 = new Card(Suit.CLUB, CardNumber.SEVEN);
        Card card3 = new Card(Suit.CLUB, CardNumber.SIX);

        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertTrue(player.isStay());
    }

}