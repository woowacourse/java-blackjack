package blackjack.domain;

import blackjack.domain.dto.ParticipantDto;
import blackjack.domain.strategy.RandomCardGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackGame(List<String> playersNames) {
        this.cardDeck = new CardDeck(RandomCardGenerator.getInstance());
        this.dealer = new Dealer();
        validateEmptyNames(playersNames);
        this.players = createPlayers(playersNames);
        giveTwoCardsToAll();
    }

    private void giveTwoCardsToAll() {
        dealer.receiveCards(cardDeck.drawTwoCards());
        for (Player player : players) {
            player.receiveCards(cardDeck.drawTwoCards());
        }
    }

    private void validateEmptyNames(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름 입력이 비었습니다");
        }
    }

    private List<Player> createPlayers(List<String> playersNames) {
        return playersNames.stream()
                .map(playerName -> new Player(playerName.trim()))
                .collect(Collectors.toList());
    }

    public boolean isAnyPlayerNotBetYet() {
        return players.stream()
                .anyMatch(Player::isNeedBettingMoney);
    }

    public Player findPlayerNeedToBetMoney() {
        return players.stream()
                .filter(Player::isNeedBettingMoney)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 이미 모든 플레이어의 배팅 금액이 입력되었습니다."));
    }

    public void playerSetupBettingMoney(int money) {
        findPlayerNeedToBetMoney().putBettingMoney(money);
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
