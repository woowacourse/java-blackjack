package domain;

import domain.card.Card;
import domain.deck.Deck;
import domain.deck.RandomShuffle;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.card.Rank.*;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameManagerTest {

    @Test
    void 플레이어어_등록_테스트() {
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), new Dealer(), new Players());
        manager.registerPlayer("pobi", "1000");
        manager.registerPlayer("cary", "1000");

        assertThat(manager.getFinalResult().getPlayerResults().entrySet())
                .extracting(Map.Entry::getKey)
                .containsExactly("pobi", "cary");
    }

    @Test
    void 게임을_시작하면_등록된_플레이어부터_딜러_순으로_순서대로_한_장씩_총_2장의_카드를_받는다() {
        GameManager manager = new GameManager(
                new Deck(cards -> {}), // 덱을 섞지 않음, 기본 순서: 스페이드 A, 2, 3, 4 ...
                new Dealer(),
                new Players());
        manager.registerPlayer("pobi", "1000");
        manager.registerPlayer("cary", "1000");

        manager.startGame();

        assertThat(manager.getScoreResults())
                .extracting(
                        GameScoreResultDto::getPlayerName,
                        GameScoreResultDto::getHand
                ).containsExactlyInAnyOrder(
                        tuple("pobi", List.of("A스페이드", "4스페이드")),
                        tuple("cary", List.of("2스페이드", "5스페이드")),
                        tuple("딜러", List.of("3스페이드", "6스페이드"))
                );
    }

    @Test
    void 처음_핸드_공개_시_딜러는_1장_플레이어는_2장의_카드를_공개한다() {
        Dealer dealer = new Dealer();
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), dealer, new Players());
        manager.registerPlayer("pobi", "1000");
        manager.registerPlayer("cary", "1000");

        manager.startGame();
        GameInitialInfoDto initialInfo = manager.getInitialInfo();

        assertAll(
                () -> assertThat(dealer.showHand()).containsOnlyOnce(initialInfo.getDealerOpenCard()),
                () -> assertThat(initialInfo.getPlayerResults())
                        .extracting(
                                result -> result.getHand().size()
                        ).containsOnly(2)
        );
    }

    @Test
    void 플레이어_카드_드로우_테스트() {
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), new Dealer(), new Players());
        manager.registerPlayer("pobi", "1000");
        manager.registerPlayer("cary", "1000");

        manager.drawPlayerCard("pobi");
        manager.drawPlayerCard("cary");
        manager.drawPlayerCard("cary");

        List<GameScoreResultDto> results = manager.getScoreResults();

        assertAll(
                () -> assertThat(results)
                        .filteredOn(result -> result.getPlayerName().equals("pobi"))
                        .extracting(result -> result.getHand().size())
                        .containsExactly(1),
                () -> assertThat(results)
                        .filteredOn(result -> result.getPlayerName().equals("cary"))
                        .extracting(result -> result.getHand().size())
                        .containsExactly(2)
        );
    }

    @Test
    void 딜러_카드_드로우_테스트() {
        Dealer dealer = new Dealer();
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), dealer, new Players());

        manager.drawDealerCard();

        assertThat(dealer.showHand()).hasSize(1);
    }

    @Test
    void 참가자의_이름과_핸드와_점수를_제공한다() {
        Dealer dealer = new Dealer();
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), dealer, new Players());
        dealer.receiveCard(new Card(ACE, SPADE));
        dealer.receiveCard(new Card(KING, DIAMOND));

        List<GameScoreResultDto> scoreResults = manager.getScoreResults();

        assertThat(scoreResults)
                .extracting(
                        GameScoreResultDto::getPlayerName,
                        GameScoreResultDto::getHand,
                        GameScoreResultDto::getScore
                ).containsExactly(tuple("딜러", List.of("A스페이드", "K다이아몬드"), 21));
    }

    @Test
    void 딜러가_추가로_카드를_받을_수_있는지_확인한다() {
        Dealer dealer = new Dealer();
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), dealer, new Players());
        dealer.receiveCard(new Card(SEVEN, SPADE));
        dealer.receiveCard(new Card(EIGHT, DIAMOND));

        assertThat(manager.canDealerReceiveCard()).isTrue();
    }

    @Test
    void 플레이어가_추가로_카드를_받을_수_있는지_확인한다() {
        Players players = new Players();
        players.register("pobi", "10000");
        players.drawCardToPlayer("pobi", new Card(ACE, SPADE));
        players.drawCardToPlayer("pobi", new Card(QUEEN, SPADE));

        GameManager manager = new GameManager(new Deck(new RandomShuffle()), new Dealer(), players);

        assertThat(manager.canPlayerReceiveCard("pobi")).isFalse();
    }

    @Test
    void 플레이어의_핸드를_확인한다() {
        Players players = new Players();
        players.register("pobi", "1000");
        players.drawCardToPlayer("pobi", new Card(ACE, SPADE));
        players.drawCardToPlayer("pobi", new Card(KING, SPADE));

        GameManager manager = new GameManager(new Deck(new RandomShuffle()), new Dealer(), players);
        List<String> hand = manager.getPlayerHand("pobi");

        assertThat(hand).containsExactly("A스페이드", "K스페이드");
    }
}