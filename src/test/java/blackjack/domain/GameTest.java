package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

//    @DisplayName("딜러와 플레이어는 초기패로 2장의 카드를 받는다")
//    @Test
//    void should_getTwoCards_To_InitialHands() {
//        Deck deck = Deck.createShuffledDeck();
//        //TODO: game에서 초기패주는 것 구현 후 리팩토링
//
//        Game game = new Game(dealer, players);
//
//        assertThat(dealer.getHandsCards()).hasSize(2);
//        assertThat(players.getPlayers().get(0).getHandsCards()).hasSize(2);
//    }
}
