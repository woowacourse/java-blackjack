package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayersResults;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
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
        blackjackProcessManager = new BlackjackProcessManager(deck, PlayersResults.create());
    }

    @DisplayName("처음에 플레이어에게 카드 2장 쥐어준다.")
    @Test
    void test1() {
        // given
        Hand hand = new Hand();
        Player player = new Player("꾹이", hand);

        // when
        blackjackProcessManager.giveStartingCardsFor(player);

        // then
        assertThat(hand.getAllCards()).hasSize(2);
    }

    @DisplayName("손에 카드 1장을 쥐어준다")
    @Test
    void test2() {
        Hand hand = new Hand();

        // when
        Participant participant = new Player("히로", new Hand());
        blackjackProcessManager.giveCard(participant);

        // then
        assertThat(participant.getCards()).hasSize(1);
    }

    @DisplayName("딜러의 게임 결과를 저장한다.")
    @Test
    void test5() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        Deck deck = new Deck(cardsGenerator.generate());
        PlayersResults playersResults = PlayersResults.create();
        Player player = new Player("히로", new Hand());

        Dealer dealer = new Dealer(new Hand());

        PlayerResult playerResult = new PlayerResult(player, GameResultType.LOSE, 31);
        playersResults.save(playerResult);

        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(deck, playersResults);

        // when
        DealerResult dealerResult = blackjackProcessManager.calculateDefenderResult(dealer);

        // then
        assertThat(dealerResult.getCountsOfResultTypes().getOrDefault(GameResultType.WIN, 0)).isEqualTo(1);
    }
}
