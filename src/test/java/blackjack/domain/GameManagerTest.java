package blackjack.domain;

import blackjack.domain.card.BlackjackShuffle;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameManagerTest {

    private static final BlackjackShuffle blackjackShuffle = new SortShuffle();

    @Test
    @DisplayName("참가자를 추가한다")
    void addParticipants() {
        List<Player> names = List.of(new Gambler("비타"));

        GameManager gameManager = new GameManager(blackjackShuffle);
        gameManager.addGamblers(names);

        assertThat(gameManager.getPlayers().getGamblers().size())
                .isEqualTo(1);
    }

    @DisplayName("참가자에게 카드를 한장 추가 발부한다")
    @Test
    void deal_card_to_gambler_test() {
        Player player = new Gambler("두리");

        GameManager gameManager = new GameManager(blackjackShuffle);
        gameManager.addGamblers(List.of(player));

        gameManager.dealAddCard(player);
        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어의 카드가 버스트면 TRUE를 반환한다")
    void ifThePlayerS_CardIsBurstItReturns_True() {
        Player player = new Gambler("비타");
        List<Player> names = List.of(player);

        GameManager gameManager = new GameManager(blackjackShuffle);
        gameManager.addGamblers(names);
        gameManager.dealAddCard(player);

        boolean result = gameManager.isPlayerBust(player);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드가 히트면 카드를 한장 추가한다")
    void ifTheDealerS_CardIsHitAddACard() {
        // given
        GameManager gameManager = new GameManager(blackjackShuffle);

        // when
        boolean result = gameManager.isDealerHitThenDealAddCard();

        // then
        assertAll(
                () -> assertThat(result).isTrue(),
                () -> assertThat(gameManager.getPlayers().getDealer().getCards().size()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("딜러의 카드가 히트가 아니면 false 를 반환한다")
    void if_the_dealer_card_is_not_hit_get_false() {
        GameManager gameManager = new GameManager(blackjackShuffle);
        gameManager.addGamblers(List.of(new Gambler("비타")));
        boolean result = gameManager.isDealerHitThenDealAddCard();

        assertThat(result).isFalse();
    }
}
