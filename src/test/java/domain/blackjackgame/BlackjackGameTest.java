package domain.blackjackgame;

import static fixture.CardFixture.카드;
import static fixture.CardFixture.카드들;
import static fixture.PlayersFixture.플레이어;
import static fixture.PlayersFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.card.CardShuffleStrategy;
import domain.card.Denomination;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackGameTest {
    private final CardShuffleStrategy cardNoShuffleStrategy = cards -> {
    };

    @Test
    void 게임을_시작하면_딜러와_플레이어들에게_카드를_두_장씩_나눠준다() {
        BlackjackGame blackjackGame = new BlackjackGame(CardFactory.createCardDeck(), cardNoShuffleStrategy);
        Dealer dealer = new Dealer();
        Players players = 플레이어들("뽀로로", "프린", "포비");

        blackjackGame.initGame(dealer, players);
        List<Player> playerList = players.getPlayers();
        assertAll(
                () -> assertThat(dealer.getAllCards()).hasSize(2),
                () -> assertThat(playerList.get(0).getAllCards()).hasSize(2),
                () -> assertThat(playerList.get(1).getAllCards()).hasSize(2),
                () -> assertThat(playerList.get(2).getAllCards()).hasSize(2)
        );
    }

    @Nested
    class 플레이어가_bust일_때 {
        @Test
        void 딜러가_bust이면_진다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.KING), 카드(Denomination.KING), 카드(Denomination.QUEEN), 카드(Denomination.TEN),
                            카드(Denomination.JACK), 카드(Denomination.TEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);
            blackjackGame.dealCardTo(players.getPlayers().get(0));
            blackjackGame.dealCardTo(dealer);

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.LOSE);
        }

        @Test
        void 딜러가_blackjack이면_진다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.THREE), 카드(Denomination.QUEEN), 카드(Denomination.TEN), 카드(Denomination.ACE),
                            카드(Denomination.TEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);
            blackjackGame.dealCardTo(players.getPlayers().get(0));

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.LOSE);
        }

        @Test
        void 딜러가_일반_점수이면_진다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.THREE), 카드(Denomination.QUEEN), 카드(Denomination.TEN), 카드(Denomination.KING),
                            카드(Denomination.SEVEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);
            blackjackGame.dealCardTo(players.getPlayers().get(0));

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.LOSE);
        }
    }

    @Nested
    class 플레이어가_blackjack일_때 {
        @Test
        void 딜러가_bust이면_이긴다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.KING), 카드(Denomination.ACE), 카드(Denomination.TEN),
                            카드(Denomination.JACK), 카드(Denomination.TEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);
            blackjackGame.dealCardTo(dealer);

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.WIN);
        }

        @Test
        void 딜러가_blackjack이면_무승부이다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.ACE), 카드(Denomination.TEN), 카드(Denomination.ACE),
                            카드(Denomination.TEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.DRAW);
        }

        @Test
        void 딜러가_일반_점수이면_이긴다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.ACE), 카드(Denomination.TEN), 카드(Denomination.JACK), 카드(Denomination.TEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.WIN);
        }

        @Test
        void 딜러가_일반_점수_21이면_이긴다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.SIX), 카드(Denomination.ACE), 카드(Denomination.TEN), 카드(Denomination.FIVE),
                            카드(Denomination.QUEEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);
            blackjackGame.dealCardTo(dealer);

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.WIN);
        }
    }

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class 플레이어가_stay일_때 {
        @Test
        void 딜러가_bust이면_이긴다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.KING), 카드(Denomination.QUEEN), 카드(Denomination.TEN),
                            카드(Denomination.JACK), 카드(Denomination.TEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);
            blackjackGame.dealCardTo(dealer);

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.WIN);
        }

        @Test
        void 딜러가_blackjack이면_진다() {
            CardDeck cardDeck = new CardDeck(
                    카드들(카드(Denomination.QUEEN), 카드(Denomination.TEN), 카드(Denomination.ACE), 카드(Denomination.TEN)));
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();
            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), ResultStatus.LOSE);
        }

        @ParameterizedTest
        @MethodSource("provideCardsAndResult")
        void 딜러가_일반_점수이면_점수가_높은_쪽이_이긴다(List<Card> cards, ResultStatus expected) {
            CardDeck cardDeck = new CardDeck(cards);
            BlackjackGame blackjackGame = new BlackjackGame(cardDeck, cardNoShuffleStrategy);
            Dealer dealer = new Dealer();

            Players players = 플레이어들("프린");
            blackjackGame.initGame(dealer, players);

            GameResult result = blackjackGame.createGameResult(dealer, players);
            assertThat(result.getPlayerResult()).containsEntry(플레이어("프린"), expected);
        }

        private Stream<Arguments> provideCardsAndResult() {
            return Stream.of(
                    Arguments.of(
                            카드들(카드(Denomination.TEN), 카드(Denomination.NINE), 카드(Denomination.ACE),
                                    카드(Denomination.SEVEN)),
                            ResultStatus.WIN
                    ),
                    Arguments.of(
                            카드들(카드(Denomination.TEN), 카드(Denomination.KING), 카드(Denomination.QUEEN),
                                    카드(Denomination.JACK)),
                            ResultStatus.DRAW
                    ),
                    Arguments.of(
                            카드들(카드(Denomination.TWO), 카드(Denomination.THREE), 카드(Denomination.QUEEN),
                                    카드(Denomination.KING)),
                            ResultStatus.LOSE
                    )
            );
        }
    }
}
