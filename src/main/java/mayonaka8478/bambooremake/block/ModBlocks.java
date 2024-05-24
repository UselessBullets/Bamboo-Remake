package mayonaka8478.bambooremake.block;

import mayonaka8478.bambooremake.BambooRemake;
import mayonaka8478.bambooremake.IDUtils;
import net.minecraft.client.render.block.model.BlockModelAxisAligned;
import net.minecraft.client.render.block.model.BlockModelCropsWheat;
import net.minecraft.client.render.block.model.BlockModelCrossedSquares;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockAxisAligned;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

public class ModBlocks {
	//tatami
	public static final Block tatami = new BlockBuilder(BambooRemake.MOD_ID)
		.setResistance(5.0f)
		.setHardness(0.1f)
		.setBlockSound(BlockSounds.GRASS)
		.setBlockModel(block -> {
			return new BlockModelAxisAligned<>(block).withTextures("bambooremake:block/tatami_top", "bambooremake:block/tatami");
		})
		.build(new BlockAxisAligned("tatami", IDUtils.getCurrBlockId(), Material.decoration));
	//bamboo_works
	public static final Block bamboo_works = new BlockBuilder(BambooRemake.MOD_ID)
		.setResistance(3.0f)
		.setHardness(2.0f)
		.setBlockSound(BlockSounds.WOOD)
		.setFlammability(5, 20)
		.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
		.setTextures("bambooremake:block/bamboo_works")
		.build(new BlockAxisAligned("bamboo_works", IDUtils.getCurrBlockId(), Material.decoration));
	//bamboo_shoot
	public static final Block bamboo_shoot = new BlockBuilder(BambooRemake.MOD_ID)
		.setResistance(3.0f)
		.setHardness(0.0f)
		.setBlockSound(BlockSounds.STONE)
		.setBlockModel(block -> new BlockModelCrossedSquares<>(block).withTextures("bambooremake:block/bamboo_shoot"))
		.build(new BambooShoot("bamboo_shoot", IDUtils.getCurrBlockId()));
	//bamboo
	public static final Block bamboo = new BlockBuilder(BambooRemake.MOD_ID)
		.setResistance(3.0f)
		.setHardness(0.0f)
		.setBlockSound(BlockSounds.STONE)
		.setBlockModel(block -> new BlockModelCropsWheat<>(block).withTextures("bambooremake:block/bamboo"))
		.build(new Bamboo("bamboo", IDUtils.getCurrBlockId()));

	public static void createBlocks() {

	}
}