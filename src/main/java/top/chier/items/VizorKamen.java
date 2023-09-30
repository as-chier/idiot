package top.chier.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import top.chier.Mana;

public class VizorKamen extends Item {

    public VizorKamen(Settings settings) {
        super(settings);
    }

    //The instance of magic stick "Vizor Kamen"
    public static final Item vizor_kamen = new VizorKamen(new FabricItemSettings().maxDamage(65));
    //The sound of "Vizor Kamen"
    public static final Identifier VizorKamen_Sound = new Identifier("idiot:vizor_kamen_sound");
    //The sound event of "Vizor Kamen"
    public static SoundEvent VizorKamen_SoundEvent = SoundEvent.of(VizorKamen_Sound);
    //The magic value consumed when using
    public static final int spendManaValue = 20;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Check if the mana value is enough to use
        if(Mana.manaValue% spendManaValue >= 1) {
            //Check if the cooling time has passed
            if (!playerEntity.getItemCooldownManager().isCoolingDown(this)) {
                //Play the sound of this magic stick
                playerEntity.playSound(VizorKamen_SoundEvent, 1.0F, 1.0F);
                //Regenerate logic: Regenerate 2 drops of blood
                playerEntity.heal(20);
                //Cooling time (in game scale units)
                int cooldownTicks = 150;
                //Set cooling time
                playerEntity.getItemCooldownManager().set(this, cooldownTicks);
                //Deduct Magic Points
                Mana.manaValue = Mana.manaValue - spendManaValue;
            }
        }

        //When the item is damaged, compensate the player
        ItemStack heldItemStack = playerEntity.getStackInHand(hand);
        if(heldItemStack.getDamage() - 1 == 0){
            playerEntity.getInventory().offerOrDrop(new ItemStack(Materials.items[0], Random.create().nextBetween(2,5)));
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}



