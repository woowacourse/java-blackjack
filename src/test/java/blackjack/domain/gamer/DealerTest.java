package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Pattern;
import blackjack.domain.card.Rank;
import blackjack.domain.deck.DeckGenerator;
import blackjack.domain.deck.PlayingDeck;
import blackjack.domain.deck.shuffle.NoShuffle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DealerTest {

    @DisplayName("딜러는 카드를 뽑을 수 있다.")
    @Test
    void drawCard() {
        //given
        Dealer dealer = new Dealer(new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle()));

        //when
        Card card = dealer.drawCard();

        //then
        assertThat(card).isEqualTo(new Card(Pattern.SPADE, Rank.ACE));
    }

    @DisplayName("딜러 카드를 두 장씩 나누어 줄 수 있다.")
    @Test
    void initialDraw() {
        //given
        Dealer dealer = new Dealer(new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle()));
        Card card = new Card(Pattern.SPADE, Rank.ACE);
        Card card2 = new Card(Pattern.SPADE, Rank.TWO);

        //when
        dealer.initialDraw();
        List<Card> cards = List.of(card, card2);

        //then
        assertAll(
                () -> assertThat(dealer.getHandDeck()).hasSize(2),
                () -> assertThat(dealer.getHandDeck()).isEqualTo(cards)
        );
    }

    @DisplayName("딜러가 받은 카드 중 첫 번째 카드를 확인한다.")
    @Test
    void getFirstCard() {
        //given
        Dealer dealer = new Dealer(new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle()));
        Card card = new Card(Pattern.CLOVER, Rank.FIVE);
        Card card2 = new Card(Pattern.SPADE, Rank.ACE);

        dealer.receiveCard(card);
        dealer.receiveCard(card2);

        //when
        Card firstCard = dealer.getFirstCard();

        //then
        assertThat(firstCard).isEqualTo(card);
    }

    @DisplayName("딜러 카드의 합이 16 이하일 때 히트할 수 있는지 확인한다.")
    @Test
    void canHit() {
        //given
        Dealer dealer = new Dealer(new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle()));
        Card card = new Card(Pattern.CLOVER, Rank.FIVE);
        Card card2 = new Card(Pattern.DIAMOND, Rank.ACE);

        //when
        dealer.receiveCard(card);
        dealer.receiveCard(card2);

        //then
        assertThat(dealer.canHit()).isTrue();
    }


}
