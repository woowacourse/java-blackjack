package service;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import domain.player.Player;
import domain.player.PlayerGroups;
import domain.player.WinStatus;
import dto.ParticipantResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GameService {
    private static final int CARDS_PER_PLAYER_AT_START = 2;

    private PlayerGroups playerGroups;
    private final CardDeck cardDeck = initCardDeck();

    public void joinPlayers(List<String> playerNames) {
        playerGroups = new PlayerGroups(playerNames);
    }

    public void initAllPlayerCard() {
        for (int i = 0; i < CARDS_PER_PLAYER_AT_START; i++) {
            giveAllPlayerCard();
        }
    }

    public List<Player> getPlayers() {
        return playerGroups.getPlayers();
    }

    private void giveAllPlayerCard() {
        for (Player player : playerGroups.getPlayers()) {
            player.addCard(cardDeck.draw());
        }

        playerGroups.drawDealerCard(cardDeck.draw());
    }

    // 플레이어들 정보 전달
    public List<ParticipantResult> getPlayersStatus() {
        return playerGroups.playersStatus();
    }

    // 히트
    public void hit(Player player) {
        player.addCard(cardDeck.draw());
    }

    // 딜러 히트
    public boolean dealerHit() {
        if (playerGroups.getDealer().isHit()) {
            playerGroups.getDealer().addCard(cardDeck.draw());
            return true;
        }
        return false;
    }

    public Map<String, Integer> getPlayersTotalScore() {
        return playerGroups.getPlayerTotalScore();
    }

    public Map<String, WinStatus> result() {
        return playerGroups.getGameResult();
    }

    public int getPlayerGroupSize() {
        return playerGroups.getPlayerGroupSize();
    }

    public ParticipantResult getDealerResult() {
        return playerGroups.getDealerResult();
    }

    private CardDeck initCardDeck() {
        List<Card> cards = new ArrayList<>();
        for (TrumpSuit suit : TrumpSuit.values()) {
            for (TrumpNumber number : TrumpNumber.values()) {
                cards.add(new Card(suit, number));
            }
        }

        Collections.shuffle(cards);

        return new CardDeck(cards);
    }
}
