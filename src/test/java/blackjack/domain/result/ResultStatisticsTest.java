package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ResultStatisticsTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        dealer = new Dealer(new Cards(cardList));
    }

    @Test
    @DisplayName("플레이어가 딜러보다 점수가 높으면, 플레이어가 승리한다.")
    void winner() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        List<Player> players = new ArrayList<>();
        Player player = new Player(new Cards(cardList), "pobi");
        players.add(player);

        ResultStatistics resultStatistics = new ResultStatistics(new Players(players), dealer);

        assertThat(resultStatistics.getDealerLoseCounts()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 딜러보다 점수가 같으면, 무승부가 발생한다.")
    void drawer() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        List<Player> players = new ArrayList<>();
        Player player = new Player(new Cards(cardList), "pobi");
        players.add(player);

        ResultStatistics resultStatistics = new ResultStatistics(new Players(players), dealer);

        assertThat(resultStatistics.getDealerDrawCounts()).isEqualTo(1);

    }

    @Test
    @DisplayName("플레이어가 딜러보다 점수가 낮으면, 플레이어가 패배한다.")
    void loser() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.TWO));

        List<Player> players = new ArrayList<>();
        Player player = new Player(new Cards(cardList), "pobi");
        players.add(player);

        ResultStatistics resultStatistics = new ResultStatistics(new Players(players), dealer);

        assertThat(resultStatistics.getDealerWinCounts()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 버스트가 나면 플레이어는 항상 패배한다.")
    void bustLoser() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.HEART, Face.JACK));
        cardList.add(new Card(Suit.SPADE, Face.JACK));

        List<Player> players = new ArrayList<>();
        Player player = new Player(new Cards(cardList), "pobi");
        players.add(player);

        ResultStatistics resultStatistics = new ResultStatistics(new Players(players), dealer);

        assertThat(resultStatistics.getDealerWinCounts()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 버스트가 나고 딜러도 버스트가 나면 플레이어가 패배한다.")
    void bothBustLoser() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.HEART, Face.JACK));
        cardList.add(new Card(Suit.SPADE, Face.JACK));

        List<Player> players = new ArrayList<>();
        Player player = new Player(new Cards(cardList), "pobi");
        players.add(player);

        Cards cards = new Cards(dealer.getCardsAsList());
        cards.add(new Card(Suit.HEART, Face.JACK));

        Dealer dealer1 = new Dealer(cards);
        ResultStatistics resultStatistics = new ResultStatistics(new Players(players), dealer1);

        assertThat(resultStatistics.getDealerWinCounts()).isEqualTo(1);
    }
}