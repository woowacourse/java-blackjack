package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        dealer = new Dealer(new Cards(cardList));
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러의 점수보다 높으면, 승리를 반환한다.")
    void getPlayerResultIsWinner() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        Player player = new Player(new Cards(cardList), "pobi");

        assertThat(Result.getPlayerResult(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러의 점수와 같으면, 무승부를 반환한다.")
    void getPlayerResultIsDrawer() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        Player player = new Player(new Cards(cardList), "pobi");

        assertThat(Result.getPlayerResult(player, dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러의 점수보다 낮으면, 패배를 반환한다.")
    void getPlayerResultIsLoser() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));

        Player player = new Player(new Cards(cardList), "pobi");

        assertThat(Result.getPlayerResult(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어가 bust되면, 플레이어의 패배를 반환한다.")
    void getPlayerResultIsLoserWhenPlayerIsBusted() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));

        Player player = new Player(new Cards(cardList), "pobi");

        assertThat(Result.getPlayerResult(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 bust되면, 플레이어의 승리를 반환한다.")
    void getPlayerResultIsWinnerWhenDealerIsBusted() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        dealer.receiveMoreCard(new Card(Suit.DIAMOND, Face.QUEEN));

        Player player = new Player(new Cards(cardList), "pobi");

        assertThat(Result.getPlayerResult(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 bust되면, 딜러의 승리를 반환한다.")
    void getPlayerResultWhenBothAreBusted() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.QUEEN));

        Player player = new Player(new Cards(cardList), "pobi");

        assertThat(Result.getPlayerResult(player, dealer)).isEqualTo(Result.LOSE);
    }
}
