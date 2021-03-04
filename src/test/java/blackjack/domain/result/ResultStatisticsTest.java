package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.Challengers;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
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
    @DisplayName("챌린저가 딜러보다 점수가 높으면, 챌린저가 승리한다.")
    void winner() {
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Suit.DIAMOND, Face.ACE));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);

        assertThat(resultStatistics.getDealerLoses()).isEqualTo(1);
    }

    @Test
    @DisplayName("챌린저가 딜러보다 점수가 같으면, 무승부가 발생한다.")
    void drawer() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);

        assertThat(resultStatistics.getDealerDraws()).isEqualTo(1);

    }

    @Test
    @DisplayName("챌린저가 딜러보다 점수가 낮으면, 챌린저가 패배한다.")
    void loser() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.TWO));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);

        assertThat(resultStatistics.getDealerWins()).isEqualTo(1);
    }

    @Test
    @DisplayName("챌린저가 버스트가 나면 챌린저는 항상 패배한다.")
    void bustLoser() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.HEART, Face.JACK));
        cardList.add(new Card(Suit.SPADE, Face.JACK));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challengers.add(challenger);

        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer);

        assertThat(resultStatistics.getDealerWins()).isEqualTo(1);
    }

    @Test
    @DisplayName("챌린저가 버스트가 나고 딜러도 버스트가 나면 챌린저가 패배한다.")
    void bothBustLoser() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.HEART, Face.JACK));
        cardList.add(new Card(Suit.SPADE, Face.JACK));

        List<Challenger> challengers = new ArrayList<>();
        Challenger challenger = new Challenger(new Cards(cardList), new Name("pobi"));
        challengers.add(challenger);

        Cards cards = new Cards(dealer.getCards());
        cards.add(new Card(Suit.HEART, Face.JACK));

        Dealer dealer1 = new Dealer(cards);
        ResultStatistics resultStatistics = new ResultStatistics(new Challengers(challengers), dealer1);

        assertThat(resultStatistics.getDealerWins()).isEqualTo(1);
    }
}