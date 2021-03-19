package de.odin.odininventories.inventory.layout;

import de.odin.odininventories.inventory.components.IOdinComponent;
import de.odin.odininventories.inventory.util.Position;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public interface OdinLayout {

    void apply(Inventory bukkitInventory);

    Map<Position, IOdinComponent> getComponents();

}
