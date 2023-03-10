package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.Rank;
import card.Suit;
import participants.Name;
import participants.Player;

class PlayerTest {
    @Test
    @DisplayName("플레이어를 생성한다.")
    void createPlayer() {
        Name name = new Name("폴로");

        assertThatCode(() -> new Player(name))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어는 카드를 받을 수 있다.")
    void hit() {
        Player player = new Player(new Name("폴로"));
        Card card = new Card(Rank.TWO, Suit.HEART);

        player.hit(card);

        assertThat(player.calculateScore().getScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어는 받은 카드의 점수 합계를 계산한다.")
    void calculateScore() {
        Player player = new Player(new Name("폴로"));
        Card card1 = new Card(Rank.ACE, Suit.HEART);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        Card card3 = new Card(Rank.SIX, Suit.HEART);
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThat(player.calculateScore().getScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("플레이어는 현재 가지고 있는 카드를 반환한다.")
    void showCards() {
        Player player = new Player(new Name("폴로"));
        Card card1 = new Card(Rank.ACE, Suit.HEART);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        Card card3 = new Card(Rank.SIX, Suit.HEART);
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThat(player.showCards()).contains(card1, card2, card3);
    }

    @Test
    @DisplayName("플레이어는 버스트인 경우 true를 반환한다.")
    void isBust() {
        Player player = new Player(new Name("폴로"));
        player.hit(new Card(Rank.KING, Suit.HEART));
        player.hit(new Card(Rank.KING, Suit.DIAMOND));
        player.hit(new Card(Rank.KING, Suit.SPADE));

        assertThat(player.isBust()).isTrue();
    }


    @Test
    @DisplayName("플레이어가 blackjack인 경우 true를 반환한다")
    void isBlackJack() {
        Player player = new Player(new Name("폴로"));
        player.hit(new Card(Rank.KING, Suit.HEART));
        player.hit(new Card(Rank.ACE, Suit.HEART));

        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 blackjack인 경우 false를 반환한다")
    void isBlackJackFalse() {
        Player player = new Player(new Name("폴로"));
        player.hit(new Card(Rank.KING, Suit.HEART));
        player.hit(new Card(Rank.KING, Suit.SPADE));
        player.hit(new Card(Rank.ACE, Suit.HEART));

        assertThat(player.isBlackjack()).isFalse();
    }
}
