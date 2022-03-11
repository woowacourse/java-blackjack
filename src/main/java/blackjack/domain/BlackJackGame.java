package blackjack.domain;

import blackjack.domain.dto.GameResultDto;
import blackjack.domain.dto.ParticipantDto;
import blackjack.domain.strategy.RandomCardGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackGame(List<String> playersNames) {
        this.cardDeck = new CardDeck(new RandomCardGenerator());
        this.dealer = new Dealer(cardDeck.drawTwoCards());
        validateEmptyNames(playersNames);
        this.players = createPlayers(playersNames);
    }

    private void validateEmptyNames(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }

    private List<Player> createPlayers(List<String> playersNames) {
        return playersNames.stream()
                .map(playerName -> new Player(playerName.trim(), cardDeck.drawTwoCards()))
                .collect(Collectors.toList());
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
        return players.stream()
                .filter(participant -> !participant.isFinished())
                .findFirst().get()
                .getName();
    }

    public List<Card> playerDrawCardOnDecision(Boolean decision) {
        Player playerOnTurn = players.stream()
                .filter(participant -> !participant.isFinished())
                .findFirst().get();
        if(decision) {
            playerOnTurn.receiveCard(cardDeck.drawCard());
        }
        if(!decision) {
            playerOnTurn.closeTurn();
        }
        return playerOnTurn.getHoldingCard().getHoldingCard();
    }

    public boolean dealerDrawMoreCard() {
        if(!dealer.isFinished()){
            dealer.receiveCard(cardDeck.drawCard());
            return true;
        }
        return false;
    }

    public List<GameResultDto> getGameResultsDtos() {
        List<GameResultDto> gameResultDtos = new ArrayList<>();
        List<GameResult> dealerResults = new ArrayList<>();
        for (Player player : players) {
            GameResult dealerResult = dealer.judgeResult(player);
            dealerResults.add(dealerResult);
            gameResultDtos.add(GameResultDto.ofPlayer(player.getName(), GameResult.getPairResult(dealerResult)));
        }
        gameResultDtos.add(0, GameResultDto.ofDealer("딜러", dealerResults));
        return gameResultDtos;
    }

}
