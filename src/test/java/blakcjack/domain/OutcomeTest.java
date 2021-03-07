package blakcjack.domain;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeTest {
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player(new Name("pobi"));
        dealer = new Dealer();
    }

    @DisplayName("플레이어 버스트시 플레이어 패")
    @Test
    void judgeOutcome_playerBust() {
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.TWO));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.TWO));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

        Outcome outcome = Outcome.of(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러만 버스트시 플레이어 승")
    @Test
    void judgeOutcome_onlyDealerBust() {
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.ACE));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.TWO));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

        Outcome outcome = Outcome.of(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("둘다 버스트 아닐 시 점수 비교")
    @Test
    void judgeOutcome_scoreComparison() {
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.ACE));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

        Outcome outcome = Outcome.of(player, dealer);
        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }
}