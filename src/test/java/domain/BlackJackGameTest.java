package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.cards.gamercards.DealerCards;
import domain.cards.gamercards.PlayerCards;
import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @DisplayName("본 게임 시작 전 게이머 모두에게 2장의 카드를 나눠준다.")
    @Test
    void shareInitCardsBeforeGame() {
        BlackJackGame game = new BlackJackGame(List.of("p1", "p2", "p3"));

        game.setUpGame();

        assertThat(game.getGamers().getDealer().getCards()).hasSize(2);
        game.getGamers().getPlayers().forEach(player -> assertThat(player.getCards()).hasSize(2));
        // TODO: refactoring
    }

    @DisplayName("플레이어가 카드를 더 받을 경우 플레이어의 카드 수는 1 증가한다.")
    @Test
    void increaseCardSizeWhenPlayerHit() {
        BlackJackGame game = new BlackJackGame(List.of("dummy")); // 왜 같은 취급되지 않지? 주소는 같다고 출력되는데.
        PlayerCards playerCards = new PlayerCards(new ArrayList<>(List.of(new Card(CardNumber.THREE, CardShape.HEART),
                new Card(CardNumber.FOUR, CardShape.DIAMOND),
                new Card(CardNumber.FIVE, CardShape.CLOVER))));
        Player player = new Player("p1", playerCards);

        boolean isHit = game.hitByPlayer("y", player);

        assertThat(isHit).isTrue();
        assertThat(player.getCards()).hasSize(3 + 1);
    }

    @DisplayName("딜러가 카드를 더 받을 경우 딜러의 카드 수는 1 증가한다.")
    @Test
    void increaseCardSizeWhenDealerHit() {
        BlackJackGame game = new BlackJackGame(List.of("dummy"));
        DealerCards dealerCards = new DealerCards(new ArrayList<>(List.of(new Card(CardNumber.THREE, CardShape.HEART),
                new Card(CardNumber.FOUR, CardShape.DIAMOND),
                new Card(CardNumber.FIVE, CardShape.CLOVER))));
        Dealer dealer = new Dealer(dealerCards);

        game.hitByDealer(dealer);

        assertThat(dealer.getCards()).hasSize(3 + 1);
    }
}
