package blackjack.util;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameInitializerTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = GameInitializer.initializeDeck();
    }

    @DisplayName("초기 덱 생성 검증")
    @Test
    void checkInitialDeck() {
        assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @DisplayName("초기 플레이어 카드 분배 검증")
    @Test
    void checkPlayerCardDistribution() throws InvalidNameInputException {
        List<Player> unpreparedPlayers = Arrays.asList(
                new Player("a", "0"),
                new Player("b", "0"));
        Players players = GameInitializer.initializePlayers(unpreparedPlayers, deck);
        players.unwrap().forEach(player ->
                assertThat(player.getHand().unwrap().size()).isEqualTo(2));
    }

    @DisplayName("초기 딜러 카드 분배 검증")
    @Test
    void checkDealerCardDistribution() throws InvalidNameInputException {
        Dealer dealer = GameInitializer.initializeDealer(deck);
        assertThat(dealer.getHand().unwrap().size()).isEqualTo(2);
    }


}
