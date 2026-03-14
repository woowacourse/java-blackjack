package domain;

import domain.bet.Betting;
import domain.card.Hand;
import domain.card.deckMaker.DeckMaker;
import domain.card.deckMaker.OneDeckMaker;
import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.state.Started;
import domain.state.State;
import domain.state.generator.BlackjackGenerator;
import domain.state.generator.BustGenerator;
import domain.state.generator.FinishedStateGenerator;
import java.util.ArrayList;
import java.util.List;

public class TestFixture {
    private static final DeckMaker ONE_DECK_MAKER = new OneDeckMaker();
    private static final List<Card> DEFAULT_CARDS = createCards();
    private static final Betting DEFAULT_BETTING = new Betting(10000);
    private static final List<FinishedStateGenerator> DEFAULT_STATE_GENERATOR =
            List.of(new BlackjackGenerator(), new BustGenerator());

    public static Player createDefaultPlayer() {
        return new Player("익명", DEFAULT_BETTING, Player.getDefaultHitStrategy());
    }

    public static Dealer createDefaultDealer() {
        return new Dealer(Dealer.getDefaultHitStrategy());
    }

    public static List<FinishedStateGenerator> getDefaultStateGenerator() {
        return DEFAULT_STATE_GENERATOR;
    }

    public static State getStartState(List<Rank> ranks) {
        return Started.getStartState(DEFAULT_STATE_GENERATOR, createHandByRank(ranks));
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
