package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Hand;
import domain.card.Suit;
import domain.player.Bet;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 높으면 참가자는 베팅액을 잃는다.")
    void givenDealerWinningFromPlayer() {
        Dealer dealer = makeDealer(new Card(Suit.DIAMOND, Denomination.KING));
        Gambler gambler = makeParticipant(new Card(Suit.DIAMOND, Denomination.NINE));

        Bet bet = dealer.battle(gambler);

        assertThat(bet.getValue()).isEqualTo(-3000);
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 낮으면 참가자는 베팅액을 가져간다.")
    void givenDealerLosingFromPlayer() {
        Dealer dealer = makeDealer(new Card(Suit.SPADE, Denomination.NINE));
        Gambler gambler = makeParticipant(new Card(Suit.DIAMOND, Denomination.ACE));

        Bet bet = dealer.battle(gambler);

        assertThat(bet.getValue()).isEqualTo(3000);
    }

    @Test
    @DisplayName("딜러가 상대 플레이어와 점수가 같으면 참가자는 베팅액을 그대로 돌려받는다.")
    void givenDealerDrawingWithPlayer() {
        Dealer dealer = makeDealer(new Card(Suit.DIAMOND, Denomination.NINE));
        Gambler gambler = makeParticipant(new Card(Suit.HEART, Denomination.NINE));

        Bet bet = dealer.battle(gambler);

        assertThat(bet.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("참가자만 버스트가 일어나면 참가자는 배팅액을 모두 잃는다.")
    void givenParticipantBust() {
        Dealer dealer = makeDealer(new Card(Suit.DIAMOND, Denomination.NINE));
        Gambler gambler = makeParticipant(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.DIAMOND, Denomination.SEVEN)
        );

        Bet bet = dealer.battle(gambler);

        assertThat(bet.getValue()).isEqualTo(-3000);
    }

    @Test
    @DisplayName("딜러만 버스트가 일어나면 참가자는 승리해 베팅액을 가져간다.")
    void givenDealerBust() {
        Dealer dealer = makeDealer(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.DIAMOND, Denomination.SEVEN)
        );
        Gambler gambler = makeParticipant(new Card(Suit.DIAMOND, Denomination.NINE));

        Bet bet = dealer.battle(gambler);

        assertThat(bet.getValue()).isEqualTo(3000);
    }

    @Test
    @DisplayName("딜러와 참가자 둘 다 버스트가 일어나면 참가자는 베팅액을 그대로 돌려받는다.")
    void givenDealerParticipantBothBust() {
        Dealer dealer = makeDealer(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.DIAMOND, Denomination.SEVEN)
        );
        Gambler gambler = makeParticipant(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.DIAMOND, Denomination.SEVEN)
        );

        Bet bet = dealer.battle(gambler);

        assertThat(bet.getValue()).isEqualTo(0);
    }

    private Gambler makeParticipant(Card... cards) {
        return new Gambler(
                new Hand(List.of(cards)),
                Name.of("참가자"),
                Bet.from(3000)
        );
    }

    private static Dealer makeDealer(Card... cards) {
        return new Dealer(new Hand(
                List.of(cards)
        ));
    }
}