package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.hand.Hand;
import domain.participant.*;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final GameParticipants participants;

    private BlackjackGame(Deck deck, GameParticipants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static BlackjackGame start(List<PlayerCreationInfo> playerCreationInfos) {
        return start(playerCreationInfos, Deck.createDeck());
    }

    public static BlackjackGame start(List<PlayerCreationInfo> playerCreationInfos, Deck deck) {
        Dealer dealer = Dealer.from(new Hand(initCards(deck)));
        Players players = createPlayers(playerCreationInfos, deck);
        return new BlackjackGame(deck, GameParticipants.of(dealer, players));
    }

    public void addPlayerCard(Player player) {
        player.addHandCard(deck.draw());
    }

    public boolean playDealerTurn() {
        if (participants.cannotDealerDraw()) {
            return false;
        }
        drawDealerCards();
        return true;
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public Players getPlayers() {
        return participants.getPlayers();
    }

    public List<Player> getPlayersValue() {
        return participants.getPlayers().getPlayers();
    }

    private void drawDealerCards() {
        while (getDealer().checkThreshold()) {
            getDealer().addHandCard(deck.draw());
        }
    }

    private static Players createPlayers(List<PlayerCreationInfo> playerCreationInfos, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (PlayerCreationInfo playerCreationInfo : playerCreationInfos) {
            players.add(createPlayer(playerCreationInfo, deck));
        }
        return Players.from(players);
    }

    private static Player createPlayer(PlayerCreationInfo playerCreationInfo, Deck deck) {
        return Player.of(
                playerCreationInfo.getName(),
                new Hand(initCards(deck)),
                playerCreationInfo.getBettingMoney()
        );
    }

    private static List<Card> initCards(Deck deck) {
        List<Card> cards = new ArrayList<>();
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            cards.add(deck.draw());
        }
        return cards;
    }
}
