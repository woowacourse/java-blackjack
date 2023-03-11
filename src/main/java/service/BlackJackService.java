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

    public List<DrawnCardsInfo> splitCards(final Dealer dealer, final Players players, final CardDeck cardDeck) {
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

    private List<DrawnCardsInfo> getDrawnCardsInfos(final Dealer dealer, final Players players) {
        List<DrawnCardsInfo> cardInfos = new ArrayList<>();
        addDealerCardInfo(dealer, cardInfos);

        players.stream()
                .forEach(player -> cardInfos.add(DrawnCardsInfo.toDto(player)));

        return cardInfos;
    }

    public DrawnCardsInfo drawCards(final CardDeck cardDeck, final Player player, final DrawCommand drawCommand) {
        if (canDrawMore(player, drawCommand)) {
            player.pickCard(cardDeck.draw());
        }

        return DrawnCardsInfo.toDto(player);
    }

    public boolean canDrawMore(final Player player, final DrawCommand drawCommand) {
        return player.canDrawMore() && drawCommand.isDraw();
    }

    public void pickDealerCard(final CardDeck cardDeck, final Dealer dealer) {
        if (dealer.canDrawMore()) {
            dealer.pickCard(cardDeck.draw());
        }
    }

    public boolean canDealerDrawMore(final Dealer dealer) {
        return dealer.canDrawMore();
    }

    public List<ParticipantResult> getParticipantsCardsResults(final Dealer dealer, final Players players) {
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

    public void calculateGameResults(final Dealer dealer, final Players players) {
        players.stream()
                .forEach(player -> calculateAccount(player, dealer));
    }

    private void calculateAccount(final Player player, final Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return;
        }

        if (isAllParticipantBust(player, dealer)) {
            winDealer(player, dealer);
            return;
        }

        if (isPlayerWin(player, dealer)) {
            winPlayer(player, dealer, player.getAccount());
            return;
        }

        winDealer(player, dealer);
    }

    private boolean isPlayerWin(final Player player, final Dealer dealer) {
        return (player.isScoreHighThanDealer(dealer.calculateCardScore()) && !player.isBust())
                || dealer.isBust();
    }

    private void winPlayer(final Player player, final Dealer dealer, final int playerAccount) {
        player.winGame();
        dealer.loseGame(playerAccount);
    }

    private void winDealer(final Player player, final Dealer dealer) {
        dealer.winGame(player.bustAccount());
    }

    private boolean isAllParticipantBust(final Player player, final Dealer dealer) {
        return player.isBust() && dealer.isBust();
    }

    public List<BlackJackResult> getParticipantAccountResults(final Dealer dealer, final Players players) {
        List<BlackJackResult> blackJackResults = new ArrayList<>();
        blackJackResults.add(BlackJackResult.toDto(dealer));

        players.stream()
                .forEach(player -> blackJackResults.add(BlackJackResult.toDto(player)));

        return blackJackResults;
    }
}
