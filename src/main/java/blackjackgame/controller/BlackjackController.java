package blackjackgame.controller;

import blackjackgame.domain.BlackjackGame;
import blackjackgame.domain.blackjack.GameProfit;
import blackjackgame.domain.blackjack.PlayerRandomCardDrawStrategy;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.gamers.CardHolder;
import blackjackgame.domain.gamers.Gamer;
import blackjackgame.domain.gamers.Gamers;
import blackjackgame.dto.CardDTO;
import blackjackgame.dto.GameProfitDTO;
import blackjackgame.dto.GamerDTO;
import blackjackgame.view.BetMoneyInputView;
import blackjackgame.view.GameProfitOutputView;
import blackjackgame.view.GamerOutputView;
import blackjackgame.view.NameInputView;
import blackjackgame.view.OutputView;
import blackjackgame.view.YesOrNoInputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    private static final String DEALER_NAME = "딜러";
    private static final double INITIAL_MONEY = 0.0;

    public void startBlackjackGame(Deck deck) {
        BlackjackGame blackjackGame = initBlackJackGame();
        playBlackJackGame(blackjackGame, deck);
    }

    private static BlackjackGame initBlackJackGame() {
        List<String> playerNames = NameInputView.getNames();
        List<Double> playerBetMoneys = BetMoneyInputView.getPlayerBetMoneys(playerNames);

        Gamer dealer = Gamer.dealer();
        Gamers players = Gamers.createByNamesAndBetMoneys(playerNames, playerBetMoneys);

        return new BlackjackGame(dealer, players);
    }

    private void playBlackJackGame(BlackjackGame blackjackGame, Deck deck) {
        initDealerAndPlayers(blackjackGame, deck);
        printDealerAndPlayers(blackjackGame);

        playersTryDraw(blackjackGame, deck);
        dealerTryDraw(blackjackGame, deck);

        printDealerAndPlayersWithPoint(blackjackGame);
        printDealerAndPlayersGameResult(blackjackGame);
    }

    private void initDealerAndPlayers(BlackjackGame blackjackGame, Deck deck) {
        blackjackGame.initDealerAndPlayers(deck);
        OutputView.printCardSplitMessage(blackjackGame.getRawDealerName(), blackjackGame.getRawPlayerNames());
    }

    private void printDealerAndPlayers(BlackjackGame blackjackGame) {
        printDealer(blackjackGame);
        printPlayers(blackjackGame);
    }

    private void printDealer(BlackjackGame blackjackGame) {
        GamerDTO dealerDTO = makeGamerDTO(blackjackGame.getCardHolderDealer());
        GamerOutputView.printOutputWithoutSummationCardPoint(dealerDTO);
    }

    private void printPlayers(BlackjackGame blackjackGame) {
        blackjackGame.getRawPlayers().forEach(this::printPlayer);
    }

    private void printPlayer(CardHolder player) {
        GamerDTO gamerDTO = makeGamerDTO(player);
        GamerOutputView.printOutputWithoutSummationCardPoint(gamerDTO);
    }

    private GamerDTO makeGamerDTO(CardHolder cardHolderGamer) {
        List<Card> rawHoldingCards = new ArrayList<>(cardHolderGamer.getRawHoldingCards());
        List<CardDTO> cardDTOs = rawHoldingCards.stream()
                .map(card -> new CardDTO(card.name(), card.cardType()))
                .toList();
        return new GamerDTO(cardHolderGamer.getRawName(), cardDTOs,
                cardHolderGamer.getRawSummationCardPoint());
    }

    private void playersTryDraw(BlackjackGame blackjackGame, Deck deck) {
        List<CardHolder> cardHolders = blackjackGame.getRawPlayers();
        cardHolders.forEach(cardHolder -> playerTryDraw(deck, cardHolder));
    }

    private void playerTryDraw(Deck deck, CardHolder cardHolder) {
        while(!cardHolder.isDead() && inputYesOrNo(cardHolder.getRawName())) {
            cardHolder.draw(deck, new PlayerRandomCardDrawStrategy(cardHolder));
            printPlayer(cardHolder);
        }
    }

    private void dealerTryDraw(BlackjackGame blackjackGame, Deck deck) {
        blackjackGame.dealerTryDraw(deck);
    }

    private static boolean inputYesOrNo(String playerName) {
        OutputView.printPlayerAdditionalCardMessage(playerName);
        return YesOrNoInputView.getYNAsBoolean();
    }

    private void printDealerAndPlayersWithPoint(BlackjackGame blackjackGame) {
        GamerDTO dealerDTO = makeGamerDTO(blackjackGame.getCardHolderDealer());
        GamerOutputView.generateOutputWithSummationCardPoint(dealerDTO);

        for (CardHolder player : blackjackGame.getRawPlayers()) {
            GamerDTO playerDTO = makeGamerDTO(player);
            GamerOutputView.generateOutputWithSummationCardPoint(playerDTO);
        }
    }

    private void printDealerAndPlayersGameResult(BlackjackGame blackjackGame) {
        OutputView.printGameResultMessage();
        GameProfitDTO gameProfitDTO = makeGameProfitDTO(blackjackGame);
        GameProfitOutputView.print(gameProfitDTO);
    }

    private GameProfitDTO makeGameProfitDTO(BlackjackGame blackjackGame) {
        List<String> playersName = blackjackGame.getRawPlayerNames();
        GameProfit gameProfit = blackjackGame.getGameProfit();

        return new GameProfitDTO(DEALER_NAME, playersName, gameProfit.dealerProfit(), gameProfit.playersProfit());
    }
}
