package de.odin.odininventories.inventory.components;

import de.odin.odininventories.inventory.OdinInventory;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface IClickable {

    void onClick(ButtonComponent component, InventoryClickEvent event, OdinInventory odinInventory);

}
