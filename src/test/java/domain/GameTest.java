package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    private final Deck deck = DeckGenerator.generateRandomDeck();
    private final List<String> playerNames = List.of("pobi", "norang", "haru");
    private final Game game = new Game(playerNames, deck);

    @Test
    @DisplayName("참여자가 5명이 넘어갈 경우 예외가 발생한다.")
    void testValidatePlayerCount() {
        // given
        List<String> playerNames = List.of("pobi", "woni", "brie", "neo", "norang", "haru");
        // when && then
        assertThatThrownBy(() -> new Game(playerNames, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 참여자는 최대 5명입니다.");
    }

    @Test
    @DisplayName("이름이 중복될 경우 예외가 발생한다.")
    void testValidateDuplicateName() {
        // given
        List<String> playerNames = List.of("pobi", "pobi");
        // when && then
        assertThatThrownBy(() -> new Game(playerNames, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("참가자에게 카드를 지급할 수 있다.")
    void testHitPlayerCard() {
        // given
        List<Player> players = game.getPlayers();
        Player player = players.getFirst();
        // when
        game.hitPlayerCard(player);
        // then
        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러에게 카드를 지급할 수 있다.")
    void testHitDealerCard() {
        // given && when
        game.hitDealerCard();
        // then
        assertThat(game.getDealerCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("게임 참가자들의 승패 결과를 계산하여 저장한다.")
    void testCalculateGameResult() {
        // given
        List<Player> players = game.getPlayers();
        Player player = players.getFirst();
        // when
        game.calculateGameResult();
        // then
        assertThat(game.getPlayerGameResult(player)).isNotEqualTo(GameResult.NONE);
    }

    @Test
    @DisplayName("딜러의 카드 추가지급 필요 여부를 판단한다.")
    void testDoesDealerNeedCard() {
        // given
        Dealer dealer = (Dealer) game.getParticipants().getFirst();
        // when
        boolean actual = game.doesDealerNeedCard();
        // then
        boolean expected = new GameScore(17).isGreaterThan(dealer.calculateScore());
        assertThat(actual).isEqualTo(expected);
    }
}
