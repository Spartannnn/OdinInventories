package de.odin.odininventories;

import com.comphenix.packetwrapper.WrapperPlayClientUpdateSign;
import com.comphenix.packetwrapper.WrapperPlayServerBlockChange;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import de.odin.odininventories.inventory.OdinInventory;
import de.odin.odininventories.inventory.components.ButtonComponent;
import de.odin.odininventories.inventory.components.LabelComponent;
import de.odin.odininventories.inventory.components.TextInputComponent;
import de.odin.odininventories.inventory.layout.CustomLayout;
import de.odin.odininventories.inventory.util.Position;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.UUID;

public class OdinInventoriesLauncher extends JavaPlugin {

    public static final Hashtable<UUID, BlockPosition> PLAYER_BLOCK_POS = new Hashtable<>();

    private static OdinInventoriesLauncher instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        System.out.println("Pray our father ODIN! OdinInventories successfully started. Hope you still alive ;)");
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(this, PacketType.Play.Client.UPDATE_SIGN) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                Player player = event.getPlayer();
                WrapperPlayClientUpdateSign packet = new WrapperPlayClientUpdateSign(event.getPacket());
                BlockPosition bp = packet.getLocation();
                BlockPosition playerBlockPos = PLAYER_BLOCK_POS.get(player.getUniqueId());
                if (playerBlockPos != null) {
                    if (bp.getX() == playerBlockPos.getX() && bp.getY() == playerBlockPos.getY() && bp.getZ() == playerBlockPos.getZ()) {
                        System.out.println(Arrays.toString(packet.getLines()));
                    }
                    WrapperPlayServerBlockChange blockChangePacket = new WrapperPlayServerBlockChange();
                    WrappedBlockData blockData = WrappedBlockData.createData(Material.AIR);
                    blockChangePacket.setBlockData(blockData);
                    blockChangePacket.setLocation(playerBlockPos);
                    blockChangePacket.sendPacket(player);
                    PLAYER_BLOCK_POS.remove(player.getUniqueId());
                }
            }
        });
        Bukkit.getPluginCommand("odin").setExecutor(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            OdinInventory odinInventory = new OdinInventory(5, "Test Inventory");
            CustomLayout layout = new CustomLayout();
            layout.setComponent(new Position(2, 5), new LabelComponent("Bruh", new ItemStack(Material.DIAMOND)));
            layout.setComponent(new Position(1, 2), new ButtonComponent("TestBruh", new ItemStack(Material.IRON_INGOT), (button, event, inventory) -> {
                System.out.println("Ich wurde geklickt. Yeay");
            }));
            layout.setComponent(new Position(1, 3), new TextInputComponent("TestInput", new ItemStack(Material.FEATHER)));
            odinInventory.setLayout(layout);
            odinInventory.open((Player) sender);
        }
        return true;
    }

    public static OdinInventoriesLauncher getInstance() {
        return instance;
    }
}
