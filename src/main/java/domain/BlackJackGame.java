package domain;

import static java.util.stream.Collectors.toList;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.response.DrawnCardsInfo;
import dto.response.ParticipantResult;
import dto.response.WinLoseResult;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private static final int NUMBER_OF_SPLIT_CARDS = 2;

    private final Players players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackGame(Players players, Dealer dealer, CardDeck cardDeck) {
        this.players = players;
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public List<DrawnCardsInfo> splitCards() {
        splitEachParticipant(dealer, cardDeck);
        players.stream()
                .forEach(player -> splitEachParticipant(player, cardDeck));

        return getDrawnCardsInfos(dealer, players);
    }

    private void splitEachParticipant(final Participant participant, final CardDeck cardDeck) {
        for (int i = 0; i < NUMBER_OF_SPLIT_CARDS; i++) {
            participant.drawCard(cardDeck.draw());
        }
    }

    private List<DrawnCardsInfo> getDrawnCardsInfos(final Dealer dealer,
                                                    final Players players) {
        List<DrawnCardsInfo> cardInfos = new ArrayList<>();
        cardInfos.add(DrawnCardsInfo.toDto(dealer, dealer.openDrawnCards()));

        players.stream()
                .forEach(player -> cardInfos.add(DrawnCardsInfo.toDto(player, player.openDrawnCards())));

        return cardInfos;
    }

    public DrawnCardsInfo drawPlayerCardByName(final String playerName) {
        Player player = players.findPlayerByName(playerName);
        player.drawCard(cardDeck.draw());
        return DrawnCardsInfo.toDto(player, player.openDrawnCards());
    }

    public boolean canPlayerDrawMore(final String playerName) {
        Player player = players.findPlayerByName(playerName);
        return player.isDrawable();
    }

    public void drawDealerCard() {
        dealer.drawCard(cardDeck.draw());
    }

    public boolean canDealerDrawMore() {
        return dealer.isDrawable();
    }

    public List<ParticipantResult> getParticipantResults() {
        List<ParticipantResult> participantResults = new ArrayList<>();
        participantResults.add(ParticipantResult.toDto(dealer, dealer.calculateScore()));
        players.stream()
                .forEach(player -> participantResults.add(ParticipantResult.toDto(player, player.calculateScore())));

        return participantResults;
    }

    public List<WinLoseResult> getWinLoseResults() {
        return players.stream()
                .map(player -> new WinLoseResult(player.getName(), player.isWin(dealer)))
                .collect(toList());
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Participant::getName)
                .collect(toList());
    }

    public String getDealerName() {
        return dealer.getName();
    }
}
