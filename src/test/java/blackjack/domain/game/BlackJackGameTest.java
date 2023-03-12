package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackJackGameTest {

    @DisplayName("초기 카드 2장 배분 후 참가자들이 가진 카드의 총 개수를 확인한다.")
    @Test
    void checkCardSizeOfParticipantsAfterInitialHandOut() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(), Players.createPlayers("pobi,crong"));
        ShufflingMachine shufflingMachine = new ShufflingMachine();

        // when
        blackJackGame.handOutCards(shufflingMachine);

        Players players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        int playerCardSize = players.getPlayers()
                .stream()
                .mapToInt(player -> player.getCards().size())
                .sum();
        int totalCardSize = dealer.getCards().size() + playerCardSize;

        // then
        assertThat(totalCardSize).isEqualTo(6);
    }

    @DisplayName("참가자들에게 카드를 배분한다.")
    @Test
    void handOutCardTo() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(), Players.createPlayers("pobi,crong"));
        ShufflingMachine shufflingMachine = new ShufflingMachine();
        Dealer dealer = blackJackGame.getDealer();

        // when
        blackJackGame.hit(shufflingMachine, dealer);
        int dealerCardSize = dealer.getCards().size();

        // then
        assertThat(dealerCardSize).isEqualTo(1);
    }

//    @DisplayName("BlackJackGame의 makePlayerResult는 각 Player의 승패를 계산한다.")
//    @Test
//    void findResultForEachParticipant() {
//        // given
//        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(), Players.createPlayers("pobi,crong"));
//
//        Card kingCloverOfDealer = new Card(Rank.KING, Suit.CLOVER);
//        Card jackSpadeOfDealer = new Card(Rank.JACK, Suit.SPADE);
//
//        Card threeSpadeOfPlayer = new Card(Rank.THREE, Suit.SPADE);
//        Card fourSpadeOfPlayer = new Card(Rank.FOUR, Suit.SPADE);
//
//        Dealer dealer = blackJackGame.getDealer();
//        Players players = blackJackGame.getPlayers();
//
//        dealer.receiveCard(kingCloverOfDealer);
//        dealer.receiveCard(jackSpadeOfDealer);
//
//        for (Player player : players.getPlayers()) {
//            player.receiveCard(threeSpadeOfPlayer);
//            player.receiveCard(fourSpadeOfPlayer);
//
//            threeSpadeOfPlayer = new Card(Rank.FIVE, Suit.SPADE);
//            fourSpadeOfPlayer = new Card(Rank.SIX, Suit.SPADE);
//        }
//
//        // when
//        Map<Player, Money> playerProfit = blackJackGame.makePlayerProfit();
//
//        // then
//        for (Player player : playerResult.keySet()) {
//            assertThat(playerResult.get(player)).isEqualTo(ResultType.LOSE);
//        }
//    }
}
