package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.Participant;
import domain.participant.WinStatus;
import domain.participant.player.Player;
import domain.participant.player.PlayerGroup;
import domain.vo.Name;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private static final int START_CARD_COUNT = 2;
    private final Dealer dealer = new Dealer(new HandCards());
    private final CardDeck cardDeck = new CardDeck();
    private PlayerGroup playerGroup;

    public void registerPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName), new HandCards());
            players.add(player);
        }

        playerGroup = new PlayerGroup(players);
    }

    public void dealDealerCardsOut() {
        dealCardsOut(dealer);
    }

    public void dealPlayerCardsOut() {
        for (Player player : playerGroup.getPlayers()) {
            dealCardsOut(player);
        }
    }

    public void playerHit(Name name) {
        Player player = playerGroup.findByName(name);
        player.drawCard(cardDeck.getCard());
    }

    public boolean isDealerHit() {
        if (dealer.isDealerHit()) {
            dealer.drawCard(cardDeck.getCard());
            return true;
        }
        return false;
    }

    public void decideDealerResult(WinStatus winStatus) {
        dealer.saveResult(winStatus);
    }

    public Dealer getDealer() {
        return dealer;
    }

    private void dealCardsOut(Participant participant) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            participant.drawCard(cardDeck.getCard());
        }
    }

    public Player findByName(Name name) {
        return playerGroup.findByName(name);
    }

    public boolean isBust(Name name) {
        return playerGroup.findByName(name).isBust();
    }

    public WinStatus decidePlayerResult(Name name) {
        return playerGroup.findByName(name).decideWinStatus(dealer);
    }

    public List<Player> getPlayers() {
        return playerGroup.getPlayers();
    }
}
