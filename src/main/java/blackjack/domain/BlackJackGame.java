package blackjack.domain;

import blackjack.domain.dto.BettingResultDto;
import blackjack.domain.dto.ParticipantDto;
import blackjack.domain.strategy.RandomCardGenerator;

import java.util.*;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackGame(List<Player> players) {
        this.cardDeck = new CardDeck(RandomCardGenerator.getInstance());
        this.dealer = new Dealer();
        this.players = players;
        giveTwoCardsToAll();
    }

    private void giveTwoCardsToAll() {
        dealer.receiveCards(cardDeck.drawTwoCards());
        for (Player player : players) {
            player.receiveCards(cardDeck.drawTwoCards());
        }
    }

    public List<ParticipantDto> getParticipantsDto() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(ParticipantDto.of(dealer, List.of(dealer.showFirstCard())));
        for (Participant player : players) {
            participantDtos.add(ParticipantDto.of(player, player.showCards()));
        }
        return participantDtos;
    }

    public List<ParticipantDto> getFinalParticipantsDto() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(ParticipantDto.of(dealer, dealer.showCards()));
        for (Participant player : players) {
            participantDtos.add(ParticipantDto.of(player, player.showCards()));
        }
        return participantDtos;
    }

    public boolean isAnyPlayerTurnRemain() {
        return players.stream()
                .anyMatch(participant -> !participant.isFinished());
    }

    public String playNameOnTurn() {
        return findPlayerOnTurn().getName();
    }

    public List<Card> playerDrawCardOnDecision(boolean decision) {
        Player playerOnTurn = findPlayerOnTurn();
        if(decision) {
            playerOnTurn.receiveCard(cardDeck.drawCard());
            return playerOnTurn.getHoldingCard().getCards();
        }
        playerOnTurn.closeTurn();
        return playerOnTurn.getHoldingCard().getCards();
    }

    private Player findPlayerOnTurn() {
        return players.stream()
                .filter(participant -> !participant.isFinished())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 모든 플레이어의 턴이 끝났습니다."));
    }

    public boolean dealerDrawMoreCard() {
        if(!dealer.isFinished()){
            dealer.receiveCard(cardDeck.drawCard());
            return true;
        }
        return false;
    }

    public List<BettingResultDto> calculateGameResults() {
        List<BettingResultDto> bettingResultDtos = new ArrayList<>();
        int dealerRevenue = 0;
        for (Player player : players) {
            GameResult gameResult = player.judgeResult(dealer);
            int playerRevenue = gameResult.calculateRevenue(player.getBettingMoney());
            bettingResultDtos.add(BettingResultDto.of(player, playerRevenue));
            dealerRevenue -= playerRevenue;
        }
        bettingResultDtos.add(0, BettingResultDto.of(dealer, dealerRevenue));
        return bettingResultDtos;
    }

}
