package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.CardValue;
import domain.card.Suit;
import domain.card.TrumpCard;
import domain.participant.state.HandState;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class HandStateTest {

    @Test
    void 블랙잭에_맞는_이익률을_반환한다() {
        // given
        TrumpCard[] initCards = {new TrumpCard(Suit.HEART, CardValue.A), new TrumpCard(Suit.CLOVER, CardValue.K)};

        // when
        HandState state = HandState.start(initCards);

        // then

        assertThat(state.calculateProfit()).isEqualTo(2.5);
    }

    @Test
    void 버스트일_경우_0의_이익률을_반환한다() {
        // given
        TrumpCard[] initCards = {new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.CLOVER, CardValue.K)};
        TrumpCard drawCard = new TrumpCard(Suit.SPADE, CardValue.K);

        // when
        HandState state = HandState.start(initCards);
        state = state.addCard(drawCard);

        // then
        assertThat(state.calculateProfit()).isEqualTo(0);
    }

    @Test
    void Stay일_경우_드로우_할_수_없다() {
        // given
        TrumpCard[] initCards = {new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.CLOVER, CardValue.K)};
        TrumpCard drawCard = new TrumpCard(Suit.SPADE, CardValue.A);

        // when
        HandState state = HandState.start(initCards).stay();

        // then
        assertThatThrownBy(() -> state.addCard(drawCard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Stay 상태에서는 카드를 추가할 수 없습니다.");
    }
}
