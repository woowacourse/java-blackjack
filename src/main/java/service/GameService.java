package service;

import domain.participant.Players;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;

import dto.GameScoreDto;
import dto.GameStartDto;


public class GameService {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck = new Deck();


    public GameService(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public GameStartDto startGame() {
        players.drawInitialCards(deck);
        dealer.drawInitialCards(deck.drawInitialCards());
        return GameStartDto.from(players, dealer);
    }

    public void hit(Participant participant) {
        participant.draw(deck.drawCard());
    }

    public void stay(Participant participant) {
        participant.stay();
    }

    public void endGameImmediately() {
        players.endGameImmediately();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public GameScoreDto getTotalScore() {
        return GameScoreDto.from(players, dealer);
    }
}
