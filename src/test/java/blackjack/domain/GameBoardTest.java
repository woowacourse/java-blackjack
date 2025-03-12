package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.FixCardsShuffler;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participants.BattingMoney;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @Test
    void 블랙잭_게임을_준비한다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.ONE),
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.DIAMOND, Rank.ACE)
        );
        Dealer dealer = new Dealer(new Cards());
        Players players = new Players(new Player("pobi", new Cards(), new BattingMoney(0)),
                new Player("neo", new Cards(), new BattingMoney(0)));
        GameBoard gameBoard = new GameBoard(deck, dealer, players);

        //when
        gameBoard.prepareGame(new FixCardsShuffler());

        //then
        assertAll(
                () -> assertThat(dealer).isEqualTo(new Dealer(new Cards(
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.ONE)
                ))),
                () -> assertThat(players).isEqualTo(new Players(
                        new Player("pobi", new Cards(
                                new Card(Suit.CLUB, Rank.ACE),
                                new Card(Suit.DIAMOND, Rank.EIGHT)
                        ), new BattingMoney(0)),
                        new Player("neo", new Cards(
                                new Card(Suit.DIAMOND, Rank.NINE),
                                new Card(Suit.DIAMOND, Rank.ACE)
                        ), new BattingMoney(0))
                ))
        );
    }

    @Test
    void 플레이어에게_카드를_뽑게_한다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.TEN)
        );
        Player pobi = new Player("pobi", new Cards(), new BattingMoney(1000));
        GameBoard gameBoard = new GameBoard(deck, new Dealer(new Cards()), new Players(
                pobi,
                new Player("neo", new Cards(), new BattingMoney(1000))
        ));

        //when
        gameBoard.drawCard(pobi);

        //then
        assertThat(pobi).isEqualTo(
                new Player("pobi", new Cards(new Card(Suit.CLUB, Rank.TEN)), new BattingMoney(1000)));
    }

    @Test
    void 달러에게_카드를_뽑게_한다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.ACE)
        );
        Dealer dealer = new Dealer(new Cards());
        GameBoard gameBoard = new GameBoard(deck, dealer, new Players(
                new Player("pobi", new Cards(), new BattingMoney(1000)),
                new Player("neo", new Cards(), new BattingMoney(1000))
        ));

        //when
        gameBoard.drawAdditionalCardOfDealer();

        //then
        assertThat(dealer).isEqualTo(new Dealer(new Cards(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.ACE)
        )));
    }
}
