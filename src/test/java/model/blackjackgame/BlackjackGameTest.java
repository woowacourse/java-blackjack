package model.blackjackgame;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.SIX;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.Hand;
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
        Hand cards = new Hand(
            List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(ACE, HEART),
                new Card(SEVEN, CLOVER), new Card(SIX, DIAMOND), new Card(ACE, SPADE))
        );

        blackjackGame.initCards(cards);

        Dealer updatedDealer = blackjackGame.getDealer();
        Players updatedPlayers = blackjackGame.getPlayers();

        assertThat(updatedDealer.cardsSize()).isEqualTo(2);
        updatedPlayers.getGroup()
            .forEach(player -> assertThat(player.cardsSize()).isEqualTo(2));
    }

    private BlackjackGame prepareBlackjackGame() {
        Dealer dealer = new Dealer();
        Players players = Players.from(List.of("lily", "jojo"));
        return new BlackjackGame(dealer, players);
    }

    @DisplayName("플레이어가 Hit 하면 추가 카드를 지급한다")
    @Test
    void testHitOrStayFromPlayer() {
        BlackjackGame blackjackGame = prepareBlackjackGame();
        Players players = blackjackGame.getPlayers();
        List<Player> playersElement = players.getGroup();
        List<Card> cards = List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER));

        for (int i = 0; i < playersElement.size(); i++) {
            blackjackGame.playerHit(playersElement.get(i), cards.get(i));
        }

        Players updatedPlayers = blackjackGame.getPlayers();
        assertThat(updatedPlayers.getGroup().get(0).cardsSize()).isEqualTo(1);
        assertThat(updatedPlayers.getGroup().get(1).cardsSize()).isEqualTo(1);
    }

    @DisplayName("최초 딜러의 카드 합이 16점 이하인지 유무를 반환한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedCardSize")
    void testIsDealerPossibleHit(Hand cards, boolean isPossibleHit) {
        Dealer dealer = new Dealer(cards);
        Players players = Players.from(List.of("lily", "jojo"));
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        assertThat(blackjackGame.isDealerPossibleHit()).isEqualTo(isPossibleHit);
    }

    private static Stream<Arguments> provideCardsAndExpectedCardSize() {
        return Stream.of(
            Arguments.of(new Hand(List.of(new Card(JACK, DIAMOND), new Card(SEVEN, CLOVER))), false),
            Arguments.of(new Hand(List.of(new Card(JACK, DIAMOND), new Card(SIX, CLOVER))), true)
        );
    }
}
