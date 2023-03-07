package blackjack.blackjacGameTest;

import static blackjack.domain.game.WinningResult.LOSE;
import static blackjack.domain.game.WinningResult.TIE;
import static blackjack.domain.game.WinningResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Map;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.fixedCaradsGenerator.FixedCardsGenerator;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.WinningResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.deck.CardsGenerator;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.dealer.DealerWinningDto;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.player.Player;
import blackjack.domain.participant.player.Players;

class BlackjackGameTest {
    BlackjackGame blackjackGame;
    Players players;
    Dealer dealer;
    Deck deck;

    @BeforeEach
    void setUp() {

        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator();
        deck = new Deck(fixedCardsGenerator);
        players = new Players();
        dealer = new Dealer();
        blackjackGame = new BlackjackGame(players, dealer, deck);
    }

    @Test
    @DisplayName("플레이어를 만들고 플레이어즈에 추가할 수 있다")
    void addPlayer() {
        Player polo = new Player(new Name("폴로"));
        blackjackGame.addPlayer(polo);

        assertThat(blackjackGame).extracting("players")
                .isNotSameAs(players);
    }

    @Test
    @DisplayName("딜러에게 카드를 두 장 줄 수 있다.")
    void supplyCardsToDealer() {
        blackjackGame.supplyCardsToDealer();

        assertThat(dealer.showCards())
                .contains(new Card(CardNumber.ACE, Pattern.DIAMOND), new Card(CardNumber.ACE, Pattern.SPADE));
    }

    @Test
    @DisplayName("플레이어들에게 카드를 두 장씩 줄 수 있다.")
    void supplyCardsToPlayers() {
        Player player1 = new Player(new Name("폴로"));
        Player player2 = new Player(new Name("로지"));
        blackjackGame.addPlayer(player1);
        blackjackGame.addPlayer(player2);

        blackjackGame.supplyCardsToPlayers();

        assertThat(player1.showCards()).contains(new Card(CardNumber.ACE, Pattern.DIAMOND),
                new Card(CardNumber.ACE, Pattern.SPADE));
        assertThat(player2.showCards()).contains(new Card(CardNumber.ACE, Pattern.CLOVER),
                new Card(CardNumber.ACE, Pattern.HEART));

    }

    @DisplayName("딜러에게 추가 카드를 주는 기능")
    @Nested
    class SupplyAdditionalCardToDealer {
        @Test
        @DisplayName("언더 스코어가 아니면 카드를 주지 않는다.")
        void doesNotSupply() {
            dealer.hit(Arrays.asList(new Card(CardNumber.KING, Pattern.SPADE), new Card(CardNumber.KING, Pattern.HEART)));
            int beforeSize = dealer.showCards().size();
            blackjackGame.supplyAdditionalCardToDealerAnd(ignore -> {});
            int afterSize = dealer.showCards().size();

            assertThat(afterSize).isEqualTo(beforeSize);
        }

        @Test
        @DisplayName("언더 스코어 이면 카드를 받는다.")
        void supply() {
            dealer.hit(Arrays.asList(new Card(CardNumber.KING, Pattern.HEART), new Card(CardNumber.SIX, Pattern.DIAMOND)));
            int beforeSize = dealer.showCards().size();
            blackjackGame.supplyAdditionalCardToDealerAnd(ignore -> {});
            int afterSize = dealer.showCards().size();

            assertThat(afterSize - beforeSize).isEqualTo(1);
        }
    }

    @Test
    @DisplayName("딜러의 게임 결과를 반환할 수 있다.")
    void getDealerWinningResult() {
        dealer.win();
        dealer.win();
        dealer.lose();
        dealer.tie();

        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        Name name = dealerWinningResult.getName();
        Map<WinningResult, Integer> dealerResult = dealerWinningResult.getResultToCount();

        assertThat(name.getValue()).isEqualTo("딜러");
        assertThat(dealerResult.get(WIN)).isEqualTo(2);
        assertThat(dealerResult.get(LOSE)).isEqualTo(1);
        assertThat(dealerResult.get(TIE)).isEqualTo(1);
    }
}
