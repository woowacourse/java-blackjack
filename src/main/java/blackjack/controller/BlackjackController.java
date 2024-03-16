package blackjack.controller;

import blackjack.dto.DtoGenerator;
import blackjack.dto.GamersDto;
import blackjack.dto.GamersNetProfitDto;
import blackjack.dto.PlayerDto;
import blackjack.model.card.Card;
import blackjack.model.deck.DeckGenerator;
import blackjack.model.deck.PlayingDeck;
import blackjack.model.gameRule.GameRule;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Gamers;
import blackjack.model.gamer.Player;
import blackjack.model.betting.BettingInfo;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRule gameRule = new GameRule();

    public void run() {
        Gamers gamers = registerGamers();
        PlayingDeck playingDeck = createPlayingDeck();
        BettingInfo bettingInfo = createBettingInfo(gamers.getPlayers());

        playBlackJack(gamers, playingDeck, bettingInfo);
    }

    private Gamers registerGamers() {
        Dealer dealer = new Dealer();
        List<Player> players = registerPlayer();
        return new Gamers(dealer, players);
    }

    private List<Player> registerPlayer() {
        List<String> playersName = InputView.readPlayersName();
        return playersName.stream()
                .map(Player::new)
                .toList();
    }

    private PlayingDeck createPlayingDeck() {
        return new PlayingDeck(DeckGenerator.generateDeck());
    }

    private BettingInfo createBettingInfo(List<Player> players) {
        BettingInfo bettingInfo = new BettingInfo();
        for (Player player : players) {
            int betAmount = InputView.readPlayerBetAmount(player.getName());
            bettingInfo.addPlayerBetAmount(player, betAmount);
        }
        return bettingInfo;
    }

    private void playBlackJack(Gamers gamers, PlayingDeck playingDeck, BettingInfo bettingInfo) {
        initialDraw(playingDeck, gamers);
        runHitStay(playingDeck, gamers);
        gameResult(bettingInfo, gamers);
    }

    private void initialDraw(PlayingDeck playingDeck, Gamers gamers) {
        drawCardToDealer(playingDeck, gamers.getDealer());
        drawCardToPlayer(playingDeck, gamers.getPlayers());

        GamersDto gamersDtoByInitialCard = DtoGenerator.createGamersDtoByInitialCard(gamers);
        OutputView.printInitialDrawResult(gamersDtoByInitialCard);
    }

    private void drawCardToDealer(PlayingDeck playingDeck, Dealer dealer) {
        Card firstDealerCard = playingDeck.drawCard();
        Card secondDealerCard = playingDeck.drawCard();

        dealer.receiveCard(firstDealerCard);
        dealer.receiveCard(secondDealerCard);
    }

    private void drawCardToPlayer(PlayingDeck playingDeck, List<Player> players) {
        for (Player player : players) {
            Card firstPlayerCard = playingDeck.drawCard();
            Card secondPlayerCard = playingDeck.drawCard();

            player.receiveCard(firstPlayerCard);
            player.receiveCard(secondPlayerCard);
        }
    }

    private void runHitStay(PlayingDeck playingDeck, Gamers gamers) {
        List<Player> hitStayTargetPlayer = gameRule.decideHitOrStayPlayers(gamers);
        if (hitStayTargetPlayer.isEmpty()) {
            return;
        }

        runPlayerTurn(playingDeck, hitStayTargetPlayer);
        runDealerTurn(playingDeck, gamers.getDealer());
    }

    private void runPlayerTurn(PlayingDeck playingDeck, List<Player> hitStayTargetPlayer) {
        for (Player player : hitStayTargetPlayer) {
            hitOrStay(playingDeck, player);
        }
    }

    private void hitOrStay(PlayingDeck playingDeck, Player player) {
        while (player.canHit() && InputView.askPlayerForCard(player.getName())) {
            executeHit(playingDeck, player);
        }
        if (player.canHit()) {
            PlayerDto playerDto = DtoGenerator.createPlayerDto(player);
            OutputView.printPlayerCard(playerDto);
        }
    }

    private void executeHit(PlayingDeck playingDeck, Player player) {
        Card card = playingDeck.drawCard();
        player.receiveCard(card);
        PlayerDto playerDto = DtoGenerator.createPlayerDto(player);
        OutputView.printPlayerCard(playerDto);
    }

    private void runDealerTurn(PlayingDeck playingDeck, Dealer dealer) {
        if (dealer.canHit()) {
            Card card = playingDeck.drawCard();
            dealer.receiveCard(card);
            OutputView.printDealerHit();
        }
    }

    private void gameResult(BettingInfo bettingInfo, Gamers gamers) {
        gameRule.applyGameResultProfit(bettingInfo, gamers);

        showScore(gamers);
        showNetProfit(bettingInfo, gamers);
    }

    private void showScore(Gamers gamers) {
        GamersDto gamersDto = DtoGenerator.createGamersDto(gamers);
        OutputView.printGamerCardAndScore(gamersDto);
    }

    private static void showNetProfit(BettingInfo bettingInfo, Gamers gamers) {
        GamersNetProfitDto gamersNetProfitDto = DtoGenerator.createGamersNetProfitDto(bettingInfo, gamers.getPlayers());
        OutputView.printNetProfit(gamersNetProfitDto);
    }
}
