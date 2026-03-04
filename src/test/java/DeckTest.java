import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    void 카드를_52장_생성한다() {
        Deck deck = new Deck();
        deck.init();

        int deckSize = deck.size();

        assertThat(deckSize).isEqualTo(52);
    }

    @Test
    void 카드는_숫자순에서_무늬순으로_생성된다() {
        Deck deck = new Deck();
        deck.init();
        int deckSize = deck.size();

        List<Card> result = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck.draw();
            result.add(card);
        }

        assertThat(result.toString()).contains(
                "[ACESPADE, TWOSPADE, THREESPADE, FOURSPADE, FIVESPADE, SIXSPADE, SEVENSPADE, EIGHTSPADE, " +
                        "NINESPADE, TENSPADE, JACKSPADE, QUEENSPADE, KINGSPADE, ACEHEART, TWOHEART, THREEHEART, " +
                        "FOURHEART, FIVEHEART, SIXHEART, SEVENHEART, EIGHTHEART, NINEHEART, TENHEART, JACKHEART," +
                        " QUEENHEART, KINGHEART, ACEDIAMOND, TWODIAMOND, THREEDIAMOND, FOURDIAMOND, FIVEDIAMOND," +
                        " SIXDIAMOND, SEVENDIAMOND, EIGHTDIAMOND, NINEDIAMOND, TENDIAMOND, JACKDIAMOND," +
                        " QUEENDIAMOND, KINGDIAMOND, ACECLUB, TWOCLUB, THREECLUB, FOURCLUB, FIVECLUB, SIXCLUB," +
                        " SEVENCLUB, EIGHTCLUB, NINECLUB, TENCLUB, JACKCLUB, QUEENCLUB, KINGCLUB]"
        );
    }

    @Test
    void 카드_셔플_테스트() {
        Deck deck1 = new Deck();
        deck1.init();
        deck1.shuffle();
        int deckSize = deck1.size();

        List<Card> result1 = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck1.draw();
            result1.add(card);
        }

        Deck deck2 = new Deck();
        deck2.init();

        List<Card> result2 = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck2.draw();
            result2.add(card);
        }

        assertThat(result1.size()).isEqualTo(result2.size());
        assertThat(result1).containsExactlyInAnyOrderElementsOf(result2);
    }

    @Test
    void 등록된_플레이어와_딜러_순서대로_카드를_돌린다() {
        Player player1 = new Player("pobi");
        Player player2 = new Player("cary");

        Players players = new Players();
        players.add(player1);
        players.add(player2);
        List<Player> records = players.getPlayers();
        Dealer dealer = new Dealer();
        GameManager manager = new GameManager();

        manager.dealCards(records, dealer);

        assertThat(player1.getHand().size()).isEqualTo(2);
        assertThat(player2.getHand().size()).isEqualTo(2);
        assertThat(dealer.getHand().size()).isEqualTo(2);
    }
}
