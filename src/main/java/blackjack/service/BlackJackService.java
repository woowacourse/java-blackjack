package blackjack.service;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackService {

    private Deck deck = Deck.create();
    private Players players;
    private Dealer dealer;
    private GameResult gameResult;

    public void initDealer() {
        this.dealer = new Dealer(getInitCards());
    }

    public void initPlayers(final List<String> requestNames) {
        List<Cards> playerCards = new ArrayList<>();
        for (int i = 0; i < requestNames.size(); i++) {
            playerCards.add(getInitCards());
        }
        this.players = new Players(playerCards, requestNames);
    }

    public void initBettings(Map<String, Double> bettings) {
        players.initBettings(bettings);
    }

    public void initGameResult() {
        gameResult = new GameResult(getPlayersAsList(), getDealer());
    }

    public List<Participant> getParticipantsAsList() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayersAsList());
        return participants;
    }

    public List<Player> getPlayersAsList() {
        return this.players.getPlayersAsList();
    }

    public List<String> getPlayersAsStrings(){
        List<Player> players = this.getPlayersAsList();
        return players.stream()
                .map(player -> player.getNameAsString())
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public void receiveMoreCard(final Participant participant) {
        participant.receiveMoreCard(deck.draw());
    }

    public boolean isDealerBlackJack() {
        return this.dealer.isBlackJack();
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    private Cards getInitCards() {
        List<Card> cards = Arrays.asList(deck.draw(), deck.draw());
        return new Cards(cards);
    }

    public void calculateProfits() {
        players.calculateProfits(gameResult);
        dealer.changeProfit(gameResult);
    }
}
