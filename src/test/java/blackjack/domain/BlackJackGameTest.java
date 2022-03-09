package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameTest {
    @Test
    @DisplayName("딜러와 플레이어에게 게임 시작 시 2장씩 배분한다.")
    void initDistribution() {
        Dealer dealer = new Dealer();
        List<Player> players = Arrays.asList(new Player("a"), new Player("a"));

        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.initDistribution(dealer, players);

        assertThat(dealer.getCards().size()).isEqualTo(2);
        assertThat(players)
                .map(gamer -> gamer.getCards().size())
                .containsExactly(2, 2);
    }

    @Test
    @DisplayName("플레이어에게 1장 배분한다.")
    void distributeCard() {
        Gamer gamer = new Gamer("name");
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.distributeCard(gamer);

        assertThat(gamer.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 점수가 17이상일 때 까지 카드를 1장씩 받는다.")
    void dealerDistribution() {
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame();
        dealer.addCard(Card.getInstance(CardShape.CLOVER,  CardNumber.EIGHT));

        blackJackGame.distributeAdditionalToDealer(dealer);
        assertThat(dealer.isOverThan(16)).isTrue();
    }
}
