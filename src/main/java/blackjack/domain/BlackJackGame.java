package blackjack.domain;

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

    public void calculateGameResults() {
        for (Player player : players) {
            GameResult dealerResult = dealer.judgeResult(player);
            GameResult playerResult = GameResult.getPairResult(dealerResult);
            dealer.exchangeBettingMoney(player.moneyToExchange(playerResult));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
