package blackjack;

import blackjack.core.BlackjackGame;
import blackjack.domain.card.ShuffledCardsGenerator;
import blackjack.dto.GameResultDtos;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDtos;
import blackjack.view.BlackjackView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        BlackjackView view = new BlackjackView(new InputView(), new OutputView());
        BlackjackGame game = BlackjackGame.create(new ShuffledCardsGenerator(), view.readPlayers());

        game.initialDeal();
        view.printInitialDeal(InitialDealDtos.of(game.getDealer(), game.getPlayers()));

        game.getPlayers().forEach(player -> {
            while (game.canHit(player) && view.isHitAnswer(player.getName())) {
                game.hit(player);
                view.printPlayerCards(ParticipantCardsDto.from(player));
            }
        });

        while (game.canHitDealer()) {
            game.hitDealer();
            view.printDealerHit(game.getDealerName());
        }

        view.printScore(
            ParticipantScoreDtos.of(game.getDealer(), game.getPlayers()));
        view.printResult(
            GameResultDtos.of(game.getDealer(), game.getPlayers()));
    }
}
