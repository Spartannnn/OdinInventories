package de.odin.odininventories.inventory;

import de.odin.odininventories.OdinInventoriesLauncher;
import de.odin.odininventories.inventory.components.ButtonComponent;
import de.odin.odininventories.inventory.components.IClickable;
import de.odin.odininventories.inventory.components.IOdinComponent;
import de.odin.odininventories.inventory.layout.OdinLayout;
import de.odin.odininventories.inventory.util.Position;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class OdinInventory {

    private int rows;
    private String name;
    private Inventory bukkitInventory;
    private OdinLayout layout;

    public OdinInventory(int rows, String name) {
        this.rows = rows;
        this.name = name;
        this.bukkitInventory = Bukkit.createInventory(null, rows * 9, name);
    }

    public void setLayout(OdinLayout layout) {
        this.layout = layout;
    }

    public void open(Player player) {
        Validate.notNull(layout, "Can not open odin inventory. Layout is null");
        layout.apply(this.bukkitInventory);
        Bukkit.getPluginManager().registerEvents(new Listeners(bukkitInventory, this), OdinInventoriesLauncher.getInstance());
        player.openInventory(bukkitInventory);
    }

    public OdinLayout getLayout() {
        return layout;
    }

    public static class Listeners implements Listener {

        private Inventory bukkitInventory;
        private OdinInventory odinInventory;

        public Listeners(Inventory bukkitInventory, OdinInventory odinInventory) {
            this.bukkitInventory = bukkitInventory;
            this.odinInventory = odinInventory;
        }

        @EventHandler
        public void onClick(InventoryClickEvent event) {
            if(event.getClickedInventory() == null) return;
            if(event.getClickedInventory().equals(bukkitInventory)) {
                for(Position position : odinInventory.getLayout().getComponents().keySet()) {
                    if(position.asSlot() == event.getSlot()) {
                        IOdinComponent component = odinInventory.getLayout().getComponents().get(position);
                        if(component instanceof ButtonComponent) {
                            ((ButtonComponent) component).click(event, odinInventory);
                            event.setCancelled(true);
                            return;
                        }
                    }
                }

            }
        }


    }

}
