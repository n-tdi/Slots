package world.ntdi.slots.Goowey;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;
import world.ntdi.slots.Slots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GambleGUI {
    private InventoryGUI gui;
    private List<ItemStack> spinners = Arrays.asList(
            new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName(ChatColor.RED + "RED"),
            new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName(ChatColor.GOLD + "ORANGE"),
            new ItemBuilder(Material.YELLOW_STAINED_GLASS_PANE).setName(ChatColor.YELLOW + "YELLOW"),
            new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName(ChatColor.GREEN + "GREEN"),
            new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE).setName(ChatColor.BLUE + "BLUE"),
            new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE).setName(ChatColor.AQUA + "CYAN"),
            new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setName(ChatColor.LIGHT_PURPLE + "PURPLE"),
            new ItemBuilder(Material.MAGENTA_STAINED_GLASS_PANE).setName(ChatColor.DARK_PURPLE + "MAGENTA"),
            new ItemBuilder(Material.PINK_STAINED_GLASS_PANE).setName(ChatColor.LIGHT_PURPLE + "PINK"));


    public GambleGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 5*9, ChatColor.RED + "Slots"));
        setItems();
        gui.open(p);
    }

    public void setItems() {
        gui.fill(0, 5*9, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(""));
        ItemStack startItem = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.GREEN + "Start").setLore(ChatColor.GOLD + "Click to start the game");
        ItemButton startButton = ItemButton.create(startItem, e -> {
            gui.getButton(22).setItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            start();
        });
        gui.addButton(22, startButton);
        gui.update();
    }

    public int j = 2;
    public int k = 0;

    public void start() {
        int slot1 = 2;
        int slot2 = 4;
        int slot3 = 6;
        ItemButton slot1Button = ItemButton.create(getRandom(), e -> { e.setCancelled(true); });
        gui.addButton(slot1, slot1Button);

        ItemButton slot2Button = ItemButton.create(getRandom(), e -> { e.setCancelled(true); });
        gui.addButton(slot2, slot2Button);

        ItemButton slot3Button = ItemButton.create(getRandom(), e -> { e.setCancelled(true); });
        gui.addButton(slot3, slot3Button);

        gui.update();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (k < 5) {
                    moveDown(j);
                    j += 2;
                    moveDown(j);
                    j += 2;
                    moveDown(j);
                    j += 5;
                    gui.update();
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Slots.getInstance(), 10, 10);

        gui.update();
    }

    public void moveDown(int slot) {
        if (slot + 9 < 43) {
            ItemButton temp = gui.getButton(slot);
            gui.addButton(slot + 9, temp);
            gui.getButton(slot).setItem(getRandom());
        } else {
            gui.getButton(slot).setItem(getRandom());
        }
    }

    public ItemStack getRandom() {
        Random rand = new Random();
        return spinners.get(rand.nextInt(spinners.size()));
    }
}
