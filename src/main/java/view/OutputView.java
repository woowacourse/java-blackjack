package view;

import dto.AllPlayersNameAndCardsResponse;
import dto.NameAndCardsResponse;
import dto.PlayedGameResultResponse;
import dto.PlayerGameResultsResponse;
import dto.PlayerNamesResponse;
import java.util.List;

public final class OutputView {

    private static final String JOINER = ", ";

    private OutputView() {

    }

    public static void distributeCards(PlayerNamesResponse response) {
        String players = String.join(JOINER, response.names());
        System.out.println(OutputMessage.DISTRIBUTE.description(players));
    }

    public static void initDealerCardInfos(NameAndCardsResponse response) {
        String cards = response.cardInfos().getFirst();
        System.out.println(OutputMessage.NAME_AND_CARDS.description(response.name(), cards));
    }

    public static void initAllPlayerCardInfos(AllPlayersNameAndCardsResponse response) {
        List<NameAndCardsResponse> infos = response.allInfos();
        infos.forEach(OutputView::playerCardInfos);
    }

    public static void playerCardInfos(NameAndCardsResponse response) {
        System.out.println(nameAndAllCards(response.name(), response.cardInfos()));
    }

    private static String nameAndAllCards(String name, List<String> cardInfos) {
        String cards = String.join(JOINER, cardInfos);
        return OutputMessage.NAME_AND_CARDS.description(name, cards);
    }

    public static void dealerDrawCard() {
        System.out.println(OutputMessage.DEALER_DRAW_CARD.description());
    }

    public static void participantResult(PlayedGameResultResponse response) {
        String nameAndCards = nameAndAllCards(response.name(), response.cardInfos());
        int score = response.scoreSum();

        System.out.println(OutputMessage.PARTICIPANTS_RESULT.description(nameAndCards, score));
    }

    public static void playerResults(PlayerGameResultsResponse playerGameResultsResponse) {
        playerGameResultsResponse.results()
                .forEach(OutputView::participantResult);
    }

    public static void printTaskDivider() {
        System.out.println();
    }
}
