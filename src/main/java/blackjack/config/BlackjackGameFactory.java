//package blackjack.config;
//
//import blackjack.core.BlackjackGame;
//import blackjack.domain.card.ShuffledCardsGenerator;
//import blackjack.view.BlackjackView;
//import blackjack.view.InputView;
//import blackjack.view.OutputView;
//
//public class BlackjackGameFactory {
//    public BlackjackGame createGame() {
//        return new BlackjackGame(view(), new ShuffledCardsGenerator());
//    }
//
//    private BlackjackView view() {
//        return new BlackjackView(new InputView(), new OutputView());
//    }
//
//}
