package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.gamer.Player;

class BlackJackGameTest {

    @Test
    @DisplayName("플레이어에게 게임 시작 시 2장씩 배분한다.")
    void initDistribution() {
        BlackJackGame blackJackGame = new BlackJackGame(
            Arrays.asList("pobi", "jason"), betting -> 1000, new Deck(Card.getCards()));

        blackJackGame.play(hitRequest -> false, (name, cards) -> {});

        List<Player> players = blackJackGame.getPlayers();

        assertThat(players)
                .map(dto -> dto.getCards().size())
                .containsExactly(2, 2);
    }

    @Test
    @DisplayName("딜러의 점수가 17이상일 때 까지 카드를 1장씩 받는다.")
    void dealerDistribution() {
        BlackJackGame blackJackGame = new BlackJackGame(
            List.of("name"), betting -> 10, new Deck(Card.getCards()));

        blackJackGame.play(hitRequest -> false, (name, cards) -> {});

        int cardNumberSum = blackJackGame.getDealer().sumCardsNumber();

        assertThat(cardNumberSum).isGreaterThan(16);
    }
}
