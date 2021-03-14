package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("pobi", 10000);
        dealer = new Dealer();
    }

    @DisplayName("플레이어 객체 생성 성공")
    @Test
    void create() {
        final Player player = new Player("pobi", 10000);
        assertThat(player).isEqualTo(new Player("pobi", 10000));
    }

    @DisplayName("카드 받기 성공")
    @Test
    void receiveCard() {
        final Player player = new Player("sakjung", 10000);
        final Card card = Card.of(CardSymbol.CLUB, CardNumber.ACE);
        player.receiveCard(card);

        assertThat(player.showCardList()).isEqualTo(Collections.singletonList(Card.of(CardSymbol.CLUB, CardNumber.ACE)));
    }

    @DisplayName("21점 미만이면 추가 카드를 더 뽑을 수 있다.")
    @Test
    void isNotBust() {
        final Player player = new Player("pobi", 10000);
        player.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.KING));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));
        assertThat(player.canDrawMoreCard()).isTrue();

        player.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.ACE));
        assertThat(player.canDrawMoreCard()).isFalse();
    }

    @DisplayName("플레이어 버스트시 플레이어 패")
    @Test
    void decideOutcome_playerBust() {
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.TWO));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.TWO));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

        final Outcome outcome = player.decideOutcome(dealer);
        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러만 버스트시 플레이어 승")
    @Test
    void decideOutcome_onlyDealerBust() {
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.ACE));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.TWO));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

        final Outcome outcome = player.decideOutcome(dealer);
        assertThat(outcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("둘다 버스트 아닐 시 점수 비교")
    @Test
    void decideOutcome_scoreComparison() {
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
        player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.ACE));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
        dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

        final Outcome outcome = player.decideOutcome(dealer);
        assertThat(outcome).isEqualTo(Outcome.LOSE);
    }
}
