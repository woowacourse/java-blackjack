package model.blackjackgame;

import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.ONE;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.SIX;
import static model.card.CardNumber.THREE;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackGameTest {

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 2장 지급")
    @Test
    void testDistributeCardsForSetting() {
        BlackjackGame blackjackGame = prepareBlackjackGame();
        Cards cards = new Cards(
            List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(ONE, HEART),
                new Card(SEVEN, CLOVER), new Card(SIX, DIAMOND), new Card(ONE, SPADE))
        );

        blackjackGame.distributeCardsForSetting(cards);

        Dealer updatedDealer = blackjackGame.getDealer();
        Players updatedPlayers = blackjackGame.getPlayers();

        assertThat(updatedDealer.cardsSize()).isEqualTo(2);
        updatedPlayers.getPlayers()
            .forEach(player -> assertThat(player.cardsSize()).isEqualTo(2));
    }

    private BlackjackGame prepareBlackjackGame() {
        Dealer dealer = new Dealer();
        Players players = Players.from(List.of("lily", "jojo"));
        return new BlackjackGame(dealer, players);
    }

    @DisplayName("각 플레이어에게 추가 카드를 지급한다")
    @Test
    void testHitFromPlayer() {
        BlackjackGame blackjackGame = prepareBlackjackGame();
        Players players = blackjackGame.getPlayers();
        List<Player> playersElement = players.getPlayers();
        List<Answer> answers = List.of(new Answer(true), new Answer(false));
        List<Card> cards = List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER));

        for (int i = 0; i < answers.size(); i++) {
            blackjackGame.hitOrStay(playersElement.get(i), answers.get(i), cards.get(i));
        }

        Players updatedPlayers = blackjackGame.getPlayers();
        assertThat(updatedPlayers.getPlayers().get(0).cardsSize()).isEqualTo(1);
        assertThat(updatedPlayers.getPlayers().get(1).cardsSize()).isEqualTo(0);
    }

    @DisplayName("최초 딜러의 카드 합이 16점 이하이면 카드를 1장을 지급한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedCardSize")
    void testHitIfDealerPossible(Cards cards, int expectedCardSize) {
        Dealer dealer = new Dealer(cards);
        Players players = Players.from(List.of("lily", "jojo"));
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.hitForDealer(new Card(THREE, SPADE));

        Dealer updatedDealer = blackjackGame.getDealer();
        assertThat(updatedDealer.cardsSize()).isEqualTo(expectedCardSize);
    }

    private static Stream<Arguments> provideCardsAndExpectedCardSize() {
        return Stream.of(
            Arguments.of(new Cards(List.of(new Card(JACK, DIAMOND), new Card(SEVEN, CLOVER))), 2),
            Arguments.of(new Cards(List.of(new Card(JACK, DIAMOND), new Card(SIX, CLOVER))), 3)
        );
    }

}
