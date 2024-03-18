package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.BlackjackCardsFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialParticipantStateTest {

    @DisplayName("초기 상태에서 블랙잭이 아닌 핸드를 받으면 HitState가 반횐된다")
    @Test
    public void hitState() {
        InitialParticipantState initialState = new InitialParticipantState();
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);

        ParticipantState newParticipantState = initialState.draw(deck);

        assertThat(newParticipantState).isInstanceOf(HitParticipantState.class);
    }

    @DisplayName("초기 상태에서 블랙잭인 핸드를 받으면 BlackJackState가 반환된다")
    @Test
    public void blackJackState() {
        InitialParticipantState initialState = new InitialParticipantState();
        Deck deck = Deck.of(new BlackjackCardsFactory(),
                cards -> cards.stream().filter(card -> card.isAce() || card.getDenomination().equals(Denomination.KING))
                        .toList());

        ParticipantState newParticipantState = initialState.draw(deck);

        assertThat(newParticipantState).isInstanceOf(BlackJackParticipantState.class);
    }

    @DisplayName("초기 상태에서 스탠드를 하면 에러가 발생한다")
    @Test
    public void standFail() {
        InitialParticipantState initialState = new InitialParticipantState();

        assertThatCode(initialState::stand).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("초기 상태에서는 할 수 없습니다.");
    }

    @DisplayName("초기 상태에서 핸드 반환하면 에러가 발생한다")
    @Test
    public void getHandFail() {
        InitialParticipantState initialState = new InitialParticipantState();

        assertThatCode(initialState::getHand).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("초기 상태에서는 할 수 없습니다.");
    }

    @DisplayName("초기 상태에서 끝났는지 판단하면 false를 반환한다")
    @Test
    public void isFinishedFalse() {
        InitialParticipantState initialState = new InitialParticipantState();

        assertThat(initialState.isFinished()).isFalse();
    }

    @DisplayName("초기 상태에서 수익률을 구하려하면 에러가 발생한다")
    @Test
    public void getProfitRate() {
        InitialParticipantState initialState = new InitialParticipantState();

        assertThatCode(() -> initialState.getProfitRate(Hand.of())).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("초기 상태에서는 할 수 없습니다.");
    }
}
