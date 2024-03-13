package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import domain.constant.GamerResult;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultTest {
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
        return Deck.withCustomCards(
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
                new Player(new Name("win")),
                new Player(new Name("lose")),
                new Player(new Name("lose2"))));
    }


    @Test
    @DisplayName("Player들의 승리 결과를 반환한다.")
    void getPlayersResult() {
        Name win = new Name("win");
        Name lose = new Name("lose");
        Name lose2 = new Name("lose2");
        BlackJackResult blackJackResult = new BlackJackResult(dealer, players);
        Map<Name, GamerResult> playersResult = blackJackResult.getPlayersResult();
        Assertions.assertThat(playersResult)
                .isEqualTo(Map.of(
                        win, GamerResult.WIN,
                        lose, GamerResult.LOSE,
                        lose2, GamerResult.LOSE
                ));
    }

    @Test
    @DisplayName("Dealer의 승리 결과를 반환한다.")
    void getDealerResult() {
        BlackJackResult blackJackResult = new BlackJackResult(dealer, players);
        Map<GamerResult, Integer> dealerResult = blackJackResult.getDealerResult();
        Assertions.assertThat(dealerResult)
                .isEqualTo(Map.of(
                        GamerResult.WIN, 2,
                        GamerResult.LOSE, 1));
    }
}
