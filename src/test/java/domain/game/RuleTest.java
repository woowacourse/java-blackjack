package domain.game;

import domain.card.Card;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.card.Shape;
import domain.player.Name;
import domain.score.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RuleTest {

    private Name capy = new Name("capy");
    private Rule rule = new Rule();

    @Test
    @DisplayName("딜러가 처음 뽑은 2장 합이 21이라면 블랙잭이다.")
    void decideDealerStatus_21_BlackJack() {
        DealerCards dealerCards = new DealerCards(List.of(
                new Card(1, Shape.HEART),
                new Card(10, Shape.CLUB)
        ));

        Status dealerStatus = rule.decideDealerStatus(dealerCards);

        assertThat(dealerStatus).isEqualTo(Status.BLACKJACK);
    }

    @Test
    @DisplayName("딜러 카드의 합이 21을 초과하면 패배한다.")
    void decideDealerStatus_over21_Lose() {
        DealerCards dealerCards = new DealerCards(List.of(
                new Card(10, Shape.HEART),
                new Card(10, Shape.CLUB)
        ));
        dealerCards.receive(new Card(2, Shape.CLUB));
        Status dealerStatus = rule.decideDealerStatus(dealerCards);

        assertThat(dealerStatus).isEqualTo(Status.LOSE);
    }

    @Test
    @DisplayName("딜러 카드가 21이상이 아니라면 무승부다.")
    void decideDealerStatus_under21_Tie() {
        DealerCards dealerCards = new DealerCards(List.of(
                new Card(10, Shape.HEART),
                new Card(10, Shape.CLUB)
        ));
        Status dealerStatus = rule.decideDealerStatus(dealerCards);

        assertThat(dealerStatus).isEqualTo(Status.TIE);
    }

    @Test
    @DisplayName("딜러가 블랙잭이라면 플레이어의 블랙잭은 무승부와 같다.")
    void decidePlayerBlackjackStatus_blackjack_Tie() {
        Status playerBlackjackStatus = rule.decidePlayerBlackjackStatus(Status.BLACKJACK);

        assertThat(playerBlackjackStatus).isEqualTo(Status.TIE);
    }

    @ParameterizedTest
    @EnumSource(names = {"TIE", "LOSE"})
    @DisplayName("딜러가 블랙잭이 아니라면 플레이어의 블랙잭은 블랙잭이다.")
    void decidePlayerBlackjackStatus_NotBlackjack_blackjack(Status status) {
        Status playerBlackjackStatus = rule.decidePlayerBlackjackStatus(status);

        assertThat(playerBlackjackStatus).isEqualTo(Status.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어 카드가 딜러 카드의 합과 같다면 무승부다.")
    void decideDealerStatus_Tie() {
        PlayerCards playerCards = new PlayerCards(capy, List.of(
                new Card(10, Shape.HEART),
                new Card(10, Shape.CLUB)
        ));

        Status playerStatus = rule.decidePlayerStatus(20, playerCards);

        assertThat(playerStatus).isEqualTo(Status.TIE);
    }

    @Test
    @DisplayName("플레이어 카드가 딜러 카드의 합보다 크다면 승리다.")
    void decidePlayerStatus_BlackJack() {
        PlayerCards playerCards = new PlayerCards(capy, List.of(
                new Card(8, Shape.HEART),
                new Card(10, Shape.CLUB)
        ));

        Status playerStatus = rule.decidePlayerStatus(17, playerCards);

        assertThat(playerStatus).isEqualTo(Status.WIN);
    }

    @Test
    @DisplayName("플레이어 카드가 딜러 카드의 합보다 작다면 패배다.")
    void decidePlayerStatus_Win() {
        PlayerCards playerCards = new PlayerCards(capy, List.of(
                new Card(9, Shape.HEART),
                new Card(10, Shape.CLUB)
        ));

        Status playerStatus = rule.decidePlayerStatus(20, playerCards);

        assertThat(playerStatus).isEqualTo(Status.LOSE);
    }
}
