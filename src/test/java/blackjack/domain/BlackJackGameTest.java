package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.game.BlackJackReferee;

class BlackJackGameTest {
    @Test
    @DisplayName("플레이어에게 게임 시작 시 2장씩 배분한다.")
    void initDistribution() {
        BlackJackGame blackJackGame = new BlackJackGame(
            Arrays.asList("a", "b"), s -> 10, new Deck(Card.getCards()));
        blackJackGame.play(answer -> false, (s, c) -> {});

        Gamers gamers = blackJackGame.getGamers();
        List<Player> players = gamers.getPlayers();

        assertThat(players)
                .map(dto -> dto.getCards().size())
                .containsExactly(2, 2);
    }

    @Test
    @DisplayName("딜러의 점수가 17이상일 때 까지 카드를 1장씩 받는다.")
    void dealerDistribution() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("name"), s -> 10, new Deck(Card.getCards()));
        blackJackGame.play(answer -> false, (s, c) -> {});

        Gamers gamers = blackJackGame.getGamers();
        int cardNumberSum = gamers.getDealer().sumCardsNumber();

        assertThat(cardNumberSum).isGreaterThan(16);
    }

    @Test
    @DisplayName("1000원 배팅일 때 지면 수익 -1000원")
    void createResultLose() {
        BlackJackGame blackJackGame = new BlackJackGame(
            List.of("name"), s -> 1000, new Deck(Card.getCards()));

        BlackJackReferee result = blackJackGame.play(answer -> true, (s, c) -> {});

        int dealerEarning = result.getDealerEarning();
        Map<String, Integer> playerEarnings = result.getPlayerEarnings();

        assertThat(playerEarnings.get("name")).isEqualTo(-1000);
        assertThat(dealerEarning).isEqualTo(1000);
    }
}
