package domain.participant.player;

import static org.assertj.core.api.Assertions.assertThat;

import static domain.card.CardSuit.HEART;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;

class PlayerTest {

    @DisplayName("참가자는 카드의 합을 구할 수 있다.")
    @Test
    void cardSum() {
        Player player = new Player("zeus");
        player.receive(new Card(CardSuit.SPADE, CardRank.JACK));
        assertThat(player.score())
                .isEqualTo(10);
    }

    @DisplayName("카드의 합계로 `Bust`를 판단한다.")
    @Test
    void isBust() {
        Card cardTwo = new Card(CardSuit.SPADE, CardRank.TWO);
        Card cardKing = new Card(HEART, CardRank.KING);
        Card cardQueen = new Card(HEART, CardRank.QUEEN);
        Player player = new Player("hotea");
        player.receive(cardTwo);
        player.receive(cardKing);
        player.receive(cardQueen);
        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("카드의 합계로 `Blackjack`를 판단한다.")
    @Test
    void isBlackjack() {
        Card cardAce = new Card(CardSuit.SPADE, CardRank.ACE);
        Card cardKing = new Card(HEART, CardRank.KING);
        Card cardQueen = new Card(HEART, CardRank.QUEEN);
        Player player = new Player("hotea");
        player.receive(cardAce);
        player.receive(cardKing);
        player.receive(cardQueen);
        assertThat(player.isBlackjack()).isTrue();
    }
}
