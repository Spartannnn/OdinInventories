package de.odin.odininventories.inventory.layout;

import de.odin.odininventories.inventory.components.IOdinComponent;
import de.odin.odininventories.inventory.util.Position;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class CustomLayout implements OdinLayout{

    private final Map<Position, IOdinComponent> componentMap;

    public CustomLayout() {
        this.componentMap = new HashMap<>();
    }

    public CustomLayout(Map<Position, IOdinComponent> componentMap) {
        this.componentMap = componentMap;
    }

    /**
     * Example:
     * The Grid:
     * 1 2 3 4 5 6 7 8 9 //Row 1
     * A B C D E F G H I //Row 2
     * J K L M N O P Q R //Row 3
     * S T U V W X Y Z Ö //Row 4
     * //Column 0 - 9
     * Then the position will be: new Position(1, 4) => On this position in the grid: P(1, 4) and in mc inv: pos = 3 [Character 4 in the grid];
     * Another example:
     * new Position(3, 8) => P(3, Q) and in mc inv: pos = 25 [Character Q in the grid]
     *
     * x var = the row
     * y var = column
     *
     * @param position
     * @param component
     */
    public void setComponent(Position position, IOdinComponent component) {
        component.setPosition(position);
        if(componentMap.containsKey(position)) {
            componentMap.replace(position, component);
            return;
        }
        componentMap.put(position, component);
    }

    public void apply(Inventory bukkitInventory) {
        for(Position position : componentMap.keySet()) {
            IOdinComponent component = componentMap.get(position);
            position = position.minus();
            int x = position.getX();
            int y = position.getY();
            bukkitInventory.setItem(x * 9 + y, component.getItemStack());
        }
    }

    @Override
    public Map<Position, IOdinComponent> getComponents() {
        return this.componentMap;
    }
}
