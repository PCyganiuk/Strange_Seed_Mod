package net.paveuu.smolbartek.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;
import net.paveuu.smolbartek.block.ModBlocks;
import net.paveuu.smolbartek.block.custom.StrangeSeedCropBlock;
import net.paveuu.smolbartek.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables(){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    @Override
    protected void generate() {
        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.STRANGE_PLANT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrangeSeedCropBlock.AGE,3));

        this.add(ModBlocks.STRANGE_PLANT_CROP.get(),createCropDrops(ModBlocks.STRANGE_PLANT_CROP.get(), ModItems.STRANGE_PLANT_EGG.get(),
                Items.FLOWER_POT,lootItemConditionBuilder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
