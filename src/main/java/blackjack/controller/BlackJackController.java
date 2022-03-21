package blackjack.controller;

import blackjack.domain.BettingMoney;
import blackjack.domain.BlackJack;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.GameResult;
import blackjack.dto.GamerCardsDto;
import blackjack.dto.GamerCardsResultDto;
import blackjack.dto.ProfitDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    private BlackJack blackJack;

    public void run() {
        initialize();
        play();
        finish();
    }

    private void initialize() {
        blackJack = new BlackJack(initializePlayerGroup(), new Dealer());
        blackJack.divideCards();
        OutputView.printInitialGamerCards(GamerCardsDto.valueOf(blackJack.getDealer()),
                GamerCardsDto.valueOf(blackJack.getPlayers()));
    }

    private PlayerGroup initializePlayerGroup() {
        List<String> playerNames = getPlayerNames();
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            BettingMoney bettingMoney = getBettingMoney(playerName);
            players.add(Player.of(playerName, bettingMoney));
        }
        return new PlayerGroup(players);
    }

    private List<String> getPlayerNames() {
        try {
            return InputView.requestPlayerNames();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getPlayerNames();
        }
    }

    private BettingMoney getBettingMoney(String playerName) {
        try {
            int amount = InputView.requestBettingMoney(playerName);
            return BettingMoney.of(amount);
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getBettingMoney(playerName);
        }
    }

    private void play() {
        List<String> playerNames = blackJack.getPlayerNames();
        for (String playerName : playerNames) {
            requestHitOrStand(playerName);
        }
        OutputView.printDealerCardMessage(blackJack.addCardToDealer());
    }

    private void requestHitOrStand(String playerName) {
        Player player = blackJack.findPlayerByName(playerName);
        if (InputView.requestHit(playerName)) {
            blackJack.addCardTo(player);
            OutputView.printGamerCard(GamerCardsDto.of(playerName, player.getCards()));
            requestHitOrStandByStatus(player);
            return;
        }
        OutputView.printGamerCard(GamerCardsDto.of(playerName, player.getCards()));
    }

    private void requestHitOrStandByStatus(Player player) {
        if (player.isNotBust()) {
            requestHitOrStand(player.getName());
        }
    }

    private void finish() {
        OutputView.printGamerCardAndSum(GamerCardsResultDto.valueOf(blackJack.getDealer()));
        OutputView.printGamersCardAndSum(GamerCardsResultDto.valueOf(blackJack.getPlayers()));

        GameResult gameResult = blackJack.getGameResult();
        OutputView.printGameResult(ProfitDto.of(gameResult));
    }
}
