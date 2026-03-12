package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.dto.ProfitResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private BlackjackGame createGame(List<String> names, List<Integer> amounts) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Player player = Player.of(Name.of(names.get(i))).withBetAmount(BetAmount.of(amounts.get(i)));
            players.add(player);
        }
        return BlackjackGame.create(Players.of(players), cards -> {
        });
    }

    private void giveCards(Player player, TrumpCard... cards) {
        for (TrumpCard card : cards) {
            player.receiveCard(card);
        }
    }

    private void giveCards(Dealer dealer, TrumpCard... cards) {
        for (TrumpCard card : cards) {
            dealer.receiveCard(card);
        }
    }

    @Test
    void 딜을_하면_모든_플레이어가_카드_2장을_받는다() {
        BlackjackGame game = createGame(List.of("handa", "dalsu"), List.of(10000, 20000));
        game.deal();

        assertThat(game.getPlayers().playerAt(0).countCards()).isEqualTo(2);
        assertThat(game.getPlayers().playerAt(1).countCards()).isEqualTo(2);
    }

    @Test
    void 딜을_하면_딜러가_카드_2장을_받는다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        game.deal();

        assertThat(game.getDealer().countCards()).isEqualTo(2);
    }

    @Test
    void 플레이어_수를_반환한다() {
        BlackjackGame game = createGame(List.of("handa", "dalsu"), List.of(10000, 20000));

        assertThat(game.playerCount()).isEqualTo(2);
    }

    @Test
    void 플레이어_점수가_21이하면_히트_가능하다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getPlayers().playerAt(0),
                TrumpCard.of(Suit.SPADE, Rank.NINE),
                TrumpCard.of(Suit.SPADE, Rank.EIGHT)
        );

        assertThat(game.canPlayerHit(0)).isTrue();
    }

    @Test
    void 플레이어_점수가_21초과면_히트_불가능하다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getPlayers().playerAt(0),
                TrumpCard.of(Suit.SPADE, Rank.KING),
                TrumpCard.of(Suit.SPADE, Rank.NINE),
                TrumpCard.of(Suit.SPADE, Rank.THREE)
        );

        assertThat(game.canPlayerHit(0)).isFalse();
    }

    @Test
    void 딜러_점수가_16이하면_히트_가능하다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getDealer(),
                TrumpCard.of(Suit.HEART, Rank.EIGHT),
                TrumpCard.of(Suit.HEART, Rank.SEVEN)
        );

        assertThat(game.canDealerHit()).isTrue();
    }

    @Test
    void 딜러_점수가_17이상이면_히트_불가능하다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getDealer(),
                TrumpCard.of(Suit.HEART, Rank.EIGHT),
                TrumpCard.of(Suit.HEART, Rank.SIX)
        );

        assertThat(game.canDealerHit()).isTrue();
    }

    @Test
    void 플레이어가_일반_승리하면_베팅금액만큼_수익이_생긴다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getPlayers().playerAt(0),
                TrumpCard.of(Suit.SPADE, Rank.KING),
                TrumpCard.of(Suit.SPADE, Rank.NINE)
        );
        giveCards(game.getDealer(),
                TrumpCard.of(Suit.HEART, Rank.SEVEN),
                TrumpCard.of(Suit.HEART, Rank.EIGHT)
        );

        ProfitResult result = game.calculateProfits();

        assertThat(result.playerProfits().getFirst().profit()).isEqualTo(10000);
    }

    @Test
    void 플레이어가_블랙잭으로_승리하면_1_5배_수익이_생긴다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getPlayers().playerAt(0),
                TrumpCard.of(Suit.SPADE, Rank.ACE),
                TrumpCard.of(Suit.SPADE, Rank.KING)
        );
        giveCards(game.getDealer(),
                TrumpCard.of(Suit.HEART, Rank.SEVEN),
                TrumpCard.of(Suit.HEART, Rank.EIGHT)
        );

        ProfitResult result = game.calculateProfits();

        assertThat(result.playerProfits().getFirst().profit()).isEqualTo(15000);
    }

    @Test
    void 플레이어가_패배하면_베팅금액만큼_잃는다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getPlayers().playerAt(0),
                TrumpCard.of(Suit.SPADE, Rank.SEVEN),
                TrumpCard.of(Suit.SPADE, Rank.EIGHT)
        );
        giveCards(game.getDealer(),
                TrumpCard.of(Suit.HEART, Rank.KING),
                TrumpCard.of(Suit.HEART, Rank.NINE)
        );

        ProfitResult result = game.calculateProfits();

        assertThat(result.playerProfits().getFirst().profit()).isEqualTo(-10000);
    }

    @Test
    void 플레이어가_무승부면_수익이_0이다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getPlayers().playerAt(0),
                TrumpCard.of(Suit.SPADE, Rank.KING),
                TrumpCard.of(Suit.SPADE, Rank.NINE)
        );
        giveCards(game.getDealer(),
                TrumpCard.of(Suit.HEART, Rank.KING),
                TrumpCard.of(Suit.HEART, Rank.NINE)
        );

        ProfitResult result = game.calculateProfits();

        assertThat(result.playerProfits().getFirst().profit()).isEqualTo(0);
    }

    @Test
    void 딜러_수익은_플레이어_수익의_합에_마이너스다() {
        BlackjackGame game = createGame(List.of("handa"), List.of(10000));
        giveCards(game.getPlayers().playerAt(0),
                TrumpCard.of(Suit.SPADE, Rank.KING),
                TrumpCard.of(Suit.SPADE, Rank.NINE)
        );
        giveCards(game.getDealer(),
                TrumpCard.of(Suit.HEART, Rank.SEVEN),
                TrumpCard.of(Suit.HEART, Rank.EIGHT)
        );

        ProfitResult result = game.calculateProfits();

        assertThat(result.dealerProfit()).isEqualTo(-10000);
    }

    @Test
    void 여러_플레이어의_딜러_수익은_플레이어_수익의_합에_마이너스다() {
        BlackjackGame game = createGame(List.of("handa", "dalsu"), List.of(10000, 20000));
        giveCards(game.getPlayers().playerAt(0),
                TrumpCard.of(Suit.SPADE, Rank.KING),
                TrumpCard.of(Suit.SPADE, Rank.NINE)
        );
        giveCards(game.getPlayers().playerAt(1),
                TrumpCard.of(Suit.SPADE, Rank.SEVEN),
                TrumpCard.of(Suit.SPADE, Rank.EIGHT)
        );
        giveCards(game.getDealer(),
                TrumpCard.of(Suit.HEART, Rank.SEVEN),
                TrumpCard.of(Suit.HEART, Rank.EIGHT)
        );

        ProfitResult result = game.calculateProfits();

        assertThat(result.dealerProfit()).isEqualTo((-1) * 10000);
    }
}