package service;

import static java.util.stream.Collectors.toList;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.request.DrawCommand;
import dto.response.DealerWinLoseResult;
import dto.response.DrawnCardsInfo;
import dto.response.ParticipantResult;
import dto.response.WinLoseResult;
import java.util.ArrayList;
import java.util.List;

public class BlackJackService {

    private static final int NUMBER_OF_SPLIT_CARDS = 2;

    public List<DrawnCardsInfo> splitCards(final Dealer dealer,
                                           final Players players,
                                           final CardDeck cardDeck) {
        splitCards(dealer, cardDeck);
        players.stream()
                .forEach(player -> splitCards(player, cardDeck));

        return getDrawnCardsInfos(dealer, players);
    }

    private void splitCards(final Participant participant, final CardDeck cardDeck) {
        for (int i = 0; i < NUMBER_OF_SPLIT_CARDS; i++) {
            participant.pickCard(cardDeck.draw());
        }
    }

    private List<DrawnCardsInfo> getDrawnCardsInfos(final Dealer dealer,
                                                    final Players players) {
        List<DrawnCardsInfo> cardInfos = new ArrayList<>();
        addDealerCardInfo(dealer, cardInfos);

        players.stream()
                .forEach(player -> cardInfos.add(DrawnCardsInfo.toDto(player)));

        return cardInfos;
    }

    public DrawnCardsInfo drawCardByCommand(final CardDeck cardDeck,
                                            final Player player,
                                            final DrawCommand drawCommand) {
        if (drawCommand.isDraw()) {
            player.pickCard(cardDeck.draw());
        }

        return DrawnCardsInfo.toDto(player);
    }

    public boolean canPlayerDrawMore(final Player player, final DrawCommand drawCommand) {
        if (!player.isDrawable() || drawCommand.isStop()) {
            return false;
        }

        return true;
    }

    public void drawDealerCard(final CardDeck cardDeck, final Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.pickCard(cardDeck.draw());
        }
    }

    public boolean canDealerDrawMore(final Dealer dealer) {
        return dealer.isDrawable();
    }

    public List<ParticipantResult> getParticipantResults(final Dealer dealer,
                                                         final Players players) {
        List<ParticipantResult> participantResults = new ArrayList<>();
        participantResults.add(ParticipantResult.toDto(dealer, dealer.calculateCardScore()));

        players.stream()
                .forEach(
                        player -> participantResults.add(ParticipantResult.toDto(player, player.calculateCardScore())));

        return participantResults;
    }

    private void addDealerCardInfo(final Dealer dealer,
                                   final List<DrawnCardsInfo> cardInfos) {
        cardInfos.add(DrawnCardsInfo.toDto(dealer));
    }

    public List<WinLoseResult> getWinLoseResults(final Dealer dealer, final Players players) {
        return players.stream()
                .map(player -> calculateWinLose(player, dealer))
                .collect(toList());
    }

    private WinLoseResult calculateWinLose(final Player player,
                                           final Dealer dealer) {
        if (player.isBurst()) {
            return WinLoseResult.toDto(player, false);
        }

        if (dealer.isBurst()) {
            return WinLoseResult.toDto(player, true);

        }

        boolean isPlayerWin = player.calculateCardScore() > dealer.calculateCardScore();
        return WinLoseResult.toDto(player, isPlayerWin);
    }

    public DealerWinLoseResult getDealerResult(final List<WinLoseResult> winLoseResults, final Dealer dealer) {
        int dealerLoseCount = (int) winLoseResults.stream()
                .filter(WinLoseResult::isWin)
                .count();
        int dealerWinCount = winLoseResults.size() - dealerLoseCount;

        return DealerWinLoseResult.toDto(dealer, dealerWinCount, dealerLoseCount);
    }
}
