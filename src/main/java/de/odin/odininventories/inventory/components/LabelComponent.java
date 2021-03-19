package de.odin.odininventories.inventory.components;

import de.odin.odininventories.inventory.util.Position;
import org.bukkit.inventory.ItemStack;

public class LabelComponent implements IOdinComponent {

    private final String name;
    private Position position;
    private ItemStack itemStack;

    public LabelComponent(String name, ItemStack itemStack) {
        this.name = name;
        this.itemStack = itemStack;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public void changeItemStack(ItemStack newStack) {
        this.itemStack = newStack;
    }

    @Override
    public ItemStack getItemStack() {
        return this.itemStack;
    }
}
