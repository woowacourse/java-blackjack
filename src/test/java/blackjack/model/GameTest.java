package blackjack.model;

import static blackjack.TestFixtures.NO_HIT_STRATEGY;
import static blackjack.TestFixtures.TEST_EMPTY_VISUALIZER;
import static blackjack.TestFixtures.createHitDecisionStrategy;
import static blackjack.model.card.CardFixtures.NO_SHUFFLER;
import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_SEVEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_SIX_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임 테스트")
class GameTest {

    @DisplayName("딜러와 플레이어를 가진다.")
    @Test
    void createTest() {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        List<Player> players = List.of(
                new Player(new Name("pobi"), NO_HIT_STRATEGY),
                new Player(new Name("neo"), NO_HIT_STRATEGY));

        // when, then
        assertThatCode(() -> new Game(dealer, players))
                .doesNotThrowAnyException();
    }

    @DisplayName("모든 참가자에게 2장의 패를 준다.")
    @Test
    void dealInitialCardsTest() {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        List<Player> players = List.of(
                new Player(new Name("pobi"), NO_HIT_STRATEGY),
                new Player(new Name("neo"), NO_HIT_STRATEGY));
        Game game = new Game(dealer, players);

        // when
        game.dealInitialCards();

        // then
        assertThat(players)
                .allSatisfy(player -> assertThat(player.getHand()).hasSize(2));
        assertThat(dealer.getHand())
                .hasSize(2);
    }

    @DisplayName("플레이어들이 원하면 카드를 더 준다")
    @Test
    void playTurnForAllPlayerTest() {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        HitDecisionStrategy hitDecisionStrategy = createHitDecisionStrategy(List.of(true, false));
        List<Player> players = List.of(new Player(new Name("pobi"), hitDecisionStrategy));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        //when
        game.askHitForAllPlayer(TEST_EMPTY_VISUALIZER);

        // then
        assertThat(players)
                .allSatisfy(player -> assertThat(player.getHand()).hasSize(3));
    }

    @DisplayName("딜러가 패 한장을 공개한다.")
    @Test
    void getDealerVisibleCardTest() {
        // given
        Dealer dealer = new Dealer(Deck.createDeckByCards(List.of(SPADE_SIX_CARD, SPADE_TEN_CARD), NO_SHUFFLER));
        Game game = new Game(dealer, List.of());
        game.dealInitialCards();

        //when
        Card dealerVisibleCard = game.getDealerVisibleCard();

        // then
        assertThat(dealerVisibleCard)
                .isEqualTo(SPADE_SIX_CARD);
        assertThat(dealerVisibleCard)
                .isNotEqualTo(SPADE_TEN_CARD);
    }

    @DisplayName("처음 받은 패가 블랙잭인 경우, 히트 여부를 묻지 않는다.")
    @Test
    void playTurn_ForAllPlayer_WhenBlackjackTest() {
        // given
        List<Card> cards = List.of(
                SPADE_ACE_CARD,
                SPADE_TEN_CARD,
                SPADE_SEVEN_CARD,
                SPADE_TEN_CARD,
                SPADE_TEN_CARD
        );
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        HitDecisionStrategy hitDecisionStrategy = createHitDecisionStrategy(List.of(true));
        List<Player> players = List.of(new Player(new Name("pobi"), hitDecisionStrategy));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        //when
        game.askHitForAllPlayer(TEST_EMPTY_VISUALIZER);

        // then
        assertThat(players)
                .allSatisfy(player -> assertThat(player.getHand()).hasSize(2));
    }

    @DisplayName("버스트인 경우 히트 여부를 묻지 않는다.")
    @Test
    void playTurn_ForAllPlayer_WhenBustTest() {
        // given
        List<Card> cards = List.of(
                SPADE_TEN_CARD,
                SPADE_TEN_CARD,
                SPADE_SEVEN_CARD,
                SPADE_TEN_CARD,
                SPADE_TWO_CARD
        );
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        HitDecisionStrategy hitDecisionStrategy = createHitDecisionStrategy(List.of(true, true));
        List<Player> players = List.of(new Player(new Name("pobi"), hitDecisionStrategy));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        //when
        game.askHitForAllPlayer(TEST_EMPTY_VISUALIZER);

        // then
        assertThat(players)
                .allSatisfy(player -> assertThat(player.getHand()).hasSize(3));
    }

    @DisplayName("딜러가 가진 패의 합이 16 이하인 경우 카드 한장을 추가로 받는다.")
    @Test
    void dealerHitTest() {
        // given
        List<Card> cards = List.of(
                SPADE_TEN_CARD,
                SPADE_TEN_CARD,
                SPADE_SEVEN_CARD,
                SPADE_TWO_CARD,
                SPADE_TEN_CARD
        );
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        List<Player> players = List.of(new Player(new Name("pobi"), NO_HIT_STRATEGY));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        //when
        boolean isDealerHit = game.dealerHit();

        // then
        assertThat(isDealerHit)
                .isSameAs(true);
        assertThat(dealer.getHand())
                .hasSize(3);
    }

    @DisplayName("딜러가 가진 패의 합이 16 초과인 경우 카드 한장을 추가로 받지 않는다.")
    @Test
    void dealerHitWhenOver16Test() {
        // given
        List<Card> cards = List.of(
                SPADE_TEN_CARD,
                SPADE_TEN_CARD,
                SPADE_SEVEN_CARD,
                SPADE_TEN_CARD,
                SPADE_TEN_CARD
        );
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        List<Player> players = List.of(new Player(new Name("pobi"), NO_HIT_STRATEGY));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        //when
        boolean isDealerHit = game.dealerHit();

        // then
        assertThat(isDealerHit)
                .isSameAs(false);
        assertThat(dealer.getHand())
                .hasSize(2);
    }

    @DisplayName("모든 플레이어의 승부를 판단한다.")
    @Test
    void judgeMatchResultsTest() {
        // given
        List<Card> cards = List.of(
                SPADE_TEN_CARD,
                SPADE_TEN_CARD,
                SPADE_SEVEN_CARD,
                SPADE_TEN_CARD
        );
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        List<Player> players = List.of(new Player(new Name("pobi"), NO_HIT_STRATEGY));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        // when
        Map<Player, MatchResult> playerMatchResults = game.judgeMatchResults();

        // then
        assertThat(playerMatchResults.get(new Player(new Name("pobi"), NO_HIT_STRATEGY)))
                .isSameAs(MatchResult.WIN);
    }
}
