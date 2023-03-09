package blackjackgame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import betting.BettingAmount;
import betting.BettingMap;
import betting.Reward;
import deck.Deck;
import dto.BettingResultsDto;
import dto.DealerFirstOpenDto;
import dto.DealerWinningDto;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;
import participants.Name;
import participants.Participants;
import participants.Player;

public class BlackjackGame {
    private static final int FIRST_DRAW_COUNT = 2;
    public static final int MAX_PLAYERS = 6;
    private final Participants participants;
    private final Deck deck;
    private final BettingMap bettingMap;

    public BlackjackGame(Participants participants, Deck deck, BettingMap bettingMap) {
        this.participants = participants;
        this.deck = deck;
        this.bettingMap = bettingMap;
    }

    public void addPlayers(List<String> names) {
        validateDuplicatedName(names);
        validateMaxPlayer(names);
        for (String name : names) {
            Player player = new Player(new Name(name));
            participants.addPlayer(player);
        }
    }

    private void validateMaxPlayer(List<String> names) {
        if (names.size() > MAX_PLAYERS) {
            throw new IllegalArgumentException("참가인원은 최대 6명입니다.");
        }
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> namesWithoutDuplication = new HashSet<>(names);
        if (namesWithoutDuplication.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public void supplyCardsToDealer() {
        for (int i = 0; i < FIRST_DRAW_COUNT; i++) {
            participants.giveCardToDealer(deck.drawCard());
        }
    }

    public void supplyCardsToPlayers() {
        int playerCount = participants.getPlayersCount();
        for (int i = 0; i < playerCount; i++) {
            supplyCardToPlayer(i);
        }
    }

    private void supplyCardToPlayer(int i) {
        for (int j = 0; j < FIRST_DRAW_COUNT; j++) {
            participants.giveCardToPlayerByIndex(i, deck.drawCard());
        }
    }

    public void supplyAdditionalCard(int playerIndex) {
        participants.giveCardToPlayerByIndex(playerIndex, deck.drawCard());
    }

    public boolean isBust(int playerIndex) {
        return participants.isBustPlayerByIndex(playerIndex);
    }

    public int countPlayer() {
        return participants.getPlayersCount();
    }

    public boolean canDealerHit() {
        return participants.canDealerHit();
    }

    public void supplyAdditionalCardToDealer() {
        participants.giveCardToDealer(deck.drawCard());
    }

    public PlayerResultDto getDealerResult() {
        return participants.getDealerResult();
    }

    public List<PlayerResultDto> getPlayerResults() {
        return participants.getPlayerResults();
    }

    public void calculateWinning() {
        participants.calculateWinning();
    }

    public DealerWinningDto getDealerWinningResult() {
        return participants.getDealerWinningResult();
    }

    public List<PlayerWinningDto> getPlayerWinningResults() {
        return participants.getPlayerWinningResults();
    }

    public DealerFirstOpenDto getDealerFirstOpen() {
        return participants.getDealerFirstOpen();
    }

    public List<PlayerOpenDto> getPlayersCards() {
        return participants.getPlayersCards();
    }

    public PlayerOpenDto getPlayerCardsByIndex(int playerIndex) {
        return participants.getPlayerCardsByIndex(playerIndex);
    }

    public Name findUserNameByIndex(int playerIndex) {
        return participants.findUserNameByIndex(playerIndex);
    }

    public void saveBetAmount(String name, int betAmount) {
        Player player = participants.findPlayerByName(new Name(name));
        bettingMap.saveBet(player, new BettingAmount(betAmount));
    }

    public Reward getDealerRewardResult() {
        return new Reward(bettingMap.calculateDealerReward());
    }

    public BettingResultsDto getPlayersResultDto() {
        return BettingResultsDto.from(bettingMap);
    }
}
