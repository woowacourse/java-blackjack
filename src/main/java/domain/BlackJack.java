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
    private final Dealer dealer;
    private final Players players;

    public BlackJack(List<String> names) {
        List<Player> players = names.stream().map(Player::init).toList();
        Deck deck = new Deck(new RandomCardsGenerator());
        this.dealer = new Dealer(Cards.empty(), deck);
        this.players = new Players(players);
    }

    public void handoutCards() {
        dealer.handoutCards(players);
    }

    public void giveOneCardTo(Participant participant) {
        dealer.giveOneCardTo(participant);
    }

    public List<Card> getShowDealerCards() {
        return dealer.getShowCards();
    }

    public boolean canDraw(Participant participant) {
        return !participant.isBust() && !participant.isWin();
    }

    public int getDealerDrawnCount() {
        return dealer.drawCards();
    }

    public int getDealerScore() {
        return dealer.getCardScore();
    }

    public Cards getDealerCards() {
        return dealer.getCards();
    }

    public Map<Player, GameResult> getPlayersResult() {
        return dealer.getPlayerResult(players);
    }

    public Map<GameResult, Integer> getDealerResult() {
        return dealer.getDealerResult(players);
    }

    public Set<Player> getPlayers() {
        return players.get();
    }
}
