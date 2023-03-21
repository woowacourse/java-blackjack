package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Participants participants;

    public BlackJackGame(List<String> playerNames) {
        this.cardDeck = initDeck();
        this.participants = initParticipants(playerNames);
    }

    private CardDeck initDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffle();
        return cardDeck;
    }

    private Participants initParticipants(List<String> playerNames) {
        Dealer dealer = initDealer();
        Players players = initPlayers(playerNames);
        return new Participants(dealer, players);
    }

    private Dealer initDealer() {
        return new Dealer(getInitCards());
    }

    private Cards getInitCards() {
        return new Cards(new ArrayList<>(List.of(cardDeck.pick(), cardDeck.pick())));
    }

    private Players initPlayers(List<String> playerNames) {
        Players players = Players.of(playerNames);
        for (Player player : players) {
            player.initCards(getInitCards());
        }
        return players;
    }

    public void drawPlayer(String playerName) {
        Card card = cardDeck.pick();
        participants.drawPlayer(playerName, card);
    }

    public void drawDealer() {
        Card card = cardDeck.pick();
        participants.drawDealer(card);
    }

    public boolean canAddCard(String playerName) {
        return participants.canAddCard(playerName);
    }

    public boolean canAddCardToDealer() {
        return participants.canAddCardToDealer();
    }

    public Cards findCards(String playerName) {
        return participants.findCards(playerName);
    }

    public Cards findDealerCards() {
        return participants.findDealerCards();
    }

    public Card findDealerFirstCard() {
        return participants.findFirstDealerCard();
    }

    public GameState judgeGameResult(String playerName) {
        return Judge.gameResult(participants.findDealerCards(), participants.findCards(playerName));
    }
}
