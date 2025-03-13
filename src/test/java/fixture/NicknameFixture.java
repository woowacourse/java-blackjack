package fixture;

import domain.gamer.Nickname;

public class NicknameFixture {
    public static Nickname createNickname(String name) {
        return new Nickname(name);
    }
}
