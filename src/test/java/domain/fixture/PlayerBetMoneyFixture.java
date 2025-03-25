package domain.fixture;

import domain.betting.PlayerBetMoney;

public class PlayerBetMoneyFixture {
    public static final PlayerBetMoney FIRST_10000 = new PlayerBetMoney(
            PlayerNameFixture.playerNameFirst,
            MoneyFixture.money10000);
    public static final PlayerBetMoney FIRST_20000 = new PlayerBetMoney(
            PlayerNameFixture.playerNameFirst,
            MoneyFixture.money20000);
    public static final PlayerBetMoney SECOND_10000 = new PlayerBetMoney(
            PlayerNameFixture.playerNameSecond,
            MoneyFixture.money10000);
    public static final PlayerBetMoney SECOND_20000 = new PlayerBetMoney(
            PlayerNameFixture.playerNameSecond,
            MoneyFixture.money20000);
}
