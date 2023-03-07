package service;

import domain.CardDeck;
import domain.Dealer;
import domain.DrawCommand;
import domain.Participant;
import domain.Player;
import domain.Players;
import dto.BlackJackResult;
import dto.DrawnCardsInfo;
import dto.ParticipantResult;
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
        if (drawCommand.isDraw() && player.calculateCardScore() < BURST_NUMBER) {
            player.pickCard(cardDeck.draw());
        }

        return DrawnCardsInfo.toDto(player);
    }

    public boolean canDrawMore(final Player player, final DrawCommand drawCommand) {
        return player.calculateCardScore() < BURST_NUMBER && drawCommand.isDraw();
    }

    public void pickDealerCard(final CardDeck cardDeck, final Dealer dealer) {
        if (dealer.calculateCardScore() <= DEALER_DRAW_LIMIT_SCORE) {
            dealer.pickCard(cardDeck.draw());
        }
    }

    public boolean canDealerDrawMore(final Dealer dealer) {
        return dealer.calculateCardScore() <= DEALER_DRAW_LIMIT_SCORE;
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

    public void getWinLoseResults(final Dealer dealer, final Players players) {
        players.stream()
                .forEach(player -> calculateWinLose(player, dealer));
    }

    private void calculateWinLose(final Player player, final Dealer dealer) {
        int playerScore = player.calculateCardScore();
        int dealerScore = dealer.calculateCardScore();
        int playerAccount = player.getAccount();

        if (isAllParticipantBust(player, dealer)) {
            return;
        }

        if (isPlayerAndDealerNotBust(player, dealer)) {
            if (playerScore > dealerScore) {
                winPlayer(player, dealer, playerAccount);
                return;
            }
            winDealer(player, dealer, playerAccount);
        }

        if (isPlayerWin(player, dealer)) {
            winPlayer(player, dealer, playerAccount);
        }

        if (isDealerWin(player, dealer)) {
            winDealer(player, dealer, playerAccount);
        }
    }

    private boolean isDealerWin(final Player player, final Dealer dealer) {
        return player.isBust() && !dealer.isBust();
    }

    private boolean isPlayerWin(final Player player, final Dealer dealer) {
        return !player.isBust() && dealer.isBust();
    }

    private void winPlayer(final Player player, final Dealer dealer, final int playerAccount) {
        player.winGame();
        dealer.loseGame(playerAccount);
    }

    private void winDealer(final Player player, final Dealer dealer, final int playerAccount) {
        player.bustAccount();
        dealer.winGame(playerAccount);
    }

    private boolean isAllParticipantBust(final Player player, final Dealer dealer) {
        return player.isBust() && dealer.isBust();
    }

    private boolean isPlayerAndDealerNotBust(final Player player, final Dealer dealer) {
        return !player.isBust() && !dealer.isBust();
    }

    public List<BlackJackResult> getGameResults(final Dealer dealer, final Players players) {
        List<BlackJackResult> blackJackResults = new ArrayList<>();
        blackJackResults.add(BlackJackResult.toDto(dealer));

        players.stream()
                .forEach(player -> blackJackResults.add(BlackJackResult.toDto(player)));

        return blackJackResults;
    }
}
