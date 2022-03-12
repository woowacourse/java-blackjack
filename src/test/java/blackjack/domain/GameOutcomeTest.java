package blackjack.domain;

import static blackjack.domain.GameOutcome.DRAW;
import static blackjack.domain.GameOutcome.LOSE;
import static blackjack.domain.GameOutcome.WIN;
import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardPattern.HEART;
import static blackjack.domain.card.CardPattern.SPADE;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameOutcomeTest {

    @Test
    @DisplayName("딜러가 아닌 사람과 비교 시 예외가 발생해야 한다.")
    void calculateOutcomeExceptionByNotDealer() {
        final List<Card> playerCards = Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A));
        final Participant player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        assertThatThrownBy(() -> GameOutcome.calculateOutcome(player, player))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러가 아닌 사람과 비교할 수 없습니다.");
    }

    @Test
    @DisplayName("둘 다 블랙잭인 경우 무승부를 반환한다.")
    void calculateOutcomeBothBlackJack() {
        final List<Card> playerCards = Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A));
        final Participant player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        final List<Card> dealerCards = Arrays.asList(Card.of(HEART, KING), Card.of(HEART, A));
        final Participant dealer = Dealer.createNewDealer(dealerCards);
        assertThat(player.fight(dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("자신만 블랙잭인 경우 우승를 반환한다.")
    void calculateOutcomeSelfBlackJack() {
        final List<Card> playerCards = Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A));
        final Participant player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        final List<Card> dealerCards = Arrays.asList(Card.of(HEART, KING), Card.of(HEART, JACK));
        final Participant dealer = Dealer.createNewDealer(dealerCards);
        assertThat(player.fight(dealer)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("상대방만 블랙잭인 경우 패배를 반환한다.")
    void calculateOutcomeNotSelfBlackJack() {
        final List<Card> playerCards = Arrays.asList(Card.of(HEART, KING), Card.of(HEART, JACK));
        final Participant player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        final List<Card> dealerCards = Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A));
        final Participant dealer = Dealer.createNewDealer(dealerCards);
        assertThat(player.fight(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("모두 블랙잭이 아닌 경우 숫자로 비교한다.")
    void calculateOutcomeBothNotBlackJack() {
        final List<Card> playerCards = Arrays.asList(Card.of(HEART, KING), Card.of(HEART, JACK));
        final Participant player = Player.createNewPlayer("player", playerCards);
        player.draw(Card.of(HEART, A));
        player.changeFinishStatus();

        final List<Card> dealerCards = Arrays.asList(Card.of(SPADE, SEVEN), Card.of(SPADE, NINE));
        final Participant dealer = Dealer.createNewDealer(dealerCards);
        dealer.draw(Card.of(SPADE, FIVE));
        assertThat(player.fight(dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("둘 다 버스트일 경우, 패배를 반환한다.")
    void calculateOutcomeBothBust() {
        final List<Card> playerCards = Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN));
        final Participant player = Player.createNewPlayer("player", playerCards);
        player.draw(Card.of(SPADE, JACK));
        player.changeFinishStatus();

        final List<Card> dealerCards = Arrays.asList(Card.of(HEART, KING), Card.of(HEART, SIX));
        final Participant dealer = Dealer.createNewDealer(dealerCards);
        dealer.draw(Card.of(HEART, JACK));
        assertThat(player.fight(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("본인만 버스트일 경우, 패배를 반환한다.")
    void calculateOutcomeSelfBust() {
        final List<Card> playerCards = Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN));
        final Participant player = Player.createNewPlayer("player", playerCards);
        player.draw(Card.of(SPADE, JACK));
        player.changeFinishStatus();

        final List<Card> dealerCards = Arrays.asList(Card.of(HEART, KING), Card.of(HEART, QUEEN));
        final Participant dealer = Dealer.createNewDealer(dealerCards);
        assertThat(player.fight(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("상대만 버스트일 경우, 우승을 반환한다.")
    void calculateOutcomeNotSelfBust() {
        final List<Card> playerCards = Arrays.asList(Card.of(HEART, KING), Card.of(HEART, QUEEN));
        final Participant player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        final List<Card> dealerCards = Arrays.asList(Card.of(HEART, KING), Card.of(HEART, SIX));
        final Participant dealer = Dealer.createNewDealer(dealerCards);
        dealer.draw(Card.of(HEART, JACK));
        assertThat(player.fight(dealer)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("EnumMap 순서대로 생성할 수 있다.")
    void createInitMap() {
        assertThat(GameOutcome.createInitMap()).containsExactly(entry(WIN, 0), entry(DRAW, 0), entry(LOSE, 0));
    }
}