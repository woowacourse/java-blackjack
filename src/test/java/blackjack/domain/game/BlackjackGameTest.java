package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardsGenerator;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participants.Bet;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.PlayerGroup;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    private static final int INITIAL_DEAL_COUNT = 2;
    private static final int DEALER_HIT_THRESHOLD = 16;

    private final CardsGenerator cardsGenerator = () -> List.of(
        new Card(Rank.SIX, Suit.DIAMOND),
        new Card(Rank.TEN, Suit.HEART),
        new Card(Rank.TEN, Suit.CLOVER),
        new Card(Rank.TEN, Suit.SPADE));
    private final GameReferee referee = new BlackjackGameReferee();
    private final BlackjackGame game = BlackjackGame.create(
        cardsGenerator,
        referee,
        new PlayerGroup(List.of(new Player(new Name("pobi"), Hand.empty(), Bet.zero()))));

    @Test
    void 초기_카드를_딜러와_플레이어들에게_2장씩_분배한다() {
        // given & when
        game.initialDeal();
        // then
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();

        assertThat(dealer.getCards()).hasSize(INITIAL_DEAL_COUNT);
        players.forEach(player ->
            assertThat(player.getCards()).hasSize(INITIAL_DEAL_COUNT));
    }

    @Test
    void 딜러는_카드_합계가_임계점을_초과할_때까지_hit한다() {
        // given & when
        int hitCount = game.playDealerTurn();
        // then
        Dealer dealer = game.getDealer();

        assertThat(dealer.getScore().value()).isGreaterThan(DEALER_HIT_THRESHOLD);
        assertThat(dealer.getCards()).hasSize(hitCount);
    }
}