package net.paveuu.smolbartek.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.paveuu.smolbartek.SmolBartekMod;
import net.paveuu.smolbartek.block.ModBlocks;
import net.paveuu.smolbartek.block.custom.StrangeSeedCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SmolBartekMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeStrangeCrop((CropBlock) ModBlocks.STRANGE_PLANT_CROP.get(),"strange_plant_stage","strange_plant_stage");
    }

    public void makeStrangeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strangeCropStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strangeCropStates(BlockState state, CropBlock block, String modelName, String textureName) {
        int age = state.getValue(((StrangeSeedCropBlock) block).getAgeProperty());
        ConfiguredModel[] models = new ConfiguredModel[1];
        if(age == 3){
            models[0] = new ConfiguredModel(models().cube(modelName + state.getValue(((StrangeSeedCropBlock) block).getAgeProperty()),
                    new ResourceLocation(SmolBartekMod.MOD_ID,"block/strange_plant_stage3_other"),
                    new ResourceLocation(SmolBartekMod.MOD_ID,"block/strange_plant_stage3_other"),
                    new ResourceLocation(SmolBartekMod.MOD_ID,"block/strange_plant_stage3_front"),
                    new ResourceLocation(SmolBartekMod.MOD_ID,"block/strange_plant_stage3_other"),
                    new ResourceLocation(SmolBartekMod.MOD_ID,"block/strange_plant_stage3_side"),
                    new ResourceLocation(SmolBartekMod.MOD_ID,"block/strange_plant_stage3_side")).renderType("cutout"));
        }
        else {
            models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(((StrangeSeedCropBlock) block).getAgeProperty()),
                    new ResourceLocation(SmolBartekMod.MOD_ID, "block/" + textureName + state.getValue(((StrangeSeedCropBlock) block).getAgeProperty()))).renderType("cutout"));
        }
        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }

}
