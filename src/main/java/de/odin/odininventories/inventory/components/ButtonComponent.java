package de.odin.odininventories.inventory.components;

import de.odin.odininventories.inventory.OdinInventory;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ButtonComponent extends LabelComponent {

    private final IClickable onClick;

    public ButtonComponent(String name, ItemStack itemStack, IClickable onClick) {
        super(name, itemStack);
        this.onClick = onClick;
    }

    public void click(InventoryClickEvent event, OdinInventory odinInventory) {
        this.onClick.onClick(this, event, odinInventory);
    }

}
