package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BlackJack {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackJack(List<String> names) {
        List<Player> players = names.stream().map(Player::init).toList();
        deck = new Deck(new RandomCardsGenerator());
        this.dealer = Dealer.init();
        this.players = new Players(players);
    }

    public void handoutCards() {
        deck.handoutCards(dealer, players);
    }

    public void giveOneCardTo(Participant participant) {
        deck.giveCardTo(participant);
    }

    public List<Card> getShowDealerCards() {
        return dealer.getShowCards();
    }

    public boolean canDraw(Participant participant) {
        return !participant.isBurst() && !participant.isWin();
    }

    public int getDealerDrawnCount() {
        return deck.drawCardTo(dealer);
    }

    public int getDealerScore() {
        return dealer.getCardScore();
    }

    public Cards getDealerCards() {
        return dealer.getCards();
    }

    public Map<Player, GameResult> getPlayersResult() {
        return dealer.getGameResult(players);
    }

    public Map<GameResult, Integer> getDealerResult() {
        return dealer.getResult();
    }

    public Set<Player> getPlayers() {
        return players.getPlayers();
    }
}
