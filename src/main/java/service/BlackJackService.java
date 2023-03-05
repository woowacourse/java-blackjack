package service;

import static java.util.stream.Collectors.toList;

import domain.CardDeck;
import domain.Dealer;
import domain.DrawCommand;
import domain.Participant;
import domain.Player;
import domain.Players;
import dto.DealerWinLoseResult;
import dto.DrawnCardsInfo;
import dto.ParticipantResult;
import dto.WinLoseResult;
import java.util.ArrayList;
import java.util.List;

public class BlackJackService {

    private static final int NUMBER_OF_SPLIT_CARDS = 2;
    private static final int BURST_NUMBER = 21;
    private static final int DEALER_DRAW_LIMIT_SCORE = 16;

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

    public DrawnCardsInfo drawCards(final CardDeck cardDeck,
                                    final Player player,
                                    final DrawCommand drawCommand) {
        if (drawCommand.isDraw()) {
            player.pickCard(cardDeck.draw());
        }

        return DrawnCardsInfo.toDto(player);
    }

    public boolean canDrawMore(final Player player, final DrawCommand drawCommand) {
        if (player.calculateCardScore() > BURST_NUMBER || drawCommand.isStop()) {
            return false;
        }

        return true;
    }

    public void pickDealerCard(final CardDeck cardDeck, final Dealer dealer) {
        if (dealer.calculateCardScore() <= DEALER_DRAW_LIMIT_SCORE) {
            dealer.pickCard(cardDeck.draw());
        }
    }

    public boolean canDealerDrawMore(final Dealer dealer) {
        if (dealer.calculateCardScore() > DEALER_DRAW_LIMIT_SCORE) {
            return false;
        }

        return true;
    }

    public List<ParticipantResult> getParticipantResults(final Dealer dealer,
                                                         final Players players) {
        List<ParticipantResult> participantResults = new ArrayList<>();
        participantResults.add(ParticipantResult.toDto(dealer));

        players.stream()
                .forEach(player -> participantResults.add(ParticipantResult.toDto(player)));

        return participantResults;
    }

    private void addDealerCardInfo(final Dealer dealer,
                                   final List<DrawnCardsInfo> cardInfos) {
        cardInfos.add(DrawnCardsInfo.toDto(dealer));
    }

    public List<WinLoseResult> getWinLoseResults(final Dealer dealer, final Players players) {
        int dealerScore = dealer.calculateCardScore();
        boolean isDealerBurst = dealerScore > BURST_NUMBER;

        List<WinLoseResult> winLoseResults = players.stream()
                .map(player -> calculateWinLose(dealerScore, isDealerBurst, player))
                .collect(toList());

        return winLoseResults;
    }

    private WinLoseResult calculateWinLose(final int dealerScore, final boolean isDealerBurst, final Player player) {
        int playerScore = player.calculateCardScore();
        if (playerScore > BURST_NUMBER) {
            return WinLoseResult.toDto(player, false);
        }

        if (isDealerBurst) {
            return WinLoseResult.toDto(player, true);

        }

        boolean isPlayerWin = playerScore > dealerScore;
        return WinLoseResult.toDto(player, isPlayerWin);
    }

    public DealerWinLoseResult getDealerResult(final List<WinLoseResult> winLoseResults, final Dealer dealer) {
        int dealerLoseCount = (int) winLoseResults.stream()
                .filter(WinLoseResult::isWin)
                .count();
        int dealerWinCount = winLoseResults.size() - dealerLoseCount;

        DealerWinLoseResult dealerWinLoseResult = DealerWinLoseResult.toDto(dealer, dealerWinCount, dealerLoseCount);
        return dealerWinLoseResult;
    }
}
