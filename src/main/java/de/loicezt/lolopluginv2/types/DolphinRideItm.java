package de.loicezt.lolopluginv2.types;

import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Horse;

public class DolphinRideItm {
    private Dolphin dolphin;
    private Horse horse;

    public DolphinRideItm(Dolphin d, Horse h) {
        this.dolphin = d;
        this.horse = h;
    }

    public Dolphin getDolphin() {
        return dolphin;
    }

    public void setDolphin(Dolphin dolphin) {
        this.dolphin = dolphin;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }
}
