package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayingCardsTest {
    @Test
    @DisplayName("카드 묶음 생성 테스트")
    void createCards() {
        // given
        PlayingCards playingCards = new PlayingCards(List.of(
                PlayingCard.of(Suit.CLUBS, Denomination.FIVE),
                PlayingCard.of(Suit.HEARTS, Denomination.SEVEN)));

        // when
        List<PlayingCard> rawPlayingCards = playingCards.getCards();

        // then
        assertThat(rawPlayingCards).contains(PlayingCard.of(Suit.CLUBS, Denomination.FIVE));
    }

    @ParameterizedTest(name = "{0}{1} + {2}{3} = {4}")
    @CsvSource(value = {
            "SPADES,ACE,CLUBS,ACE,12",
            "SPADES,ACE,CLUBS,TEN,21",
            "SPADES,ACE,CLUBS,NINE,20"})
    @DisplayName("점수 계산 테스트")
    void calculateScore(Suit suit, Denomination denomination,
                        Suit suit2, Denomination denomination2,
                        int expected) {
        // given
        PlayingCards playingCards = new PlayingCards(List.of(
                PlayingCard.of(suit, denomination),
                PlayingCard.of(suit2, denomination2)));

        // when
        int actual = playingCards.getScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}{1} + {2}{3} + {4}{5} = {6}")
    @CsvSource(value = {
            "SPADES,ACE,CLUBS,ACE,SPADES,TEN,false",
            "SPADES,TWO,CLUBS,TEN,SPADES,TEN,true",
            "SPADES,ACE,CLUBS,ACE,SPADES,ACE,false"})
    @DisplayName("버스트 여부 테스트")
    void isBust(Suit suit, Denomination denomination,
                Suit suit2, Denomination denomination2,
                Suit suit3, Denomination denomination3,
                boolean expected) {
        // given
        PlayingCards playingCards = new PlayingCards(List.of(
                PlayingCard.of(suit, denomination),
                PlayingCard.of(suit2, denomination2),
                PlayingCard.of(suit3, denomination3)
        ));

        // when
        boolean actual = playingCards.isBust();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙잭 여부 테스트")
    void isBlackjack() {
        // given
        PlayingCard aClubs = PlayingCard.of(Suit.CLUBS, Denomination.ACE);
        PlayingCard tenHearts = PlayingCard.of(Suit.HEARTS, Denomination.TEN);
        PlayingCard tenDiamonds = PlayingCard.of(Suit.DIAMONDS, Denomination.TEN);

        PlayingCards blackjackPlayingCards = new PlayingCards(List.of(aClubs, tenHearts));
        PlayingCards notBlackjackPlayingCards = new PlayingCards(List.of(tenHearts, tenDiamonds, aClubs));

        // when
        boolean shouldBeBlackjack = blackjackPlayingCards.isBlackJack();
        boolean shouldNotBeBlackjack = notBlackjackPlayingCards.isBlackJack();

        // then
        assertAll(
                () -> assertThat(shouldBeBlackjack).isTrue(),
                () -> assertThat(shouldNotBeBlackjack).isFalse()
        );
    }
}
