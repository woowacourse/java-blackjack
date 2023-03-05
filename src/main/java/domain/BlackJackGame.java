package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.*;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(List<String> playerNames) {
        this.deck = InitGameSetter.generateDeck();
        Players players = InitGameSetter.generatePlayers(deck, playerNames);
        Dealer dealer = InitGameSetter.generateDealer(deck);
        this.participants = new Participants(players, dealer);
    }
}
