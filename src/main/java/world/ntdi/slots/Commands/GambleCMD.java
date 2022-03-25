package world.ntdi.slots.Commands;

import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;
import world.ntdi.slots.Goowey.GambleGUI;

public class GambleCMD {
    @CommandHook("slots_play")
    public void slotsPlayer(Player p){
        new GambleGUI(p);
    }
}
