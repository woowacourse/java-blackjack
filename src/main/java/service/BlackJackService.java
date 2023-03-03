package service;

import static java.util.stream.Collectors.toList;

import domain.Card;
import domain.CardDeck;
import domain.Dealer;
import domain.Participant;
import domain.Player;
import domain.Players;
import dto.DealerResult;
import dto.DrawnCardsInfo;
import dto.GameResult;
import dto.WinLoseResult;
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

        return createDrawnCardsInfos(dealer, dealer.openDrawnCards(), players);
    }

    private void splitCards(final Participant participant, final CardDeck cardDeck) {
        for (int i = 0; i < NUMBER_OF_SPLIT_CARDS; i++) {
            participant.pickCard(cardDeck.draw());
        }
    }

    private List<DrawnCardsInfo> createDrawnCardsInfos(final Dealer dealer,
                                                       final List<Card> dealerCards,
                                                       final Players players) {
        List<DrawnCardsInfo> cardInfos = new ArrayList<>();
        addDealerCardInfo(dealer, dealerCards, cardInfos);

        players.stream()
                .forEach(player -> cardInfos.add(DrawnCardsInfo.toDto(player, player.openDrawnCards())));

        return cardInfos;
    }

    public DrawnCardsInfo drawCards(final CardDeck cardDeck,
                                    final Player player,
                                    final String result) {
        if (result.equals("y")) {
            player.pickCard(cardDeck.draw());
        }
        return DrawnCardsInfo.toDto(player, player.openDrawnCards());
    }

    public boolean canDrawMore(final Player player, final String result) {
        if (player.calculateCardScore() > 21 || result.equals("n")) {
            return false;
        }

        return true;
    }

    public void pickDealerCard(final CardDeck cardDeck, final Dealer dealer) {
        if (dealer.calculateCardScore() <= 16) {
            dealer.pickCard(cardDeck.draw());
        }
    }

    public boolean canDealerDrawMore(final Dealer dealer) {
        if (dealer.calculateCardScore() > 16) {
            return false;
        }

        return true;
    }

    public List<GameResult> createResultStatus(final Dealer dealer,
                                               final Players players) {
        List<GameResult> result = new ArrayList<>();
        result.add(GameResult.toDto(dealer));

        players.stream()
                .forEach(player -> result.add(GameResult.toDto(player)));

        return result;
    }

    private void addDealerCardInfo(final Dealer dealer,
                                   final List<Card> dealerCards,
                                   final List<DrawnCardsInfo> cardInfos) {
        cardInfos.add(DrawnCardsInfo.toDto(dealer, dealerCards));
    }

    public List<WinLoseResult> getWinLoseResults(final Dealer dealer, final Players players) {
        int dealerScore = dealer.calculateCardScore();
        boolean isDealerBurst = dealerScore > 21;

        List<WinLoseResult> winLoseResults = players.stream()
                .map(player -> calculateWinLose(dealerScore, isDealerBurst, player))
                .collect(toList());

        return winLoseResults;
    }

    private WinLoseResult calculateWinLose(final int dealerScore, final boolean isDealerBurst, final Player player) {
        int playerScore = player.calculateCardScore();
        if (playerScore > 21) {
            return WinLoseResult.toDto(player, false);
        }

        if (isDealerBurst) {
            return WinLoseResult.toDto(player, true);

        }

        boolean result = playerScore > dealerScore;
        return WinLoseResult.toDto(player, result);
    }

    public DealerResult getDealerResult(final List<WinLoseResult> winLoseResults, final Dealer dealer) {
        int dealerLoseCount = (int) winLoseResults.stream()
                .filter(WinLoseResult::isWin)
                .count();
        int dealerWinCount = winLoseResults.size() - dealerLoseCount;

        DealerResult dealerResult = DealerResult.toDto(dealer, dealerWinCount, dealerLoseCount);
        return dealerResult;
    }
}
