package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
