package controller;

import model.deck.Deck;
import model.name.Name;
import model.player.dto.PlayerResponse;
import model.players.Players;
import view.AlertView;
import view.AskView;
import view.GameView;
import view.ScoreBoardView;
import view.StatusView;

import java.io.IOException;
import java.util.List;

import static util.Rule.DEALER_MORE_SCORE;
import static util.Rule.GOAL_SCORE;
import static util.Rule.INIT_GIVE_CARDS;

public class GameController {

    private final Deck deck;
    private final GameView view;
    private final Players players;

    public GameController(final Deck deck, final GameView view) throws IOException {
        this.deck = deck;
        this.view = view;
        this.players = joinPlayers(view.getInput());
    }

    public void play() throws IOException {
        AskView.askPlayerNames();
        AlertView.alertGiveInitCard(Name.getDealer(), players.getPlayerNamesExceptDealer(), INIT_GIVE_CARDS.getValue());
        players.giveInitialCards(deck, INIT_GIVE_CARDS.getValue());

        for (PlayerResponse response : players.playersResponse()) {
            if (response.isDealerResponse()) {
                StatusView.printPersonDefaultStatus(response.getNameValue(), response.getCardsNameWithSecret());
                continue;
            }
            StatusView.printPersonDefaultStatus(response.getNameValue(), response.getCardsName());
        }

        System.out.println();

        for (String player : players.getPlayerNameValuesExceptDealer()) {
            while (players.isNotExceed(player, GOAL_SCORE.getValue()) && view.askWantMoreCard(player)) {
                players.giveOneCard(deck, player);
                PlayerResponse response = players.getPlayerResponseByName(player);
                StatusView.printPersonDefaultStatus(response.getNameValue(), response.getCardsName());
            }
        }

        if (players.dealerScoreEnough(Name.getDealer(), DEALER_MORE_SCORE.getValue())) {
            AlertView.alertDealerEnough(Name.getDealer(), DEALER_MORE_SCORE.getValue());
        }

        if (players.dealerScoreUnder(Name.getDealer(), DEALER_MORE_SCORE.getValue())) {
            AlertView.alertGiveDealerCard(Name.getDealer(), DEALER_MORE_SCORE.getValue());
            players.giveOneCard(deck, Name.getDealer());
        }

        for (PlayerResponse response : players.playersResponse()) {
            StatusView.printPlayerResultStatus(response.getNameValue(), response.getCardsName(), response.getScore());
        }

        AlertView.alertFinalGrade();

        List<PlayerResponse> playerResponsesWithGrade = players.calculateEachGradeWithGoal(GOAL_SCORE.getValue());
        PlayerResponse dealerResponse = playerResponsesWithGrade.get(0);
        for (PlayerResponse response : playerResponsesWithGrade) {
            if (response.equals(dealerResponse)) {
                ScoreBoardView.printDealerScoreBoard(response.getNameValue(),
                        players.getDealerWin(dealerResponse.getGrade()),
                        players.getDealerSame(dealerResponse.getGrade()),
                        players.getDealerLose(dealerResponse.getGrade()));
                continue;
            }
            if (response.isUpThanDealer(dealerResponse)) {
                ScoreBoardView.printWin(response.getNameValue());
                continue;
            }
            if (response.isSameWithDealer(dealerResponse)) {
                ScoreBoardView.printSame(response.getNameValue());
                continue;
            }
            ScoreBoardView.printLose(response.getNameValue());
        }
    }

    private Players joinPlayers(final String nameInput) {
        return Players.from(nameInput);
    }
}
