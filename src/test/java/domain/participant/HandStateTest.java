package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.CardValue;
import domain.card.Suit;
import domain.card.TrumpCard;
import domain.participant.state.HandState;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class HandStateTest {

    @Test
    void 블랙잭_승리_수익률을_반환한다() {
        // given
        List<TrumpCard> initCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.K));
        List<TrumpCard> otherInitCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.FIVE));

        HandState state = HandState.start(initCards);
        HandState other = HandState.start(otherInitCards);
        other = other.stay();

        // when
        double actual = state.calculateProfitRate(other);

        // then
        assertThat(actual).isEqualTo(2.5);
    }

    @Test
    void 블랙잭_무승부_수익률을_반환한다() {
        // given
        List<TrumpCard> initCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.K));
        List<TrumpCard> otherInitCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.SPADE, CardValue.J));
        HandState state = HandState.start(initCards);
        HandState other = HandState.start(otherInitCards);

        // when
        double actual = state.calculateProfitRate(other);

        // then
        assertThat(actual).isEqualTo(1.0);
    }

    @Test
    void 버스트일_경우_0의_수익률을_반환한다() {
        // given
        List<TrumpCard> initCards = List.of(new TrumpCard(Suit.HEART, CardValue.K),
                new TrumpCard(Suit.CLOVER, CardValue.K));
        TrumpCard drawCard = new TrumpCard(Suit.SPADE, CardValue.K);
        HandState other = HandState.start(initCards);
        HandState state = HandState.start(initCards);

        // when
        state = state.addCard(drawCard);

        // then
        assertThat(state.calculateProfitRate(other)).isEqualTo(0);
    }

    @Test
    void Stay일_경우_드로우_할_수_없다() {
        // given
        List<TrumpCard> initCards = List.of(new TrumpCard(Suit.HEART, CardValue.K),
                new TrumpCard(Suit.CLOVER, CardValue.K));
        TrumpCard drawCard = new TrumpCard(Suit.SPADE, CardValue.A);

        // when
        HandState state = HandState.start(initCards).stay();

        // then
        assertThatThrownBy(() -> state.addCard(drawCard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Stay 상태에서는 카드를 추가할 수 없습니다.");
    }

    @Test
    void 블랙잭이_아닌상태로_승리한_경우_수익률을_반환한다() {
        // given
        List<TrumpCard> initCards = List.of(new TrumpCard(Suit.HEART, CardValue.J),
                new TrumpCard(Suit.CLOVER, CardValue.K));
        List<TrumpCard> otherInitCards = List.of(new TrumpCard(Suit.HEART, CardValue.FIVE),
                new TrumpCard(Suit.CLOVER, CardValue.FIVE));
        HandState state = HandState.start(initCards);
        HandState other = HandState.start(otherInitCards);
        state = state.stay();
        other = other.stay();

        // when
        double actual = state.calculateProfitRate(other);

        // then
        assertThat(actual).isEqualTo(2.0);
    }

    @Test
    void 블랙잭이_아닌상태로_패배한_경우_수익률을_반환한다() {
        // given
        List<TrumpCard> initCards = List.of(new TrumpCard(Suit.HEART, CardValue.J),
                new TrumpCard(Suit.CLOVER, CardValue.TWO));
        List<TrumpCard> otherInitCards = List.of(new TrumpCard(Suit.HEART, CardValue.FIVE),
                new TrumpCard(Suit.CLOVER, CardValue.A));
        HandState state = HandState.start(initCards);
        HandState other = HandState.start(otherInitCards);
        state = state.stay();
        other = other.stay();

        // when
        double actual = state.calculateProfitRate(other);

        // then
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void 블랙잭이_아닌상태로_무승부한_경우_수익률을_반환한다() {
        // given
        List<TrumpCard> initCards = List.of(new TrumpCard(Suit.HEART, CardValue.J),
                new TrumpCard(Suit.CLOVER, CardValue.K));
        List<TrumpCard> otherInitCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.NINE));
        HandState state = HandState.start(initCards);
        HandState other = HandState.start(otherInitCards);
        state = state.stay();
        other = other.stay();

        // when
        double actual = state.calculateProfitRate(other);

        // then
        assertThat(actual).isEqualTo(1.0);
    }

}
