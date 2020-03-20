package domain.user;

import common.PlayerDto;
import common.PlayersDto;
import domain.UserInterface;
import domain.card.Card;
//todo: fix
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;
    private UserInterface userInterface;

    private Players(List<Player> players) {
        this.players = players;
    }

    private Players(List<Player> players, UserInterface userInterface) {
        this(players);
        this.userInterface = userInterface;
    }


    public static Players join(PlayersDto playersDto, UserInterface userInterface) {
        List<Player> players = new ArrayList<>();
        for (PlayerDto playerDto : playersDto.getPlayerDtos()) {
            Player player = Player.join(playerDto);
            players.add(player);
        }
        return new Players(players, userInterface);
    }

    private static Players update(List<Player> players) {
        return new Players(players);

    }

    public Players confirmCards(Dealer dealer) {
        for (Player player : players) {
            String wantToHit = userInterface.inputWantToHit(player.getName());
            while (player.wantToHit(wantToHit)) {
                Card card = dealer.passCard();
                player.hit(card);
                //todo: important fix
                OutputView.printCurrentStateOfPlayer(player.serialize());
                wantToHit = userInterface.inputWantToHit(player.getName());
            }
        }

        return update(players);
    }
}
