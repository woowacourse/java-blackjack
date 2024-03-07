package domain;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("딜러의 승패를 판단한다.")
    void checkDealerWin() {
        //given

        Hands sum18 = new Hands(List.of(new Card(CardNumber.EIGHT, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.DIAMOND))); // 18
        Hands sum21 = new Hands(
                List.of(new Card(CardNumber.JACK, CardShape.HEART), new Card(CardNumber.ACE, CardShape.SPADE)));

        Hands sum20 = new Hands(
                List.of(new Card(CardNumber.SEVEN, CardShape.SPADE), new Card(CardNumber.TWO, CardShape.SPADE),
                        new Card(CardNumber.ACE, CardShape.SPADE)));

        Player loser = new Player("레디", sum18);
        Player winner = new Player("제제", sum21);

        Players players = new Players(List.of(loser, winner));
        Dealer dealer = new Dealer(CardDeck.generate(), sum20);

        Game game = new Game(dealer, players);

        //when & then
        Map<Player, Result> expected = Map.of(loser, Result.LOSE, winner, Result.WIN);
        Assertions.assertThat(game.getResult()).isEqualTo(expected);
    }
}
