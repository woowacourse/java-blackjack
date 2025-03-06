package blackjack.manager;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.StubPossibleSumCardHolder;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.Deck;
import blackjack.domain.GameResultType;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.PlayersResult;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackProcessManagerTest {

    BlackjackProcessManager blackjackProcessManager;

    @BeforeEach
    void init() {
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();
        Deck deck = new Deck(cards);
        blackjackProcessManager = new BlackjackProcessManager(deck, PlayersResult.create(), DealerResult.create());
    }

    @DisplayName("처음에 카드 2장 쥐어준다.")
    @Test
    void test1() {
        // given
        Hand hand = new Hand();

        // when
        blackjackProcessManager.giveStartingCards(hand);

        // then
        assertThat(hand.getAllCards()).hasSize(2);
    }

    @DisplayName("손에 카드 1장을 쥐어준다")
    @Test
    void test2() {
        Hand hand = new Hand();

        // when
        blackjackProcessManager.giveCard(hand);

        // then
        assertThat(hand.getAllCards()).hasSize(1);
    }

    @DisplayName("플레이어의 결과를 판단한다.")
    @Test
    void test3() {
        // given
        Dealer dealer = new Dealer(new StubPossibleSumCardHolder(List.of(15, 20, 21)));
        Player player = new Player("꾹이", new StubPossibleSumCardHolder(List.of(15, 19, 20)));

        // when
        GameResultType gameResultType = blackjackProcessManager.decideResultOfPlayer(player, dealer);

        // then
        assertThat(gameResultType).isEqualTo(GameResultType.LOSE);
    }

    @DisplayName("플레이어의 결과를 판단한다.")
    @Test
    void test4() {
        // given
        Dealer dealer = new Dealer(new StubPossibleSumCardHolder(List.of(15, 20, 21)));
        Player player = new Player("꾹이", new StubPossibleSumCardHolder(List.of(15, 19, 21)));

        // when
        GameResultType gameResultType = blackjackProcessManager.decideResultOfPlayer(player, dealer);

        // then
        assertThat(gameResultType).isEqualTo(GameResultType.TIE);
    }


}
