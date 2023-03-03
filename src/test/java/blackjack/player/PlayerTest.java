package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Pattern;
import player.Name;
import player.Player;

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
        Card card = new Card(CardNumber.ACE, Pattern.HEART);

        assertThatCode(() -> player.hit(card))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어는 받은 카드의 점수 합계를 구할 수 있다")
    void calculateScore() {
        Player player = new Player(new Name("폴로"));
        Card card1 = new Card(CardNumber.ACE, Pattern.HEART);
        Card card2 = new Card(CardNumber.EIGHT, Pattern.HEART);
        Card card3 = new Card(CardNumber.SIX, Pattern.HEART);
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThat(player.calculateScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("플레이어는 현재 가지고 있는 카드를 반환할수 있다.")
    void showCards() {
        Player player = new Player(new Name("폴로"));
        Card card1 = new Card(CardNumber.ACE, Pattern.HEART);
        Card card2 = new Card(CardNumber.EIGHT, Pattern.HEART);
        Card card3 = new Card(CardNumber.SIX, Pattern.HEART);
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThat(player.showCards()).contains(card1, card2, card3);
    }
}
