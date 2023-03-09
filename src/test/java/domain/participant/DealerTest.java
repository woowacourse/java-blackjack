package domain.participant;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러는 ")
class DealerTest {

    @Test
    void 카드를_가질_수_있다() {
        //given
        Dealer dealer = Dealer.from(Deck.create(notShuffle()));
        int beforeCount = dealer.getCards().size();

        //when
        dealer.addCard(new Card(Rank.ACE, Suit.CLUB));

        //then
        int afterCount = dealer.getCards().size();

        assertThat(beforeCount + 1).isEqualTo(afterCount);
    }

    @Test
    void 카드들의_합을_계산_할_수_있다() {
        //given
        Dealer dealer = Dealer.from(Deck.create(notShuffle()));
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.EIGHT, Suit.HEART));
        cards.forEach(dealer::addCard);

        //when
        int totalScore = dealer.calculateScore();

        //then
        assertThat(totalScore).isEqualTo(21);
    }

    @Test
    void 카드의_합이_16_이하면_stand가_아니다() {
        //given
        Dealer dealer = Dealer.from(Deck.create(notShuffle()));
        dealer.addCard(new Card(Rank.FOUR, Suit.CLUB));

        //when

        //then
        assertThat(dealer.isStand()).isFalse();
    }

    @Test
    void 카드의_합이_16_초과면_stand() {
        //given
        Dealer dealer = Dealer.from(Deck.create(notShuffle()));
        dealer.addCard(new Card(Rank.FIVE, Suit.CLUB));

        //when

        //then
        assertThat(dealer.isStand()).isTrue();
    }

    @Test
    void 카드들의_합이_21_이하라면_bust_가_아니다() {
        //given
        Dealer dealer = Dealer.from(Deck.create(notShuffle()));
        dealer.addCard(new Card(Rank.KING, Suit.CLUB));

        //when

        //then
        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    void 카드들의_합이_21_초과라면_bust() {
        //given
        Dealer dealer = Dealer.from(Deck.create(notShuffle()));
        dealer.addCard(new Card(Rank.KING, Suit.CLUB));
        dealer.addCard(new Card(Rank.KING, Suit.SPADE));
        dealer.addCard(new Card(Rank.KING, Suit.HEART));

        //when

        //then
        assertThat(dealer.isBust()).isTrue();
    }

    private ShuffleStrategy notShuffle() {
        return cards -> {
        };
    }
}
