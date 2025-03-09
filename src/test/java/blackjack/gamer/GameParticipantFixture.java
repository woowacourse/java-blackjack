package blackjack.gamer;

public class GameParticipantFixture {

    public static Player createPlayer(String nickname) {
        return Player.from(Nickname.from(nickname));
    }

    public static Dealer createDealer() {
        return Dealer.create();
    }
}
