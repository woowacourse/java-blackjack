package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.strategy.SettedDecksGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @DisplayName("딜러와 플레이어에게 카드를 2장씩 준다.")
    @Test
    void prepareCardTest() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card3 = new Card(Symbol.HEART, Rank.NINE);
        Card card4 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card5 = new Card(Symbol.HEART, Rank.NINE);
        Card card6 = new Card(Symbol.SPADE, Rank.QUEEN);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3, card4, card5, card6);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        Name name1 = new Name("lini");
        Name name2 = new Name("kaki");
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(player1, player2));

        Gamers gamers = Gamers.of(players, dealer);
        BlackJackGame blackJackGame = new BlackJackGame(decks);

        // when
        blackJackGame.prepareCards(gamers);

        // then
        assertAll(
                () -> assertThat(gamers.getGamers().get(0).getHand()).hasSize(2),
                () -> assertThat(gamers.getGamers().get(1).getHand()).hasSize(2),
                () -> assertThat(gamers.getGamers().get(2).getHand()).hasSize(2)
        );
    }

    @DisplayName("게이머의 점수가 21 이하이므로 카드를 주는 것을 성공한다.")
    @Test
    void giveCardSuccessTest() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        Name name = new Name("lini");
        Player player = new Player(name);
        Dealer dealer = new Dealer();

        BlackJackGame blackJackGame = new BlackJackGame(decks);

        // when
        boolean dealerResult = blackJackGame.succeededGiving(dealer);
        boolean playerResult = blackJackGame.succeededGiving(player);

        Card expectedDealerCard = card2;
        Card expectedPlayerCard = card1;

        // then
        assertAll(
                () -> assertThat(dealerResult).isTrue(),
                () -> assertThat(playerResult).isTrue(),
                () -> assertThat(dealer.getHand().get(0)).isEqualTo(expectedDealerCard),
                () -> assertThat(player.getHand().get(0)).isEqualTo(expectedPlayerCard)
        );
    }

    @DisplayName("게이머의 점수가 21을 초과하여 카드 주기를 실패한다.")
    @Test
    void giveCardFailureTest() {
        // given
        Card card1 = new Card(Symbol.DIAMOND, Rank.TWO);
        Card card2 = new Card(Symbol.HEART, Rank.NINE);
        Card card3 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card4 = new Card(Symbol.HEART, Rank.BIG_ACE);
        Card card5 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card6 = new Card(Symbol.HEART, Rank.SEVEN);
        Card card7 = new Card(Symbol.SPADE, Rank.QUEEN);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3, card4, card5, card6,
                card7);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        Name name = new Name("lini");
        Player player = new Player(name);
        Dealer dealer = new Dealer();

        BlackJackGame blackJackGame = new BlackJackGame(decks);

        // when
        blackJackGame.succeededGiving(dealer);
        blackJackGame.succeededGiving(dealer);
        boolean dealerResult = blackJackGame.succeededGiving(dealer);

        blackJackGame.succeededGiving(player);
        blackJackGame.succeededGiving(player);
        blackJackGame.succeededGiving(player);
        blackJackGame.succeededGiving(player);
        boolean playerResult = blackJackGame.succeededGiving(player);

        int expectedDealerSize = 2;
        int expectedPlayerSize = 4;

        // then
        assertAll(
                () -> assertThat(dealerResult).isFalse(),
                () -> assertThat(playerResult).isFalse(),
                () -> assertThat(dealer.getHand()).hasSize(expectedDealerSize),
                () -> assertThat(player.getHand()).hasSize(expectedPlayerSize)
        );
    }

    @DisplayName("플레이어의 승패를 결정한다.")
    @Test
    void findResultsTest() {
        // given
        Card card1 = new Card(Symbol.DIAMOND, Rank.EIGHT);
        Card card2 = new Card(Symbol.CLOVER, Rank.BIG_ACE);
        Card card3 = new Card(Symbol.SPADE, Rank.KING);
        Card card4 = new Card(Symbol.CLOVER, Rank.SEVEN);
        Card card5 = new Card(Symbol.SPADE, Rank.EIGHT);
        Card card6 = new Card(Symbol.HEART, Rank.TWO);
        Card card7 = new Card(Symbol.CLOVER, Rank.NINE);
        Card card8 = new Card(Symbol.DIAMOND, Rank.THREE);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3, card4, card5, card6,
                card7, card8);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        Dealer dealer = new Dealer();

        Name name1 = new Name("pobi");
        Name name2 = new Name("jason");
        Player pobi = new Player(name1);
        Player jason = new Player(name2);

        Players players = new Players(List.of(pobi, jason));
        Gamers gamers = Gamers.of(players, dealer);

        BlackJackGame blackJackGame = new BlackJackGame(decks);
        blackJackGame.prepareCards(gamers);

        blackJackGame.succeededGiving(pobi);
        blackJackGame.succeededGiving(dealer);

        // when
        PlayerResults playerResults = blackJackGame.findPlayerResult(gamers);

        // then
        assertAll(
                () -> assertThat(playerResults.getResults().get(pobi)).isEqualTo(Result.WIN),
                () -> assertThat(playerResults.getResults().get(jason)).isEqualTo(Result.LOSE)
        );
    }
}
