package top.chier.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class Materials {


    public static Item[] items = {
            new Item(new FabricItemSettings()), //mana_crystal_fragment
            new Item(new FabricItemSettings()),  //vital_crystal
            new Item(new FabricItemSettings().maxCount(1)) //rotten_divine_wood_staff
    };

    public static String[] itemName = {
            "mana_crystal_fragment",
            "vital_crystal",
            "rotten_divine_wood_staff"
    };
}
