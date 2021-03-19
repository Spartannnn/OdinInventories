package de.odin.odininventories.inventory.components;

import de.odin.odininventories.inventory.util.Position;
import org.bukkit.inventory.ItemStack;

public interface IOdinComponent {

    String getName();

    void setPosition(Position position);

    Position getPosition();

    ItemStack getItemStack();

    void changeItemStack(ItemStack newStack);

}
