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

    public List<DrawnCardsInfo> splitCards(final Dealer dealer,
                                           final Players players,
                                           final CardDeck cardDeck) {
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

    public DrawnCardsInfo drawPlayerCard(final CardDeck cardDeck,
                                         final Player player) {
        player.drawCard(cardDeck.draw());
        return DrawnCardsInfo.toDto(player, player.openDrawnCards());
    }

    public boolean canPlayerDrawMore(final Player player) {
        return player.isDrawable();
    }

    public void drawDealerCard(final CardDeck cardDeck, final Dealer dealer) {
        dealer.drawCard(cardDeck.draw());
    }

    public boolean canDealerDrawMore(final Dealer dealer) {
        return dealer.isDrawable();
    }

    public List<ParticipantResult> getParticipantResults(final Dealer dealer,
                                                         final Players players) {
        List<ParticipantResult> participantResults = new ArrayList<>();
        participantResults.add(ParticipantResult.toDto(dealer, dealer.calculateScore()));
        players.stream()
                .forEach(player -> participantResults.add(ParticipantResult.toDto(player, player.calculateScore())));

        return participantResults;
    }

    public List<WinLoseResult> getWinLoseResults(final Dealer dealer, final Players players) {
        return players.stream()
                .map(player -> calculateWinLose(player, dealer))
                .collect(toList());
    }

    private WinLoseResult calculateWinLose(final Player player,
                                           final Dealer dealer) {
        return new WinLoseResult(player.getName(), player.isWin(dealer));
    }
}
