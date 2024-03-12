package blackjackgame.controller;

import blackjackgame.domain.blackjack.BetMaker;
import blackjackgame.domain.blackjack.BetMakers;
import blackjackgame.domain.blackjack.BetMoney;
import blackjackgame.domain.blackjack.BlackjackGame;
import blackjackgame.domain.blackjack.CardHolder;
import blackjackgame.domain.blackjack.GameResult;
import blackjackgame.domain.blackjack.GameResultCalculator;
import blackjackgame.domain.blackjack.CardHolders;
import blackjackgame.domain.blackjack.HoldingCards;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Deck;
import blackjackgame.dto.CardDTO;
import blackjackgame.dto.DealerGameResultDTO;
import blackjackgame.dto.GamerDTO;
import blackjackgame.dto.PlayerGameResultDTO;
import blackjackgame.view.BetMoneyInputView;
import blackjackgame.view.GameResultOutputView;
import blackjackgame.view.GamerOutputView;
import blackjackgame.view.NameInputView;
import blackjackgame.view.OutputView;
import blackjackgame.view.YesOrNoInputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {
    private static final String DEALER_NAME = "딜러";
    private static final int INITIAL_MONEY = 0;

    public void startBlackjackGame(Deck deck) {
        BlackjackGame blackjackGame = initBlackJackGame();
        playBlackJackGame(blackjackGame, deck);
    }

    private static BlackjackGame initBlackJackGame() {
        CardHolder cardHolderDealer = makeCardHolderDealer();
        BetMaker betMakerGamer = makeBetMakerDealer();

        List<String> playerNames = NameInputView.getNames();
        CardHolders cardHolderGamers = new CardHolders(makeCardHolderPlayers(playerNames));
        BetMakers betMakerGamers = new BetMakers(makeBetMakerPlayers(playerNames));

        return new BlackjackGame(cardHolderDealer, betMakerGamer, cardHolderGamers, betMakerGamers);
    }

    private void playBlackJackGame(BlackjackGame blackjackGame, Deck deck) {
        initDealerAndPlayers(blackjackGame, deck);
        printDealerAndPlayers(blackjackGame);

        playersTryDraw(blackjackGame, deck);
        dealerTryDraw(blackjackGame, deck);

        printDealerAndPlayersWithPoint(blackjackGame);
        printDealerAndPlayersGameResult(blackjackGame);
    }

    private static CardHolder makeCardHolderDealer() {
        return new CardHolder(DEALER_NAME, HoldingCards.of());
    }

    private static BetMaker makeBetMakerDealer() {
        return new BetMaker(DEALER_NAME, new BetMoney(INITIAL_MONEY));
    }

    private static List<CardHolder> makeCardHolderPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new CardHolder(playerName, HoldingCards.of()))
                .toList();
    }

    private static List<BetMaker> makeBetMakerPlayers(List<String> playerNames) {
        List<BetMaker> betMakerGamers = new ArrayList<>();

        List<Integer> playerBetMoneys = BetMoneyInputView.getPlayerBetMoneys(playerNames);
        for (int i = 0; i < playerNames.size(); i++) {
            BetMaker betMakerGamer = new BetMaker(playerNames.get(i), new BetMoney(playerBetMoneys.get(i)));
            betMakerGamers.add(betMakerGamer);
        }
        return betMakerGamers;
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
        List<CardHolder> players = blackjackGame.getRawPlayers();
        players.forEach(player -> playerTryDraw(blackjackGame, deck, player));
    }

    private void playerTryDraw(BlackjackGame blackjackGame, Deck deck, CardHolder player) {
        while(!blackjackGame.isPlayerDead(player) && inputYesOrNo(player)) {
            blackjackGame.playerTryDraw(deck, player);
            printPlayer(player);
        }
    }

    private void dealerTryDraw(BlackjackGame blackjackGame, Deck deck) {
        blackjackGame.dealerTryDraw(deck);
    }

    private static boolean inputYesOrNo(CardHolder player) {
        OutputView.printPlayerAdditionalCardMessage(player.getRawName());
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
        GameResultOutputView.print(makeDealerGameResultDTO(blackjackGame));

        List<PlayerGameResultDTO> playerGameResultDTOS = makePlayerGameResultDTO(blackjackGame);
        playerGameResultDTOS.forEach(GameResultOutputView::print);
    }

    private DealerGameResultDTO makeDealerGameResultDTO(BlackjackGame blackjackGame) {
        Map<GameResult, Integer> dealerGameResultCounts = blackjackGame.getRawPlayers().stream()
                .collect(Collectors.groupingBy(player -> GameResultCalculator.calculate(blackjackGame.getCardHolderDealer(), player),
                        Collectors.summingInt(value -> 1)));
        return new DealerGameResultDTO(dealerGameResultCounts);
    }

    private List<PlayerGameResultDTO> makePlayerGameResultDTO(BlackjackGame blackjackGame) {
        return blackjackGame.getRawPlayers().stream()
                .map(player -> new PlayerGameResultDTO(player.getRawName(),
                        GameResultCalculator.calculate(player, blackjackGame.getCardHolderDealer())))
                .toList();
    }
}
