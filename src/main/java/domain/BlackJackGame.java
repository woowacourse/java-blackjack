package domain;

import dto.ParticipantDto;
import java.util.List;
import java.util.Optional;

public class BlackJackGame {
    private final Deck totalDeck;
    private final Dealer dealer;
    private final Players players;

    private BlackJackGame(Deck totalDeck, Dealer dealer, Players players) {
        this.totalDeck = totalDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackGame ready(List<String> playerNames, CardCreationStrategy strategy) {
        Deck totalDeck = Deck.createDeck(strategy);

        Dealer dealer = createNewDealer(totalDeck);
        Players players = Players.of(playerNames, totalDeck);

        return new BlackJackGame(totalDeck, dealer, players);
    }

    private static Dealer createNewDealer(Deck totalDeck) {
        List<Card> dealersInitialCards = totalDeck.drawTwoCards();
        Card card1 = dealersInitialCards.get(0);
        Card card2 = dealersInitialCards.get(1);
        return new Dealer(Hand.of(card1, card2));
    }

    public Optional<Player> whoseTurn() {
        return players.findNotStayPlayer();
    }

    public void doHitProcess() {
        players.findNotStayPlayer()
                .ifPresent(player -> players.executeHit(player, totalDeck::drawCard));
    }

    public void doStandProcess() {
        players.findNotStayPlayer()
                .ifPresent(players::executeStand);
    }

    public List<ParticipantDto> getPlayersGameSettingResults() {
        return players.getInitialStates();
    }

    public ParticipantDto getDealerGameSettingResult() {
        return ParticipantDto.consistWithInitialInfo(dealer);
    }
//
//    public void play(GameDelegate observer) {
//        List<Player> individualPlayers = players.getPlayers();
//        for (Player player : individualPlayers) {
//            while (!player.isBust() && observer.askDrawCard(player.getName())) {
//                player.addCard(totalDeck);
//                observer.showPlayerCards(ParticipantDto.from(player));
//            }
//        }
//        while (dealer.addCard(totalDeck).isPresent()) {
//            observer.showDealerOneMoreCardMessage();
//        }
//    }
//
//    public void end(GameDelegate delegate) {
//        GameResultDto result = this.calculateResult();
//        delegate.showGameResult(result);
//    }
//
//    private GameResultDto calculateResult() {
//        int dealerScore = dealer.calculateHandSum();
//        boolean isDealerBust = dealer.isBust();
//
//        Map<Player, Result> playerWinLossResults = consistPlayerWinLossResults(dealerScore, isDealerBust);
//        Map<Result, Integer> dealerWinLossResults = consistDealerResult(playerWinLossResults);
//
//        return GameResultDto.from(
//                dealer,
//                players,
//                dealerWinLossResults,
//                playerWinLossResults
//        );
//    }
//
//    private Map<Player, Result> consistPlayerWinLossResults(int dealerScore, boolean isDealerBust) {
//        Map<Player, Result> playerWinLossResults = new LinkedHashMap<>();
//        List<Player> playingPlayers = players.getPlayers();
//
//        for (Player specificPlayer : playingPlayers) {
//            Result specificPlayerResult = determinePlayerResult(dealerScore, isDealerBust, specificPlayer);
//            playerWinLossResults.put(specificPlayer, specificPlayerResult);
//        }
//
//        return playerWinLossResults;
//    }
//
//    private Result determinePlayerResult(int dealerScore, boolean isDealerBust, Player specificPlayer) {
//        return Result.determinePlayerResult(
//                dealerScore,
//                specificPlayer.calculateHandSum(),
//                isDealerBust,
//                specificPlayer.isBust()
//        );
//    }
//
//    private Map<Result, Integer> consistDealerResult(Map<Player, Result> playerWinLossResults) {
//        Map<Result, Integer> dealerWinLossResults = new HashMap<>();
//        List<Player> playingPlayers = playerWinLossResults.keySet().stream().toList();
//        for (Player player : playingPlayers) {
//            Result dealerResult = playerWinLossResults.get(player).reverse();
//            int currentValue = dealerWinLossResults.getOrDefault(dealerResult, 0);
//            dealerWinLossResults.put(dealerResult, currentValue + 1);
//        }
//        return dealerWinLossResults;
//    }
}
