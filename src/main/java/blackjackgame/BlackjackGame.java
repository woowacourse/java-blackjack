package blackjackgame;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import betting.BettingAmount;
import betting.PlayersBettingTable;
import betting.Reward;
import deck.Deck;
import dto.BettingResultDto;
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
    public static final int MAX_PLAYERS = 6;
    private static final int FIRST_DRAW_COUNT = 2;
    private final Participants participants;
    private final Deck deck;
    private final PlayersBettingTable playersBettingTable;

    public BlackjackGame(Participants participants, Deck deck, PlayersBettingTable playersBettingTable) {
        this.participants = participants;
        this.deck = deck;
        this.playersBettingTable = playersBettingTable;
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
        playersBettingTable.saveBet(player, new BettingAmount(betAmount));
    }

    public Reward getDealerRewardResult() {
        return new Reward(playersBettingTable.calculateDealerReward());
    }

    public BettingResultsDto getPlayersResultDto() {
        List<BettingResultDto> bettingResults = getBettingResults(playersBettingTable.getBettingMap());
        return new BettingResultsDto(bettingResults);
    }

    private List<BettingResultDto> getBettingResults(Map<Name, BettingAmount> bettingAmountMap) {
        return bettingAmountMap.entrySet()
                .stream()
                .map(entry -> new BettingResultDto(entry.getKey(), getRewardByResult(entry)))
                .collect(Collectors.toList());
    }

    private int getRewardByResult(Entry<Name, BettingAmount> entry) {
        return entry.getValue().calculateRewardByResult(participants.findPlayerResultByName(entry.getKey()));
    }

}
