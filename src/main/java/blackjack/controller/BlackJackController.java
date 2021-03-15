package blackjack.controller;

import blackjack.domain.GameTable;
import blackjack.domain.participant.*;
import blackjack.domain.result.MatchResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    public void run() {
        GameTable gameTable = new GameTable(makePlayers());
        gameTable.drawAtFirst();
        OutputView.printParticipantsCardAtFirst(gameTable.getDealer(), gameTable.getPlayers());
        askPlayersToHit(gameTable);
        drawMoreCardToDealer(gameTable);
        showResult(gameTable.getDealer(), gameTable.getPlayers());
    }

    private Players makePlayers() {
        List<Name> playerNames = getPlayerNames();
        List<Player> players = new ArrayList<>();
        for (Name name : playerNames) {
            BettingMoney bettingMoney = getBettingMoney(name);
            players.add(new Player(name, bettingMoney));
        }
        return new Players(players);
    }

    private List<Name> getPlayerNames() {
        OutputView.printNameInputGuideMessage();
        try {
            return InputView.getPlayerNameInput().stream()
                    .map(Name::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getPlayerNames();
        }
    }

    private BettingMoney getBettingMoney(Name name) {
        OutputView.printBettingMoneyInputGuideMessage(name);
        try {
            return new BettingMoney(InputView.getBettingMoney());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getBettingMoney(name);
        }
    }

    private void drawMoreCardToDealer(GameTable gameTable) {
        Dealer dealer = gameTable.getDealer();
        while (dealer.canHit()) {
            gameTable.drawMoreCardToDealer();
            OutputView.printDealerHitMessage();
        }
    }

    private void askPlayersToHit(GameTable gameTable) {
        gameTable.getEveryPlayer()
                .forEach(player -> askHit(gameTable, player));
    }

    private void askHit(GameTable gameTable, Player player) {
        String doesPlayerWantMoreCard;

        do {
            OutputView.printHitGuideMessage(player);
            doesPlayerWantMoreCard = InputView.getHitValue();
            gameTable.draw(player, doesPlayerWantMoreCard);
            OutputView.printPlayerCards(player);
        } while (player.isNotBust() && doesPlayerWantMoreCard.equals(GameTable.PLAYER_WANT_MORE_CARD));
    }

    private void showResult(Dealer dealer, Players players) {
        OutputView.printCardsAndScore(dealer, players);
        MatchResult matchResult = new MatchResult(dealer, players);
        OutputView.printMatchResult(matchResult);
    }
}
