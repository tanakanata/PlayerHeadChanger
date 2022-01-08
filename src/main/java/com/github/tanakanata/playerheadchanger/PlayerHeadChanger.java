package com.github.tanakanata.playerheadchanger;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerHeadChanger extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        //右クリックしたエンティティがプレイヤーか判定
        if (event.getRightClicked().getType() != EntityType.PLAYER){
            return;
        }
        //プレイヤーがスニークしているか判定
        if (player.isSneaking()){
            return;
        }
        //メインハンドか判定
        if(event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        //プレイヤーがPLAYER_HEADを持っているか判定
        if (item.getType() != Material.PLAYER_HEAD) {
            return;
        }

        Player clicked_player = (Player) event.getRightClicked();
        SkullMeta skull_meta = (SkullMeta) item.getItemMeta();
        skull_meta.setOwningPlayer(clicked_player);
        item.setItemMeta(skull_meta);
        player.sendMessage(clicked_player.getName()+"の頭に変更しました。");
        event.setCancelled(true);
}
}
