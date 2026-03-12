package domain;

import domain.bet.Betting;
import domain.card.DeckMaker;
import domain.card.OneDeckMaker;
import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.participants.Dealer;
import domain.participants.Hand;
import domain.participants.Player;
import java.util.ArrayList;
import java.util.List;

public class TestFixture {
    private static final DeckMaker ONE_DECK_MAKER = new OneDeckMaker();
    private static final List<Card> DEFAULT_CARDS = createCards();
    private static final Betting DEFAULT_BETTING = new Betting(10000);

    public static Player createDefaultPlayerStateByRank(List<Rank> ranks) {
        return Player.createDefaultStrategy("익명", createHandByRank(ranks), DEFAULT_BETTING);
    }

    public static Dealer createDefaultDealer(List<Rank> ranks) {
        return Dealer.createDefaultStrategy(createHandByRank(ranks));
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
}
