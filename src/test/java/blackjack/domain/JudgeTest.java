package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.dto.BlackjackResult;
import blackjack.dto.PlayerInfo;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("결과 판단")
class JudgeTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private final String bettingAmount = "10000";
    private final List<PlayerInfo> playerInfos = List.of(PlayerInfo.of("choco", bettingAmount));
    private Players players;
    private Player choco;
    private int bustDrawCount = 10;

    @DisplayName("딜러가 버스트된 경우")
    @Nested
    class whenDealerBust {
        @BeforeEach
        void setUp() {
            deck = new Deck(shuffleStrategy);
            deckDrawLoop(6);
            dealer = new Dealer(deck);
            dealer.draw(2);
            bustDealer();
        }

        @DisplayName("플레이어가 버스트된 상황은 딜러는 무승부로 판단한다.")
        @Test
        void drawWhenBustTogether() {
            //given
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            bustPlayerChoco();
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultDraw(result))
                    .isTrue();
        }

        @DisplayName("플레이어가 블랙잭인 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerBlackjack() {
            //given
            deckDrawLoop(3);
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultLose(result))
                    .isTrue();
        }

        @DisplayName("플레이어가 일반 카드인 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerNormal() {
            //given
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultLose(result))
                    .isTrue();
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class whenDealerBlackjack {
        @BeforeEach
        void setUp() {
            deck = new Deck(shuffleStrategy);
            deckDrawLoop(12);
            dealer = new Dealer(deck);
            dealer.draw(2);
        }

        @DisplayName("플레이어가 버스트되면 딜러가 승리한다.")
        @Test
        void winWhenPlayerBust() {
            //given
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            bustPlayerChoco();
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultWin(result))
                    .isTrue();
        }

        @DisplayName("플레이어가 블랙잭이면 무승부로 판단한다.")
        @Test
        void drawWhenPlayerBlackjack() {
            //given
            deckDrawLoop(11);

            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultDraw(result))
                    .isTrue();
        }

        @DisplayName("플레이어가 일반이면 딜러가 승리한다.")
        @Test
        void winWhenPlayerNormal() {
            //given
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultWin(result))
                    .isTrue();
        }
    }

    @DisplayName("딜러가 블랙잭도 아니고, 버스트되지 않은 일반 경우")
    @Nested
    class whenDealerNormal {
        @BeforeEach
        void setUp() {
            deck = new Deck(shuffleStrategy);
            dealer = new Dealer(deck);
            dealer.draw(2);
        }

        @DisplayName("플레이어가 버스트되면 딜러가 승리한다.")
        @Test
        void winWhenPlayerBust() {
            //given
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            bustPlayerChoco();
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultWin(result))
                    .isTrue();
        }

        @DisplayName("플레이어가 블랙잭이면 딜러가 패배한다.")
        @Test
        void loseWhenPlayerBlackjack() {
            //given
            deckDrawLoop(10);

            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultLose(result))
                    .isTrue();
        }

        @DisplayName("플레이어가 일반이면 딜러 카드가 클 경우 딜러가 승리한다.")
        @Test
        void winWhenPlayerNormalWithSmallerScore() {
            //given
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultWin(result))
                    .isTrue();
        }

        @DisplayName("플레이어가 일반이면 딜러 카드가 작을 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerNormalWithBiggerScore() {
            //given
            deckDrawLoop(5);
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultLose(result))
                    .isTrue();
        }

        @DisplayName("플레이어와 점수가 같을 경우 딜러는 무승부로 판정한다.")
        @Test
        void drawWhenPlayerNormalWithSameScore() {
            //given
            deckDrawLoop(3);
            players = Players.of(playerInfos);
            choco = players.getPlayers().get(0);
            IntStream.range(0, 2)
                    .forEach(i -> choco.draw(dealer.draw()));

            //when
            BlackjackResult result = Judge.judge(dealer, players);

            //then
            assertThat(isDealerResultDraw(result))
                    .isTrue();
        }
    }

    private void bustPlayerChoco() {
        IntStream.range(0, bustDrawCount)
                .forEach(i -> choco.draw(dealer.draw()));
    }

    private void bustDealer() {
        dealer.requestExtraCard();
    }

    private void deckDrawLoop(final int count) {
        IntStream.range(0, count)
                .forEach(i -> deck.draw());
    }

    private boolean isDealerResultWin(final BlackjackResult blackjackResult) {
        return blackjackResult.dealerProfit().equals(bettingAmount);
    }

    private boolean isDealerResultLose(final BlackjackResult blackjackResult) {
        return blackjackResult.dealerProfit().equals("-" + bettingAmount);
    }

    private boolean isDealerResultDraw(final BlackjackResult blackjackResult) {
        return blackjackResult.dealerProfit().equals("0");
    }
}
