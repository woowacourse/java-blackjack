package domain.participant.player;

import static org.assertj.core.api.Assertions.assertThat;

import static domain.card.Suit.HEART;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

class PlayerTest {

    @DisplayName("참가자는 카드의 합을 구할 수 있다.")
    @Test
    void cardSum() {
        Player player = PlayerFixture.from("zeus");
        player.receive(new Card(Suit.SPADE, Rank.JACK));
        assertThat(player.score())
                .isEqualTo(10);
    }

    @DisplayName("카드의 합계로 `Bust`를 판단한다.")
    @Test
    void isBust() {
        Card cardTwo = new Card(Suit.SPADE, Rank.TWO);
        Card cardKing = new Card(HEART, Rank.KING);
        Card cardQueen = new Card(HEART, Rank.QUEEN);
        Player player = PlayerFixture.from("hotea");
        player.receive(cardTwo);
        player.receive(cardKing);
        player.receive(cardQueen);
        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("카드의 합계로 `Blackjack`를 판단한다.")
    @Test
    void isBlackjack() {
        Card cardAce = new Card(Suit.SPADE, Rank.ACE);
        Card cardKing = new Card(HEART, Rank.KING);
        Card cardQueen = new Card(HEART, Rank.QUEEN);
        Player player = PlayerFixture.from("hotea");
        player.receive(cardAce);
        player.receive(cardKing);
        player.receive(cardQueen);
        assertThat(player.isBlackjack()).isTrue();
    }
}
