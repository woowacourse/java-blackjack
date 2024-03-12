package blackjackgame.controller;

import blackjackgame.domain.BlackjackGame;
import blackjackgame.domain.blackjack.BetMoney;
import blackjackgame.domain.blackjack.GameResult;
import blackjackgame.domain.blackjack.GameResultCalculator;
import blackjackgame.domain.blackjack.HoldingCards;
import blackjackgame.domain.blackjack.PlayerRandomCardDrawStrategy;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.gamers.BetMaker;
import blackjackgame.domain.gamers.BetMakers;
import blackjackgame.domain.gamers.CardHolder;
import blackjackgame.domain.gamers.CardHolders;
import blackjackgame.domain.gamers.Gamer;
import blackjackgame.domain.gamers.Gamers;
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
        BetMaker betMakerDealer = makeBetMakerDealer();
        List<String> playerNames = NameInputView.getNames();
        CardHolders cardHolderPlayers = new CardHolders(makeCardHolderPlayers(playerNames));
        BetMakers betMakerPlayers = new BetMakers(makeBetMakerPlayers(playerNames));

        Gamer dealer = new Gamer(cardHolderDealer, betMakerDealer);
        Gamers players = new Gamers(cardHolderPlayers, betMakerPlayers);

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
