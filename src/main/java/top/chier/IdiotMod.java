package top.chier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import top.chier.items.VizorKamen;

import static top.chier.items.Materials.itemName;
import static top.chier.items.Materials.items;


public class IdiotMod implements ModInitializer {

	//This mod's ID
	public static final String MOD_ID = "idiot";


	//The item group of Magic Sticks
	private static final ItemGroup MagicStick_ItemGroup = FabricItemGroup.builder()
			.icon(() -> new ItemStack(VizorKamen.vizor_kamen))
			.displayName(Text.translatable("魔杖"))
			.entries((context, entries) -> {
				entries.add(VizorKamen.vizor_kamen);
			})
			.build();

	//The item group of material
	private static final ItemGroup Material_ItemGroup = FabricItemGroup.builder()
			.icon( () -> new ItemStack(items[0]) )
			.displayName(Text.translatable("材料"))
			.entries((context,entries) -> {
                for (net.minecraft.item.Item item : items) {
                    entries.add(item);
                }
			})
			.build();

	@Override
	public void onInitialize() {
		//Init the sound of item "Vizor Kamen"
		Registry.register(Registries.SOUND_EVENT, VizorKamen.VizorKamen_Sound, VizorKamen.VizorKamen_SoundEvent);
		//Init the magic stick "Vizor Kamen" as an item.
		Registry.register(Registries.ITEM,new Identifier(MOD_ID, "vizor_kamen"), VizorKamen.vizor_kamen);

		//Registry this mod's materials
		for (int i = 0; i < items.length; i++ ){
			Registry.register(Registries.ITEM,new Identifier(IdiotMod.MOD_ID, itemName[i]), items[i]);
		}

		Registry.register(Registries.ITEM_GROUP,new Identifier(MOD_ID,"magic_stick_itemgroup"),MagicStick_ItemGroup);
		Registry.register(Registries.ITEM_GROUP,new Identifier(MOD_ID,"material_itemgroup"),Material_ItemGroup);

		//Regularly recovery mana value
		Thread ManaRecovery = new Thread(new Runnable() {
			public void run() {
				while (true){
					// sleep 1s
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					//Restore mana value
					Mana.manaValue = Mana.manaValue + 1;
				}
			}
		});
		ManaRecovery.start();
	}
}