package blackjack.controller;

import blackjack.dto.DealerDto;
import blackjack.dto.DealerNetProfitDto;
import blackjack.dto.DtoGenerator;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersNetProfitDto;
import blackjack.model.card.Card;
import blackjack.model.deck.DeckGenerator;
import blackjack.model.deck.PlayingDeck;
import blackjack.model.gameRule.GameRule;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Name;
import blackjack.model.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck());

    public void run() {
        List<Player> players = registerPlayer();
        Dealer dealer = registerDealer(players);
        playBlackJack(dealer, players);
    }

    private List<Player> registerPlayer() {
        List<String> playersName = InputView.readPlayersName();

        List<Player> players = new ArrayList<>();
        for (String name : playersName) {
            Name playerName = new Name(name);
            int betAmount = InputView.readPlayerBetAmount(playerName);
            players.add(new Player(playerName, betAmount));
        }

        return players;
    }

    private Dealer registerDealer(List<Player> players) {
        int playersBetAmount = players.stream()
                .mapToInt(Player::betAmount)
                .sum();

        return new Dealer(playersBetAmount);
    }

    private void playBlackJack(Dealer dealer, List<Player> players) {
        initialDraw(dealer, players);
        initialResult(dealer, players);
        runHitStay(dealer, players);
        gameResult(dealer, players);
    }

    private void initialDraw(Dealer dealer, List<Player> players) {
        GameRule.initialSetting(players);
        drawCardToDealer(dealer);
        drawCardToPlayer(players);

        Card dealerInitialOpenCard = GameRule.applyDealerInitialCardOpenRule(dealer);
        DealerDto dealerDto = DtoGenerator.createInitialDealerDto(dealerInitialOpenCard);
        List<PlayerDto> playerDtos = DtoGenerator.createPlayerDtos(players);

        OutputView.printInitialDrawResult(dealerDto, playerDtos);
    }

    private void drawCardToDealer(Dealer dealer) {
        Card firstDealerCard = playingDeck.drawCard();
        Card secondDealerCard = playingDeck.drawCard();

        dealer.receiveCard(firstDealerCard);
        dealer.receiveCard(secondDealerCard);
    }

    private void drawCardToPlayer(List<Player> players) {
        for (Player player : players) {
            Card firstPlayerCard = playingDeck.drawCard();
            Card secondPlayerCard = playingDeck.drawCard();

            player.receiveCard(firstPlayerCard);
            player.receiveCard(secondPlayerCard);
        }
    }

    private void initialResult(Dealer dealer, List<Player> players) {
        GameRule.applyInitialResultRule(dealer, players);
    }

    private void runHitStay(Dealer dealer, List<Player> players) {
        List<Player> hitStayTargetPlayer = GameRule.applyHitStayTargetPlayerDecisionRule(players);
        if (hitStayTargetPlayer.isEmpty()) {
            return;
        }

        runPlayerTurn(hitStayTargetPlayer);
        runDealerTurn(dealer);
    }

    private void runPlayerTurn(List<Player> hitStayTargetPlayer) {
        for (Player player : hitStayTargetPlayer) {
            hitOrStay(player);
        }
    }

    private void hitOrStay(Player player) {
        while (GameRule.applyPlayerHitRule(player) && InputView.askPlayerForCard(player)) {
            executeHit(player);
        }
        if (GameRule.applyPlayerHitRule(player)) {
            PlayerDto playerDto = DtoGenerator.createPlayerDto(player);
            OutputView.printPlayerCard(playerDto);
        }
    }

    private void executeHit(Player player) {
        Card card = playingDeck.drawCard();
        player.receiveCard(card);
        PlayerDto playerDto = DtoGenerator.createPlayerDto(player);
        OutputView.printPlayerCard(playerDto);
    }

    private void runDealerTurn(Dealer dealer) {
        if (GameRule.applyDealerHitRule(dealer)) {
            Card card = playingDeck.drawCard();
            dealer.receiveCard(card);
            OutputView.printDealerHit();
        }
    }

    private void gameResult(Dealer dealer, List<Player> players) {
        GameRule.applyFinalResultRule(dealer, players);
        for (Player player : players) {
            GameRule.applyGameResultProfit(dealer, player);
        }

        showScore(dealer, players);
        showNetProfit(dealer, players);
    }

    private void showScore(Dealer dealer, List<Player> players) {
        DealerDto dealerDto = DtoGenerator.createDealerDto(dealer);
        List<PlayerDto> playerDtos = DtoGenerator.createPlayerDtos(players);

        OutputView.printGamerCardAndScore(dealerDto, playerDtos);
    }

    private static void showNetProfit(Dealer dealer, List<Player> players) {
        DealerNetProfitDto dealerNetProfitDto = DtoGenerator.createDealerNetProfitDto(dealer);
        PlayersNetProfitDto playersNetProfitDto = DtoGenerator.createPlayerNetProfitDto(players);
        OutputView.printNetProfit(dealerNetProfitDto, playersNetProfitDto);
    }
}
