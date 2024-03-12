package domain.game;

import domain.BetAmount;
import domain.Name;
import domain.card.Card;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.card.Shape;
import domain.score.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class RuleTest {

    @Test
    @DisplayName("플레이어가 이긴 경우 승을 반환한다.")
    void decideStatus_WIN() {
        DealerCards dealerCards = new DealerCards(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        PlayerCards playerCards = new PlayerCards(
                new Name("capy"), new BetAmount(1000), List.of(new Card(7, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(playerCards, dealerCards)).isEqualTo(Status.WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 블랙잭을 반환한다.")
    void decideStatus_BLACKJACK() {
        DealerCards dealerCards = new DealerCards(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        PlayerCards playerCards = new PlayerCards(
                new Name("capy"), new BetAmount(1000), List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(playerCards, dealerCards)).isEqualTo(Status.WIN_BLACKJACK);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 비김을 반환한다.")
    void decideStatus_BLACKJACK_TIE() {
        DealerCards dealerCards = new DealerCards(List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));
        PlayerCards playerCards = new PlayerCards(
                new Name("capy"), new BetAmount(1000), List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(playerCards, dealerCards)).isEqualTo(Status.TIE);
    }

    @Test
    @DisplayName("플레이어가 비긴 경우 비김을 반환한다.")
    void decideStatus_TIE() {
        DealerCards dealerCards = new DealerCards(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        PlayerCards playerCards = new PlayerCards(
                new Name("capy"), new BetAmount(1000), List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(playerCards, dealerCards)).isEqualTo(Status.TIE);
    }

    @Test
    @DisplayName("플레이어가 진 경우 패을 반환한다.")
    void decideStatus_LOSE() {
        DealerCards dealerCards = new DealerCards(List.of(new Card(7, Shape.CLUB), new Card(10, Shape.HEART)));
        PlayerCards playerCards = new PlayerCards(
                new Name("capy"), new BetAmount(1000), List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(playerCards, dealerCards)).isEqualTo(Status.LOSE);
    }
}
