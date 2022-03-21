package blackjack.controller;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.CardPack;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GamerGroup;
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
import java.util.stream.Collectors;

public class BlackJackController {
    private final GamerGroup gamerGroup;
    private final CardPack cardPack;

    public BlackJackController() {
        this.gamerGroup = new GamerGroup(new Dealer(), initializePlayerGroup());
        this.cardPack = new CardPack();
    }

    public void run() {
        divideCards();
        play();
        finish();
    }

    private PlayerGroup initializePlayerGroup() {
        List<String> playerNames = requestPlayerNames();
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            BettingMoney bettingMoney = requestBettingMoney(playerName);
            players.add(Player.of(playerName, bettingMoney));
        }
        return new PlayerGroup(players);
    }

    private List<String> requestPlayerNames() {
        try {
            return InputView.requestPlayerNames();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestPlayerNames();
        }
    }

    private BettingMoney requestBettingMoney(String playerName) {
        try {
            int amount = InputView.requestBettingMoney(playerName);
            return BettingMoney.of(amount);
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestBettingMoney(playerName);
        }
    }

    private void divideCards() {
        gamerGroup.addInitialCards(cardPack);
        OutputView.printInitialGamerCards(GamerCardsDto.valueOf(gamerGroup.getDealer()),
                GamerCardsDto.valueOf(gamerGroup.getPlayers()));
    }

    private void play() {
        List<String> playerNames = getPlayerNames();
        for (String playerName : playerNames) {
            Player player = gamerGroup.findPlayerByName(playerName);
            requestHitOrStand(player);
        }
        OutputView.printDealerCardMessage(gamerGroup.addCardToDealer(cardPack));
    }

    private List<String> getPlayerNames() {
        return gamerGroup.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private void requestHitOrStand(Player player) {
        if (player.isBust()) {
            return;
        }

        if (InputView.requestHit(player.getName())) {
            player.addCard(cardPack.pickOne());
            OutputView.printGamerCard(GamerCardsDto.of(player.getName(), player.getCards()));
            requestHitOrStand(player);
            return;
        }

        OutputView.printGamerCard(GamerCardsDto.of(player.getName(), player.getCards()));
    }

    private void finish() {
        OutputView.printGamerCardAndSum(GamerCardsResultDto.valueOf(gamerGroup.getDealer()));
        OutputView.printGamersCardAndSum(GamerCardsResultDto.valueOf(gamerGroup.getPlayers()));

        GameResult gameResult = gamerGroup.getGameResult();
        OutputView.printGameResult(ProfitDto.of(gameResult));
    }
}
