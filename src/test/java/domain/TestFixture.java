package domain;

import domain.bet.Betting;
import domain.card.DeckMaker;
import domain.card.OneDeckMaker;
import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.card.vo.Suit;
import domain.participants.Dealer;
import domain.participants.Hand;
import domain.participants.Player;
import domain.state.State;
import java.util.ArrayList;
import java.util.List;

public class TestFixture {
    private static final Card KING_CARD = new Card(Rank.KING, Suit.SPADE);
    private static final DeckMaker ONE_DECK_MAKER = new OneDeckMaker();
    private static final List<Card> DEFAULT_CARDS = createCards();


    public static State createDefaultPlayerStateByRank(List<Rank> ranks) {
        return Player.createDefaultStrategy("익명", new Betting(0)).getStartState(createHandByRank(ranks));
    }

    public static State createDefaultDealerState(List<Rank> ranks) {
        return Dealer.createDefaultStrategy().getStartState(createHandByRank(ranks));
    }

    public static Hand createHandByRank(List<Rank> ranks) {
        return new Hand(getCardsByRanks(ranks));
    }

    public static List<Card> createCards() {
        return ONE_DECK_MAKER.make();
    }

    public static List<Card> getCardsByRanks(List<Rank> ranks) {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : ranks) {
            addCardByRank(rank, cards);
        }
        return cards;
    }

    private static void addCardByRank(Rank rank, List<Card> cards) {
        for (Card card : DEFAULT_CARDS) {
            if (card.rank().equals(rank)) {
                cards.add(card);
                return;
            }
        }
    }

    public static Card getCard(int idx) {
        return DEFAULT_CARDS.get(idx);
    }
}
