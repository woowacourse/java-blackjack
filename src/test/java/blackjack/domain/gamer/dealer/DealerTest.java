package blackjack.domain.gamer.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.player.Players;
import blackjack.domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.KING;
import static blackjack.domain.card.CardScore.NINE;
import static blackjack.domain.card.CardScore.QUEEN;
import static blackjack.domain.card.CardScore.SEVEN;
import static blackjack.domain.card.CardScore.TWO;
import static blackjack.domain.card.CardSuit.CLUB;
import static blackjack.domain.card.CardSuit.HEART;
import static blackjack.domain.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러")
class DealerTest {
    @Test
    @DisplayName("는 딜 이후 첫 카드만 반환할 수 있다.")
    void getFirstCard() {
        // given
        Dealer dealer = new Dealer(new Deck(cards ->
                List.of(new Card(SPADE, NINE), new Card(CLUB, QUEEN))));
        Card expectedCard = new Card(CLUB, QUEEN);

        // when
        dealer.deal();

        // then
        assertThat(dealer.getFirstCard()).isEqualTo(expectedCard);
    }

    @Test
    @DisplayName("의 카드 합이 16 이하이면 계속 진행할 수 있다.")
    void canContinue() {
        // given
        Dealer dealer = new Dealer(new Deck(cards ->
                List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN))));

        // when
        dealer.deal();

        // then
        assertThat(dealer.canContinue()).isEqualTo(true);
    }

    @Test
    @DisplayName("의 카드 합이 16 이하이면 카드를 한장 더 뽑는다.")
    void hit() {
        // given
        Dealer dealer = new Dealer(new Deck(cards ->
                List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, TWO))));

        // when
        dealer.deal();
        if (dealer.canContinue()) {
            dealer.hit();
        }

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("는 플레이어들의 게임 결과에 따라 수익률은 계산한다.")
    void calculateDealerRevenue() {
        // given
        List<String> names = List.of("seyang", "lemone");
        Dealer dealer = new Dealer(new Deck(cards ->
                List.of(new Card(SPADE, KING), new Card(HEART, QUEEN))));
        Players players = Players.of(names, new Deck(cards ->
                List.of(new Card(SPADE, NINE), new Card(HEART, QUEEN),
                        new Card(SPADE, ACE), new Card(HEART, QUEEN))));
        List<Money> monies = List.of(
                Money.from("10000"),
                Money.from("20000")
        );

        // when
        dealer.deal();
        players.deal();
        for (int i = 0; i < players.get().size(); i++) {
            dealer.keepPlayerMoney(players.get().get(i).getName(), monies.get(i));
        }

        // then
        assertThat(dealer.calculateDealerRevenue(players))
                .isEqualTo(5000);
        assertThat(dealer.calculatePlayerRevenues(players))
                .isEqualTo(Map.of("seyang", 15000.0, "lemone", -20000.0));
    }
}
