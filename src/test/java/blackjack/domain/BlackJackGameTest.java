package blackjack.domain;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.betting.Betting;
import blackjack.domain.betting.BettingTable;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackJackGameTest {

    @Test
    void 처음에_2장씩_카드를_뽑는다() {
        final Participants participants = new Participants(
                new Dealer(),
                List.of(new Player("toney"), new Player("dazzle"))
        );
        final BettingTable bettingTable = new BettingTable(new HashMap<>());
        final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

        blackJackGame.initialDraw();

        assertAll(
                () -> assertThat(blackJackGame.getDealerHand().count()).isEqualTo(2),
                () -> assertThat(blackJackGame.getPlayerHand(new PlayerName("toney")).count()).isEqualTo(2),
                () -> assertThat(blackJackGame.getPlayerHand(new PlayerName("dazzle")).count()).isEqualTo(2)
        );
    }

    @Test
    void 해당_플레이어는_카드를_뽑는다() {
        final Participants participants = new Participants(
                new Dealer(),
                List.of(new Player("toney"), new Player("dazzle"))
        );
        final BettingTable bettingTable = new BettingTable(new HashMap<>());
        final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

        blackJackGame.dealCardForPlayer(new PlayerName("toney"));

        assertThat(blackJackGame.getPlayerHand(new PlayerName("toney")).count()).isEqualTo(1);
    }

    @Test
    void 딜러는_카드를_뽑는다() {
        final Participants participants = new Participants(
                new Dealer(),
                List.of(new Player("toney"), new Player("dazzle"))
        );
        final BettingTable bettingTable = new BettingTable(new HashMap<>());
        final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

        blackJackGame.dealCardForDealer();

        assertThat(blackJackGame.getDealerHand().count()).isEqualTo(1);
    }

    @Nested
    class getPlayerProfit_메서드는 {

        @Nested
        class 플레이어가_블랙잭이라면 {

            @Test
            void 딜러에게_이긴경우_베팅금액과_보너스를_받는다() {
                final Player player = new Player("toney");
                player.drawCard(new Card(ACE, SPADE));
                player.drawCard(new Card(QUEEN, SPADE));
                final Participants participants = new Participants(
                        new Dealer(),
                        List.of(player, new Player("dazzle"))
                );
                final Map<PlayerName, Betting> betting = new HashMap<>();
                betting.put(new PlayerName("toney"), new Betting(10000));
                final BettingTable bettingTable = new BettingTable(betting);
                final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

                final int profit = blackJackGame.getPlayerProfit(new PlayerName("toney"));

                assertThat(profit).isEqualTo(15000);
            }

            @Test
            void 딜러와_비긴경우_수익은_없다() {
                final Player player = new Player("toney");
                player.drawCard(new Card(ACE, SPADE));
                player.drawCard(new Card(QUEEN, SPADE));
                final Dealer dealer = new Dealer();
                dealer.drawCard(new Card(ACE, HEART));
                dealer.drawCard(new Card(QUEEN, HEART));
                final Participants participants = new Participants(
                        dealer,
                        List.of(player, new Player("dazzle"))
                );
                final Map<PlayerName, Betting> betting = new HashMap<>();
                betting.put(new PlayerName("toney"), new Betting(10000));
                final BettingTable bettingTable = new BettingTable(betting);
                final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

                final int profit = blackJackGame.getPlayerProfit(new PlayerName("toney"));

                assertThat(profit).isEqualTo(0);
            }
        }

        @Nested
        class 플레이어가_블랙잭이_아니라면 {

            @Test
            void 딜러에게_이긴경우_베팅금액만큼_받는다() {
                final Player player = new Player("toney");
                player.drawCard(new Card(TEN, SPADE));
                player.drawCard(new Card(QUEEN, SPADE));
                final Dealer dealer = new Dealer();
                dealer.drawCard(new Card(NINE, HEART));
                dealer.drawCard(new Card(QUEEN, HEART));
                final Participants participants = new Participants(
                        dealer,
                        List.of(player, new Player("dazzle"))
                );
                final Map<PlayerName, Betting> betting = new HashMap<>();
                betting.put(new PlayerName("toney"), new Betting(10000));
                final BettingTable bettingTable = new BettingTable(betting);
                final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

                final int profit = blackJackGame.getPlayerProfit(new PlayerName("toney"));

                assertThat(profit).isEqualTo(10000);
            }

            @Test
            void 딜러와_비긴경우_수익은_없다() {
                final Player player = new Player("toney");
                player.drawCard(new Card(TWO, SPADE));
                player.drawCard(new Card(QUEEN, SPADE));
                final Dealer dealer = new Dealer();
                dealer.drawCard(new Card(TWO, HEART));
                dealer.drawCard(new Card(QUEEN, HEART));
                final Participants participants = new Participants(
                        dealer,
                        List.of(player, new Player("dazzle"))
                );
                final Map<PlayerName, Betting> betting = new HashMap<>();
                betting.put(new PlayerName("toney"), new Betting(10000));
                final BettingTable bettingTable = new BettingTable(betting);
                final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

                final int profit = blackJackGame.getPlayerProfit(new PlayerName("toney"));

                assertThat(profit).isEqualTo(0);
            }

            @Test
            void 딜러에게_진경우_베팅금액만큼_잃는다() {
                final Player player = new Player("toney");
                player.drawCard(new Card(TWO, SPADE));
                player.drawCard(new Card(QUEEN, SPADE));
                final Dealer dealer = new Dealer();
                dealer.drawCard(new Card(TEN, HEART));
                dealer.drawCard(new Card(QUEEN, HEART));
                final Participants participants = new Participants(
                        dealer,
                        List.of(player, new Player("dazzle"))
                );
                final Map<PlayerName, Betting> betting = new HashMap<>();
                betting.put(new PlayerName("toney"), new Betting(10000));
                final BettingTable bettingTable = new BettingTable(betting);
                final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

                final int profit = blackJackGame.getPlayerProfit(new PlayerName("toney"));

                assertThat(profit).isEqualTo(-10000);
            }
        }
    }

    @Test
    void 딜러의_수익은_플레이어_수익과_반대이다() {
        final Player player = new Player("toney");
        player.drawCard(new Card(TEN, SPADE));
        player.drawCard(new Card(QUEEN, SPADE));
        final Dealer dealer = new Dealer();
        dealer.drawCard(new Card(NINE, HEART));
        dealer.drawCard(new Card(QUEEN, HEART));
        final Participants participants = new Participants(
                dealer,
                List.of(player)
        );
        final Map<PlayerName, Betting> betting = new HashMap<>();
        betting.put(new PlayerName("toney"), new Betting(10000));
        final BettingTable bettingTable = new BettingTable(betting);
        final BlackJackGame blackJackGame = new BlackJackGame(participants, bettingTable);

        final int profit = blackJackGame.getDealerProfit();

        assertThat(profit).isEqualTo(-10000);
    }
}
