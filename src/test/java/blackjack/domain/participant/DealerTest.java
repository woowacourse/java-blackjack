package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.BlackjackCardsFactory;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Score;
import blackjack.domain.state.HitParticipantState;
import blackjack.domain.state.InitialParticipantState;
import blackjack.domain.state.StandParticipantState;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("딜러는 생성시 초기 상태를 갖는다")
    @Test
    public void createInitialStateDealer() {
        Dealer dealer = Dealer.createInitialStateDealer();

        assertThat(dealer.getState()).isInstanceOf(InitialParticipantState.class);
    }

    @DisplayName("딜러는 카드를 뽑으면 새로운 상태를 가진 딜러가 반환된다")
    @Test
    public void draw() {
        Dealer dealer = Dealer.createInitialStateDealer();

        Dealer newDealer = dealer.draw(Deck.of(new BlackjackCardsFactory(), cards -> cards));

        assertThat(newDealer).isNotInstanceOf(InitialParticipantState.class);
    }

    @DisplayName("딜러의 상태가 종료되지 않았다면 true를 반환한다")
    @Test
    public void canDrawTrue() {
        Dealer dealer = Dealer.createInitialStateDealer();

        assertThat(dealer.canDraw()).isTrue();
    }

    @DisplayName("딜러의 상태가 종료되었다면 false를 반환한다")
    @Test
    public void canDrawFalse() {
        Dealer dealer = Dealer.createInitialStateDealer();
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        dealer = dealer.draw(deck); // 2장 초기화 KING, QUEEN -> 20

        Dealer newDealer = dealer.draw(deck); // KING, QUEEN, JACK -> 30

        assertThat(newDealer.canDraw()).isFalse();
    }

    @DisplayName("딜러가 스탠드를 하면 새로운 상태를 가진 딜러를 반환한다")
    @Test
    public void stand() {
        Dealer dealer = Dealer.createInitialStateDealer();
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        dealer = dealer.draw(deck);

        Dealer newDealer = dealer.stand();

        assertThat(newDealer.getState()).isInstanceOf(StandParticipantState.class);
    }

    @DisplayName("딜러의 스코어를 계산한다")
    @Test
    public void calculate() {
        Dealer dealer = Dealer.createInitialStateDealer();
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        dealer = dealer.draw(deck);

        assertThat(dealer.calculateHand()).isEqualTo(Score.from(20));
    }

    @DisplayName("카드를 더 받을 수 있으면 드로우 한다")
    @Test
    public void decideHitOrStandToHit() {
        Dealer dealer = Dealer.createInitialStateDealer();
        Deck deck = Deck.of(() -> List.of(CardFixture.fromSuitCloverWith(Denomination.TWO),
                        CardFixture.fromSuitCloverWith(Denomination.THREE), CardFixture.fromSuitCloverWith(Denomination.FOUR)),
                cards -> cards);

        Dealer newDealer = dealer.decideHitOrStand(deck);

        assertThat(newDealer.getState()).isInstanceOf(HitParticipantState.class);
    }

    @DisplayName("카드를 더 받을 수 없으면 스탠드 한다")
    @Test
    public void decideHitOrStandToStand() {
        Dealer dealer = Dealer.createInitialStateDealer();
        Deck deck = Deck.of(() -> List.of(CardFixture.fromSuitCloverWith(Denomination.TWO),
                        CardFixture.fromSuitCloverWith(Denomination.JACK), CardFixture.fromSuitCloverWith(Denomination.SEVEN)),
                cards -> cards);
        dealer = dealer.draw(deck);

        Dealer newDealer = dealer.decideHitOrStand(deck);

        assertThat(newDealer.getState()).isInstanceOf(StandParticipantState.class);
    }

    @DisplayName("딜러가 가지고있는 카드 중 마지막 한 장만 제외하고 가져온다")
    @Test
    public void getVisibleCards() {
        Dealer dealer = Dealer.createInitialStateDealer();
        Deck deck = Deck.of(() -> List.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                        CardFixture.fromSuitCloverWith(Denomination.SEVEN)),
                cards -> cards);
        dealer = dealer.draw(deck);

        dealer = dealer.decideHitOrStand(deck);
        List<Card> visibleCards = dealer.getVisibleCards();

        assertThat(visibleCards).isEqualTo(List.of(CardFixture.fromSuitCloverWith(Denomination.SEVEN)));
    }

    @DisplayName("초기 상태에서 처음 핸드를 드로우하면 핸드에 2장이 들어온다")
    @Test
    public void whenTakeFirstHandThenTwoCardsInHand() {
        Dealer dealer = Dealer.createInitialStateDealer();
        Deck deck = Deck.of(() -> List.of(CardFixture.fromSuitCloverWith(Denomination.TWO),
                CardFixture.fromSuitCloverWith(Denomination.FIVE)), cards -> cards);

        dealer = dealer.takeFirstHand(deck);

        assertThat(dealer.getHand().getCards().size()).isEqualTo(2);
    }
}
