package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackJackGameTest {

    @DisplayName("초기 카드 2장 배분 후 참가자들이 가진 카드의 총개수를 확인한다.")
    @Test
    void checkCardSizeOfParticipantsAfterInitialHandOut() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame("pobi,crong");
        Deck deck = new Deck();

        // when
        blackJackGame.handOutInitCards(deck);

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
        Deck deck = new Deck();
        Dealer dealer = blackJackGame.getDealer();

        // when
        blackJackGame.handOutCardTo(deck, dealer);
        int dealerCardSize = dealer.getCards().size();

        // then
        assertThat(dealerCardSize).isEqualTo(1);
    }

    @DisplayName("BlackJackGame의 findWinner는 각 참가자의 승패를 계산하고 Dealer의 승패를 반환한다.")
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

        PlayerResult playerResult = new PlayerResult();
        DealerResult dealerResult = new DealerResult();
        blackJackGame.calculateParticipantResult(dealerResult, playerResult);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealerResult.getValue(Result.WIN)).isEqualTo(2);
            softly.assertThat(dealerResult.getValue(Result.LOSE)).isNull();
            softly.assertThat(dealerResult.getValue(Result.PUSH)).isNull();
        });
    }
}
