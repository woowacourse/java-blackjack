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

class PlayerTest {

    @DisplayName("플레이어가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Player player = new Player("ted");
        Card card = new Card(Pattern.CLOVER, Rank.FIVE);

        //when
        player.receiveCard(card);
        List<Card> playerDeck = player.getHandDeck();

        //then
        assertThat(playerDeck).containsExactly(card);
    }

    @DisplayName("플레이어가 최초 두 장의 카드를 받는다.")
    @Test
    void initialDraw() {
        //given
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle());
        Dealer dealer = new Dealer(playingDeck);
        Player player = new Player("ted");

        Card card = new Card(Pattern.SPADE, Rank.ACE);
        Card card2 = new Card(Pattern.SPADE, Rank.TWO);

        List<Card> cards = List.of(card, card2);

        //when
        player.initialDraw(dealer);
        List<Card> playerDeck = player.getHandDeck();

        //then
        assertThat(playerDeck).isEqualTo(cards);
    }

    @DisplayName("플레이어 카드합이 21 이하일 때 히트할 수 있는지 확인한다.")
    @Test
    void canHit() {
        //given
        Player player = new Player("ted");
        Card card = new Card(Pattern.CLOVER, Rank.TEN);
        Card card2 = new Card(Pattern.CLOVER, Rank.ACE);

        //when
        player.receiveCard(card);
        player.receiveCard(card2);

        //then
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("플레이어 카드합을 확인한다.")
    @Test
    void calculateTotalScore() {
        //given
        Player player = new Player("ted");

        Card card = new Card(Pattern.CLOVER, Rank.FIVE);
        Card card2 = new Card(Pattern.CLOVER, Rank.SEVEN);

        player.receiveCard(card);
        player.receiveCard(card2);

        //when
        int totalScore = player.calculateTotalScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }
}
