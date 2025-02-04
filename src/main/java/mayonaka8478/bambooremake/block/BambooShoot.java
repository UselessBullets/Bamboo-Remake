package mayonaka8478.bambooremake.block;

import mayonaka8478.bambooremake.ModMaterial;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.enums.LightLayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

import java.util.Random;

public class BambooShoot extends Block {
	public BambooShoot(String key, int id) {
		super(key, id, ModMaterial.bamboo_shoot);
		this.setTicking(true);
		float f = 0.2f;
		this.setBlockBounds(0.5f - f, 0.0, 0.5f - f, 0.5f + f, f * 3.0f, 0.5f + f);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.getSavedLightValue(LightLayer.Block, x, y, z) >= 13) {

			int i1 = world.getBlockMetadata(x, y, z);
			if (i1 == 15) {
				world.setBlockWithNotify(x, y, z, ModBlocks.bamboo.id);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, i1 + 1);
			}
		}
	}

	@Override
	public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
		return null;
	}

	@Override
	public boolean isSolidRender() {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return (world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
	}

	protected boolean canThisPlantGrowOnThisBlockID(int i) {
		if (Block.blocksList[i] == null) {
			return false;
		}
		return Block.blocksList[i].hasTag(BlockTags.GROWS_FLOWERS);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		super.onNeighborBlockChange(world, x, y, z, blockId);
		this.func_268_h(world, x, y, z);
	}

	protected final void func_268_h(World world, int i, int j, int k) {
		if (!this.canBlockStay(world, i, j, k)) {
			this.dropBlockWithCause(world, EnumDropCause.WORLD, i, j, k, world.getBlockMetadata(i, j, k), null);
			world.setBlockWithNotify(i, j, k, 0);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PROPER_TOOL:
				return new ItemStack[]{new ItemStack(this)};
			default:
				return null;
		}
	}

}
