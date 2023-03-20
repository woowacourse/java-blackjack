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

import java.util.LinkedHashMap;
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
        blackJackGame.handOutCardTo(shufflingMachine, dealer, 1);
        int dealerCardSize = dealer.getCards().size();

        // then
        assertThat(dealerCardSize).isEqualTo(1);
    }

    @DisplayName("BlackJackGame의 makePlayerProfit은 각 Player의 수익을 계산한다.")
    @Test
    void findResultForEachParticipant() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(), Players.createPlayers("pobi,crong"));

        Card kingCloverOfDealer = new Card(Rank.KING, Suit.CLOVER);
        Card jackSpadeOfDealer = new Card(Rank.JACK, Suit.SPADE);

        Card threeSpadeOfPlayer = new Card(Rank.THREE, Suit.SPADE);
        Card fourSpadeOfPlayer = new Card(Rank.FOUR, Suit.SPADE);

        Dealer dealer = blackJackGame.getDealer();
        Players players = blackJackGame.getPlayers();

        dealer.receiveCard(kingCloverOfDealer);
        dealer.receiveCard(jackSpadeOfDealer);

        for (Player player : players.getPlayers()) {
            player.receiveCard(threeSpadeOfPlayer);
            player.receiveCard(fourSpadeOfPlayer);
        }

        String pobiMoney = "10000";
        String crongMoney = "20000";

        Map<Player, Money> playerProfit = new LinkedHashMap<>();
        for (final Player player : players.getPlayers()) {
            if (player.getName().equals("pobi")) {
                playerProfit.put(player, new Money(pobiMoney));
            }
            if (player.getName().equals("crong")) {
                playerProfit.put(player, new Money(crongMoney));
            }
        }

        // when
        FinalProfit finalProfit = blackJackGame.makePlayerProfit(playerProfit);

        // then
        Map<Player, Money> playerMoney = finalProfit.getPlayerMoney();
        int sum = playerMoney.values().stream().mapToInt(Money::getValue).sum();
        assertThat(sum).isEqualTo(-30000);
    }
}
