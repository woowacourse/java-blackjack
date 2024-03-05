package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("게임을 시작하면 딜러와 플레이어에게 카드를 나누어준다.")
    @Test
    void play() {
        BlackJackGame game = new BlackJackGame();
        Cards cards = new Cards(
                List.of(
                        new Card(CardShape.SPADE, CardNumber.THREE),
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.SPADE, CardNumber.EIGHT),
                        new Card(CardShape.CLOVER, CardNumber.SEVEN)
                )
        );
        Dealer dealer = new Dealer(cards);
        Player player = new Player(new Name("hotea"));
        Players players = new Players(List.of(player));

        game.play(dealer, players);

        assertAll(
                () -> assertThat(dealer.sumCards()).isEqualTo(15),
                () -> assertThat(player.sumCards()).isEqualTo(5)
        );
    }
}
