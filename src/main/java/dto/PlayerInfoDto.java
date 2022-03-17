package dto;

import domain.player.Player;
import vo.Name;

public class PlayerInfoDto {
    private final Name name;
    private final boolean isDealer;

    private PlayerInfoDto(Name name, boolean isDealer) {
        this.name = name;
        this.isDealer = isDealer;
    }

    public static PlayerInfoDto from(Player player) {
        return new PlayerInfoDto(Name.from(player.getName()), player.isDealer());
    }

    public String getName() {
        return name.getName();
    }

    public boolean isDealer() {
        return isDealer;
    }
}
