package domain;

import domain.deck.Deck;
import domain.deck.RandomShuffle;
import domain.deck.Shuffle;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import domain.participant.Dealer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.constant.Rank.ACE;
import static domain.constant.Rank.KING;
import static domain.constant.Suit.DIAMOND;
import static domain.constant.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameManagerTest {

    @Test
    void 플레이어어_등록_테스트() {
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), new Dealer());
        manager.registerPlayer("pobi");
        manager.registerPlayer("cary");

        assertThat(manager.getFinalResult().getPlayerResults())
                .extracting(Map.Entry::getKey)
                .containsExactly("pobi", "cary");
    }

    @Test
    void 게임을_시작하면_등록된_플레이어부터_딜러_순으로_순서대로_한_장씩_총_2장의_카드를_받는다() {
        GameManager manager = new GameManager(new Deck(new Shuffle() {
            @Override
            public void shuffle(List<Card> cards) {
                // 덱을 섞지 않음, 기본 순서: 스페이드 A, 2, 3, 4 ...
            }
        }), new Dealer());

        manager.registerPlayer("pobi");
        manager.registerPlayer("cary");

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
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), dealer);
        manager.registerPlayer("pobi");
        manager.registerPlayer("cary");

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
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), new Dealer());
        manager.registerPlayer("pobi");
        manager.registerPlayer("cary");

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
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), dealer);

        manager.drawDealerCard();

        assertThat(dealer.showHand().size()).isEqualTo(1);
    }

    @Test
    void 참가자의_이름과_핸드와_점수를_제공한다() {
        Dealer dealer = new Dealer();
        GameManager manager = new GameManager(new Deck(new RandomShuffle()), dealer);
        dealer.addCard(new Card(ACE, SPADE));
        dealer.addCard(new Card(KING, DIAMOND));

        List<GameScoreResultDto> scoreResults = manager.getScoreResults();

        assertThat(scoreResults)
                .extracting(
                        result -> result.getPlayerName(),
                        result -> result.getHand(),
                        result -> result.getScore()
                ).containsExactly(tuple("딜러", List.of("A스페이드", "K다이아몬드"), 21));
    }
}