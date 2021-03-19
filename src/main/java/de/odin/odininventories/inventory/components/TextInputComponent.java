package de.odin.odininventories.inventory.components;

import com.comphenix.packetwrapper.WrapperPlayServerBlockChange;
import com.comphenix.packetwrapper.WrapperPlayServerOpenSignEditor;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import de.odin.odininventories.OdinInventoriesLauncher;
import de.odin.odininventories.inventory.OdinInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TextInputComponent extends ButtonComponent {

    public TextInputComponent(String name, ItemStack itemStack) {
        super(name, itemStack, null);
    }

    @Override
    public void click(InventoryClickEvent event, OdinInventory odinInventory) {
        Player player = (Player) event.getWhoClicked();
        int x = player.getLocation().getBlockX();
        int y = 255;
        int z = player.getLocation().getBlockZ();
        BlockPosition blockPos = new BlockPosition(x, y, z);

        WrapperPlayServerBlockChange blockChangePacket = new WrapperPlayServerBlockChange();
        WrappedBlockData blockData = WrappedBlockData.createData(Material.OAK_SIGN);
        blockChangePacket.setBlockData(blockData);
        blockChangePacket.setLocation(blockPos);
        blockChangePacket.sendPacket(player);

        WrapperPlayServerOpenSignEditor packet = new WrapperPlayServerOpenSignEditor();
        packet.setLocation(new BlockPosition(x, y, z));
        packet.sendPacket(player);
        OdinInventoriesLauncher.PLAYER_BLOCK_POS.put(player.getUniqueId(), blockPos);
    }
}
