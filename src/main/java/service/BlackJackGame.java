package service;

import domain.BettingAmount;
import domain.BettingManager;
import domain.Card;
import domain.CardDistributor;
import domain.Dealer;
import domain.Name;
import domain.Player;
import domain.Players;
import domain.Result;
import dto.CardStatusDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import util.InitialCardMaker;

public class BlackJackGame {

    private final CardDistributor cardDistributor;
    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(List<String> playerNames) {
        this.cardDistributor = new CardDistributor(InitialCardMaker.generate());
        this.dealer = createDealer();
        this.players = createPlayers(playerNames);
    }

    private Dealer createDealer() {
        return new Dealer(cardDistributor.distributeInitialCard());
    }

    private Players createPlayers(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(this::distributeInitialCardForPlayer)
                .collect(Collectors.toList());
        return Players.from(players);
    }

    private Player distributeInitialCardForPlayer(String playerName) {
        return Player.of(new Name(playerName), cardDistributor.distributeInitialCard());
    }

    public BettingManager createBettingManager(List<BettingAmount> amounts) {
        return new BettingManager(players.getPlayerNames(), amounts);
    }

    public List<List<CardStatusDto>> getParticipantsInitialCards() {
        return Stream.concat(Stream.of(getStatusFromCards(List.of(dealer.showOneCard()))), getPlayerCards().stream())
                .collect(Collectors.toList());
    }

    public List<CardStatusDto> getStatusFromCards(List<Card> cards) {
        return cards.stream()
                .map(CardStatusDto::from)
                .collect(Collectors.toList());
    }

    private List<List<CardStatusDto>> getPlayerCards() {
        return players.getPlayers().stream()
                .map(player -> getStatusFromCards(player.getCardList()))
                .collect(Collectors.toList());
    }

    public void progressPlayer(Player player, boolean isCardRequested) {
        if (isCardRequested && cardDistributor.isCardLeft()) {
            player.pick(cardDistributor.distribute());
        }
    }

    public int progressDealer() {
        int dealerMoreCardCount = 0;
        while (dealer.isMoreCardAble() && cardDistributor.isCardLeft()) {
            dealer.pick(cardDistributor.distribute());
            dealerMoreCardCount++;
        }
        return dealerMoreCardCount;
    }

    public Map<String, Double> calculateTotalRevenue(BettingManager bettingManager) {
        Result result = new Result(dealer, players);
        return bettingManager.calculateTotalRevenue(result.getPlayersWinResult());
    }

    public List<String> getPlayerNamesToString() {
        return players.getPlayerNamesToString();
    }

    public String getDealerNameToString() {
        return dealer.getNameToValue();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<String> getAllParticipantsNameToString() {
        return Stream.concat(Stream.of(dealer.getNameToValue()), players.getPlayerNamesToString().stream())
                .collect(Collectors.toList());
    }

    public List<List<CardStatusDto>> getAllParticipantsCards() {
        return Stream.concat(Stream.of(getStatusFromCards(dealer.getCardList())), getPlayerCards().stream())
                .collect(Collectors.toList());
    }

    public List<Integer> getAllParticipantsTotalScore() {
        Stream<Integer> playersScore = players.getPlayers().stream()
                .map(Player::getTotalScoreToValue);
        return Stream.concat(Stream.of(dealer.getTotalScoreToValue()), playersScore)
                .collect(Collectors.toList());
    }

}
