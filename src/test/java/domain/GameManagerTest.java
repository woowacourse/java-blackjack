package domain;

import domain.constant.Rank;
import domain.constant.Suit;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class GameManagerTest {

    @Test
    void 게임을_시작하면_등록된_플레이어와_딜러_순서대로_카드를_2장씩_나눠준다() {
        GameManager manager = new GameManager();
        manager.addPlayer("pobi");
        manager.addPlayer("cary");

        manager.startGame();

        List<GameScoreResultDto> scoreResults = manager.getScoreResults();

        assertThat(scoreResults)
                .extracting(
                        result -> result.getHand().size(),
                        GameScoreResultDto::getPlayerName
                ).containsExactly(
                        tuple(2, "딜러"),
                        tuple(2, "pobi"),
                        tuple(2, "cary"));
    }

    @Test
    void 게임이_시작된_후_딜러의_카드는_한_장만_공개한다() {
        GameManager manager = new GameManager();

        manager.startGame();

        assertThat(manager.getInitialInfo())
                .filteredOn(info -> info.getPlayerName().equals("딜러"))
                        .extracting(info -> info.getHand().size())
                                .containsOnly(1);
    }

    @Test
    void 플레이어의_카드는_두_장_공개한다() {
        GameManager manager = new GameManager();

        manager.addPlayer("pobi");

        manager.startGame();
        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();

        assertThat(initialInfo.get(1).getHand().size()).isEqualTo(2);
    }

    @Test
    void 플레이어를_한명_등록한다() {
        GameManager manager = new GameManager();
        manager.addPlayer("pobi");

        List<Player> result = manager.getPlayerSequence();

        assertThat(result)
                .extracting(Player::getName)
                .containsExactly("pobi");
    }

    @Test
    void 플레이어를_세명_등록한다() {
        GameManager manager = new GameManager();
        List<String> playerNames = List.of("pobi", "cary", "rudy");

        for (String playerName : playerNames) {
            manager.addPlayer(playerName);
        }

        List<Player> result = manager.getPlayerSequence();

        assertThat(result).hasSize(3);
    }

    @Test
    void 합계가_21점이면_블랙잭이다() {
        GameManager manager = new GameManager();
        Player player = new Player("pobi");
        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.SPADE));

        assertThat(manager.isBlackjack(player)).isTrue();
    }

    @Test
    void 합계가_21점이_아니면_블랙잭이_아니다() {
        GameManager manager = new GameManager();
        Player player = new Player("pobi");
        player.addCard(new Card(Rank.TEN, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.SPADE));

        assertThat(manager.isBlackjack(player)).isFalse();
    }

    @Test
    void 딜러가_16점_이하인지_확인한다() {
        GameManager manager = new GameManager();
        manager.startGame();
        List<GameScoreResultDto> scoreResults = manager.getScoreResults();

        boolean expected = false;
        if (scoreResults.getFirst().getResult() <= 16) {
            expected = manager.isDealerTurn();
        }

        assertThat(manager.isDealerTurn()).isEqualTo(expected);
    }

    @Test
    void 점수가_21점을_초과하면_버스트이다() {
        GameManager manager = new GameManager();
        Player player = new Player("pobi");
        player.addCard(new Card(Rank.FOUR, Suit.SPADE));
        player.addCard(new Card(Rank.TEN, Suit.SPADE));
        player.addCard(new Card(Rank.JACK, Suit.SPADE));

        manager.judgeBust(manager.calculateScore(player.getHand()), player);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    void 점수가_21점_이하면_버스트가_아니다() {
        GameManager manager = new GameManager();
        Player player = new Player("pobi");
        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.TEN, Suit.SPADE));

        manager.judgeBust(manager.calculateScore(player.getHand()), player);

        assertThat(player.isBust()).isFalse();
    }

    @Test
    void 플레이어_카드_드로우_테스트() {
        GameManager manager = new GameManager();
        Player player = new Player("pobi");
        int beforeScore = manager.calculateScore(player.getHand());

        manager.drawPlayerCard(player);
        int afterScore = manager.calculateScore(player.getHand());

        assertThat(player.getHand()).hasSize(1);
        assertThat(afterScore).isNotEqualTo(beforeScore);
    }

    @Test
    void 딜러_카드_드로우_테스트() {
        GameManager manager = new GameManager();
        int beforeScore = manager.getScoreResults().stream()
                .filter(result -> result.getPlayerName().equals("딜러"))
                .mapToInt(GameScoreResultDto::getResult)
                .findAny().orElse(0);

        List<String> dealerHand = manager.drawDealerCard();
        int afterScore = manager.getScoreResults().stream()
                .filter(result -> result.getPlayerName().equals("딜러"))
                .mapToInt(GameScoreResultDto::getResult)
                .findAny().orElse(0);

        assertThat(dealerHand).hasSize(1);
        assertThat(afterScore).isNotEqualTo(beforeScore);
    }
}