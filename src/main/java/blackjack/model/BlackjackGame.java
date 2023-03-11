package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }


    public BlackjackGame(Player... players) {
        this.players = new Players(players);
        this.dealer = new Dealer();
    }

    public void distributeCards(CardDeck cardDeck) {
        players.distributeFirstCards(cardDeck);
        dealer.drawFirstTurnCards(cardDeck);
    }

    public void drawPlayerCard(CardDeck cardDeck, int playerId) {
        players.hit(cardDeck, playerId);
    }

    public void drawDealerCard(CardDeck cardDeck) {
        dealer.draw(cardDeck);
    }

    public Map<String, WinningResult> participantProfits() {
        return dealer.participantWinningResults(players);
    }

    public boolean isPlayerFinished(int playerId) {
        return players.isPlayerFinished(playerId);
    }

    public String getPlayerScoreResult(int playerId, String blackjackMessage) {
        String scoreResult = Integer.toString(players.getScoreById(playerId));
        if (players.isBlackjack(playerId)) {
            scoreResult += blackjackMessage;
        }
        return scoreResult;
    }

    public Map<String, List<String>> getPlayersFirstDistributed() {
        return nameAndHandCard(players.firstDistributedCards());
    }

    public Map<String, List<String>> getPlayerNameHandCard(int playerId) {
        return getScoreResult(players.getHandCardsById(playerId));
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public String getPlayerName(int playerId) {
        return players.getNameById(playerId);
    }


    public boolean isDealerFinished() {
        return dealer.isFinished();
    }

    public Map<String, List<String>> getDealerNameHand() {
        return getScoreResult(dealer.getCardUnit());
    }

    public Map<String, List<String>> getDealerFirstDistributed() {
        return nameAndHandCard(dealer.firstDistributedCard());
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public String getDealerScoreResult(String blackjackMessage) {
        String scoreResult = Integer.toString(dealer.cardScore().getScore());
        if (dealer.isBlackjack()) {
            scoreResult += blackjackMessage;
        }
        return scoreResult;
    }

    private Map<String, List<String>> nameAndHandCard(Map<String, List<Card>> handCards) {
        Map<String, List<String>> handCardUnits = new HashMap<>();

        for (Map.Entry<String, List<Card>> handCard : handCards.entrySet()) {
            List<String> cardUnits = handCard.getValue().stream().map(Card::cardUnit)
                    .collect(Collectors.toList());
            handCardUnits.put(handCard.getKey(), cardUnits);
        }
        return handCardUnits;
    }

    public Map<String, List<String>> getScoreResult(Map<String, List<Card>> handCards) {
        Map<String, List<String>> handCardUnits = new HashMap<>();

        for (Map.Entry<String, List<Card>> handCard : handCards.entrySet()) {
            List<String> cardUnits = handCard.getValue().stream().map(Card::cardUnit)
                    .collect(Collectors.toList());
            handCardUnits.put(handCard.getKey(), cardUnits);
        }
        return handCardUnits;
    }

    public int getPlayerId(String name) {
        return players.getIdByName(name);
    }

    public List<Integer> getPlayerIds() {
        return players.getPlayerIds();
    }
}
