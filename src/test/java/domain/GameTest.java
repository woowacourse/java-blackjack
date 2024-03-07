package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static domain.Result.*;

import java.util.List;
import java.util.Map;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("참여자의 승패무를 판단한다.")
    void playerResult() {
        //given
        Hands sum18 = new Hands(List.of(new Card(EIGHT, CLOVER),
                new Card(TEN, DIAMOND))); // 18
        Hands sum21 = new Hands(
                List.of(new Card(JACK, HEART), new Card(ACE, SPADE)));

        Hands sum20 = new Hands(
                List.of(new Card(SEVEN, SPADE), new Card(TWO, SPADE),
                        new Card(ACE, SPADE)));

        Participant loser = new Participant("레디", sum18);
        Participant winner = new Participant("제제", sum21);
        Participant tier = new Participant("수달", sum20);

        Players players = new Players(List.of(loser, winner, tier));
        Dealer dealer = new Dealer(CardDeck.generate(), sum20);

        Game game = new Game(dealer, players);

        //when & then
        Map<Participant, Result> expected = Map.of(loser, LOSE, winner, WIN, tier, TIE);
        Assertions.assertThat(game.getPlayersResult()).isEqualTo(expected);
    }

    @DisplayName("딜러의 승패무를 판단한다.")
    @Test
    void dealerResult() {
        // given
        Hands sum18 = new Hands(List.of(new Card(EIGHT, CLOVER),
                new Card(TEN, DIAMOND))); // 18

        Hands sum21 = new Hands(
                List.of(new Card(JACK, HEART), new Card(ACE, SPADE)));

        Hands sum20 = new Hands(
                List.of(new Card(SEVEN, SPADE), new Card(TWO, SPADE),
                        new Card(ACE, SPADE)));

        Participant loser1 = new Participant("레디", sum18);
        Participant loser2 = new Participant("피케이", sum18);
        Participant winner = new Participant("제제", sum21);
        Participant tier = new Participant("브라운", sum20);

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
        Hands dealerBustHands = new Hands(
                List.of(new Card(EIGHT, DIAMOND), new Card(TWO, DIAMOND), new Card(TWO, DIAMOND),
                        new Card(JACK, CLOVER)));
        Dealer bustDealer = new Dealer(CardDeck.generate(), dealerBustHands);

        Hands winner1PlayerHandsSum19 = new Hands(
                List.of(new Card(FIVE, DIAMOND), new Card(THREE, CLOVER), new Card(SEVEN, HEART),
                        new Card(FOUR, DIAMOND))
        );

        Participant winner1 = new Participant("레디", winner1PlayerHandsSum19);

        Hands winner2PlayerHandsSum20 = new Hands(
                List.of(new Card(QUEEN, CLOVER), new Card(KING, SPADE))
        );
        Participant winner2 = new Participant("브라운", winner2PlayerHandsSum20);

        Hands loserPlayerHandsSum22 = new Hands(
                List.of(new Card(SEVEN, CLOVER), new Card(QUEEN, HEART), new Card(FIVE, SPADE))
        );
        Participant loser = new Participant("제제", loserPlayerHandsSum22);

        Players players = new Players(List.of(winner1, winner2, loser));

        //when
        Game game = new Game(bustDealer, players);

        Map<Participant, Result> expectedPlayerResult = Map.of(winner1, WIN, winner2, WIN, loser, LOSE);
        Map<Result, Integer> expectedDealerResult = Map.of(WIN, 1, LOSE, 2);

        //then
        Assertions.assertThat(game.getPlayersResult()).isEqualTo(expectedPlayerResult);
        Assertions.assertThat(game.getDealerResult()).isEqualTo(expectedDealerResult);
    }
}
