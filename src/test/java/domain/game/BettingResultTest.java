package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;
import domain.card.Deck;
import domain.money.Money;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingResultTest {

    private Dealer dealer;
    private Players players;

    @BeforeEach
    void setUp() {
        Deck deck = setDeck();
        dealer = new Dealer();
        setCard(dealer, deck);
        players = setPlayers();
        setPlayersCard(players, deck);
    }

    private Deck setDeck() {
        return TestDeck.withCustomCards(
                new Card(CardType.SPADE, CardNumber.TWO),
                new Card(CardType.SPADE, CardNumber.THREE),
                new Card(CardType.DIAMOND, CardNumber.ACE),
                new Card(CardType.DIAMOND, CardNumber.ACE),
                new Card(CardType.HEART, CardNumber.ACE),
                new Card(CardType.HEART, CardNumber.KING),
                new Card(CardType.CLOVER, CardNumber.ACE),
                new Card(CardType.CLOVER, CardNumber.NINE)
        );
    }

    void setCard(Gamer gamer, Deck deck) {
        gamer.pickCards(deck, 2);
    }

    private void setPlayersCard(Players players, Deck deck) {
        players.pickCardsToPlayer(deck, 2);
    }

    private Players setPlayers() {
        return new Players(List.of(
                new Player(new Name("win"), new Money(100)),
                new Player(new Name("lose"), new Money(100)),
                new Player(new Name("lose2"), new Money(100)))
        );
    }

    @Test
    @DisplayName("Player들의 배팅 결과를 반환한다.")
    void getPlayersResult() {
        Name win = new Name("win");
        Name lose = new Name("lose");
        Name lose2 = new Name("lose2");
        BettingResult bettingResult = new BettingResult(dealer, players);
        Map<Name, Money> playersResult = bettingResult.getPlayersResult();
        Assertions.assertThat(playersResult)
                .isEqualTo(Map.of(
                        win, new Money(150),
                        lose, new Money(-100),
                        lose2, new Money(-100)
                ));
    }

    @Test
    @DisplayName("Dealer의 배팅 결과를 반환한다.")
    void getDealerResult() {
        BettingResult bettingResult = new BettingResult(dealer, players);
        Money dealerResult = bettingResult.getDealerResult();
        Assertions.assertThat(dealerResult).isEqualTo(new Money(50));
    }
}
