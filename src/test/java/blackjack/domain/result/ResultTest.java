package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Kind;
import blackjack.domain.card.Number;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @DisplayName("플레이어의 점수가 더 높은 경우 승리 테스트")
    @Test
    void isWinner_Player20_isWin() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.CLOVER),
                Card.from(Number.EIGHT, Kind.CLOVER))));

        assertThat(Result.of(dealer, player)).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러의 점수가 더 높은 경우 패배 테스트")
    @Test
    void isWinner_Player20_isLose() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.EIGHT, Kind.SPADE))));
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.CLOVER))));

        assertThat(Result.of(dealer, player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어만 버스트된 경우 패배 테스트")
    @Test
    void isWinner_PlayerBusted_isLose() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.NINE, Kind.SPADE),
                Card.from(Number.NINE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.HEART))));
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.CLOVER),
                Card.from(Number.EIGHT, Kind.CLOVER))));

        assertThat(Result.of(dealer, player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러만 버스트된 경우 승리 테스트")
    @Test
    void isWinner_DealerBusted_isWin() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new Cards(List.of(
                Card.from(Number.NINE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.HEART),
                Card.from(Number.NINE, Kind.DIAMOND))));

        assertThat(Result.of(dealer, player)).isEqualTo(Result.WIN);
    }

    @DisplayName("둘 다 버스트된 경우 딜러 승 테스트")
    @Test
    void isWinner_BothBusted_isLose() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.NINE, Kind.SPADE),
                Card.from(Number.NINE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.HEART))));
        Dealer dealer = Dealer.createDefaultNameDealer();
        dealer.receive(new Cards(List.of(
                Card.from(Number.NINE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.HEART),
                Card.from(Number.NINE, Kind.DIAMOND))));

        assertThat(Result.of(dealer, player)).isEqualTo(Result.LOSE);
    }
}
