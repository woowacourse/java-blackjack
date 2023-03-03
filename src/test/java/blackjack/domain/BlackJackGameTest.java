package blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackJackGameTest {

    private Players players;

    @BeforeEach
    void init() {
        players = Players.create(List.of("gray", "luca"));
    }

    @Test
    @DisplayName("생성 테스트")
    void createBlackjackGame() {
        BlackJackGame blackJackGame = BlackJackGame.create(players);

        assertThat(blackJackGame).isNotNull();
    }

    @Test
    @DisplayName("초기에 딜러와 플레이어에게 2장씩 카드를 나눠준다.")
    void passCard() {
        BlackJackGame blackJackGame = BlackJackGame.create(players);

        blackJackGame.setUp();
        List<Player> players = blackJackGame.getPlayers().getPlayers();
        Dealer dealer = blackJackGame.getDealer();

        Assertions.assertAll(
                () -> assertThat(players.get(0).getCards().getCount()).isEqualTo(2),
                () -> assertThat(players.get(1).getCards().getCount()).isEqualTo(2),
                () -> assertThat(dealer.getCards().getCount()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("플레어어에게 카드를 한 장 나눠준다.")
    void passExtraCardToPlayer() {
        BlackJackGame blackJackGame = BlackJackGame.create(players);
        List<Player> players = this.players.getPlayers();
        Player gray = players.get(0);
        Player luca = players.get(1);

        blackJackGame.addCard(gray);
        blackJackGame.addCard(luca);
        blackJackGame.addCard(luca);

        assertAll(
                () -> assertThat(gray.getCards().getCount()).isEqualTo(1),
                () -> assertThat(luca.getCards().getCount()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("딜러가 16점 이하이면 카드 한 장을 나눠준다.")
    void passExtraCardToDealer() {
        BlackJackGame blackJackGame = BlackJackGame.create(players);
        Dealer dealer = blackJackGame.getDealer();
        dealer.addCard(new Card(Symbol.SPADE, Number.TEN));
        int beforeCount = dealer.getCards().getCount();

        blackJackGame.addDealerCard();

        assertThat(dealer.getCards().getCount()).isEqualTo(beforeCount + 1);
    }

    @Test
    @DisplayName("딜러가 17점 이상이면 카드 나눠줄 수 없다.")
    void canNotPassExtraCardToDealer() {
        BlackJackGame blackJackGame = BlackJackGame.create(players);
        Dealer dealer = blackJackGame.getDealer();
        dealer.addCard(new Card(Symbol.SPADE, Number.TEN));
        dealer.addCard(new Card(Symbol.HEART, Number.SEVEN));
        int beforeCount = dealer.getCards().getCount();

        //현재 0점
        blackJackGame.addDealerCard();

        assertThat(dealer.getCards().getCount()).isEqualTo(beforeCount);
    }
}
