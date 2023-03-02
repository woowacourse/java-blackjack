package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardMachine;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.Result;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackJackGameTest {

    @DisplayName("초기 카드 2장 배분 후 참가자들이 가진 카드의 총개수를 확인한다.")
    @Test
    void checkCardSizeOfParticipantsAfterInitialHandOut() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame("pobi,crong");
        CardMachine cardMachine = new CardMachine();

        // when
        blackJackGame.handOutInitCards(cardMachine);

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
        BlackJackGame blackJackGame = new BlackJackGame("pobi,crong");
        CardMachine cardMachine = new CardMachine();
        Dealer dealer = blackJackGame.getDealer();

        // when
        blackJackGame.handOutCardTo(cardMachine, dealer);
        int dealerCardSize = dealer.getCards().size();

        // then
        assertThat(dealerCardSize).isEqualTo(1);
    }

    @DisplayName("BlackJackGame의 findWinner는 각 참가자의 승패를 계산한다.")
    @Test
    void findResultForEachParticipant() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame("pobi,crong");

        // when
        Card dealerCard1 = new Card(Rank.KING, Suit.CLOVER);
        Card dealerCard2 = new Card(Rank.JACK, Suit.SPADE);

        Card playerCard1 = new Card(Rank.THREE, Suit.SPADE);
        Card playerCard2 = new Card(Rank.FOUR, Suit.SPADE);

        Dealer dealer = blackJackGame.getDealer();
        Players players = blackJackGame.getPlayers();

        dealer.receiveCard(dealerCard1);
        dealer.receiveCard(dealerCard2);

        for (Player player : players.getPlayers()) {
            player.receiveCard(playerCard1);
            player.receiveCard(playerCard2);

            playerCard1 = new Card(Rank.FIVE, Suit.SPADE);
            playerCard2 = new Card(Rank.SIX, Suit.SPADE);
        }

        blackJackGame.findWinner();

        // then
        Map<Result, Integer> results = dealer.getResults();

        List<Result> playerResults = new ArrayList<>();
        for (Player player: players.getPlayers()) {
            playerResults.add(player.getResult());
        }

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(results.get(Result.WIN)).isEqualTo(2);
            softly.assertThat(results.get(Result.LOSE)).isEqualTo(0);
            softly.assertThat(results.get(Result.DRAW)).isEqualTo(0);
            softly.assertThat(playerResults).isEqualTo(List.of(Result.LOSE, Result.LOSE));
        });
    }
}
