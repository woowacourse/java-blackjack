package domain;

import static domain.Result.LOSE;
import static domain.Result.TIE;
import static domain.Result.WIN;
import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.JACK;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.TEN;
import static domain.card.Rank.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    final Hands sum18 = new Hands(List.of(new Card(EIGHT, CLOVER), new Card(TEN, DIAMOND)));
    final Hands sum20 = new Hands(List.of(new Card(SEVEN, SPADE), new Card(TWO, SPADE), new Card(ACE, SPADE)));
    final Hands sum21 = new Hands(List.of(new Card(JACK, HEART), new Card(ACE, SPADE)));
    final Hands bustHands = new Hands(List.of(new Card(EIGHT, DIAMOND), new Card(TWO, DIAMOND),
            new Card(TWO, DIAMOND), new Card(JACK, CLOVER)));


    @Test
    @DisplayName("참여자의 승패무를 판단한다.")
    void playerResult() {
        //given
        Player loser = new Player("레디", sum18);
        Player winner = new Player("제제", sum21);
        Player tier = new Player("수달", sum20);

        Players players = new Players(List.of(loser, winner, tier));
        Dealer dealer = new Dealer(CardDeck.generate(), sum20);

        Game game = new Game(dealer, players);

        //when & then
        Map<Player, Result> expected = Map.of(loser, LOSE, winner, WIN, tier, TIE);
        Assertions.assertThat(game.getPlayersResult()).isEqualTo(expected);
    }

    @DisplayName("딜러의 승패무를 판단한다.")
    @Test
    void dealerResult() {
        // given
        Player loser1 = new Player("레디", sum18);
        Player loser2 = new Player("피케이", sum18);
        Player winner = new Player("제제", sum21);
        Player tier = new Player("브라운", sum20);

        Players players = new Players(List.of(loser1, loser2, winner, tier));
        Dealer dealer = new Dealer(CardDeck.generate(), sum20);

        Game game = new Game(dealer, players);

        // when
        Map<Result, Integer> expected = Map.of(WIN, 2, LOSE, 1, TIE, 1);

        // then
        Assertions.assertThat(game.getDealerResult()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 버스트일때 참여자가 버스트가 아니면 WIN")
    void all() {
        //given
        Dealer bustDealer = new Dealer(CardDeck.generate(), bustHands);
        Player winner1 = new Player("레디", sum18);
        Player winner2 = new Player("브라운", sum20);
        Player loser = new Player("제제", bustHands);

        Players players = new Players(List.of(winner1, winner2, loser));

        //when
        Game game = new Game(bustDealer, players);

        Map<Player, Result> expectedPlayerResult = Map.of(winner1, WIN, winner2, WIN, loser, LOSE);
        Map<Result, Integer> expectedDealerResult = Map.of(WIN, 1, LOSE, 2);

        //then
        Assertions.assertThat(game.getPlayersResult()).isEqualTo(expectedPlayerResult);
        Assertions.assertThat(game.getDealerResult()).isEqualTo(expectedDealerResult);
    }
}
