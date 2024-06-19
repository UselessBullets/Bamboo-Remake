package mayonaka8478.bambooremake.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockSlab;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class SlabTatami extends BlockSlab {
	public SlabTatami(Block modelBlock, int id) {
		super(modelBlock, id);
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		// Side is the side of the block you placed on, so if you place on the top of a block Side will equal SIDE.TOP
		world.setBlockMetadataWithNotify(x, y, z, side.getOpposite().getId());
	}

	@Override
	public void setBlockBoundsBasedOnState(WorldSource world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean isDoubled = (meta & 0b1000) != 0;
		if (isDoubled){
			this.setBlockBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
			return;
		}

		Side side = Side.getSideById(meta & 0b111);
		switch (side){
			case BOTTOM:
				this.setBlockBounds(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
				break;
			case TOP:
				this.setBlockBounds(0.0, 0.5, 0.0, 1.0, 1.0, 1.0);
				break;
			case NORTH:
				this.setBlockBounds(0.0, 0.0, 0.0, 1.0, 1.0, 0.5);
				break;
			case SOUTH:
				this.setBlockBounds(0.0, 0.0, 0.5, 1.0, 1.0, 1.0);
				break;
			case WEST:
				this.setBlockBounds(0.0, 0.0, 0.0, 0.5, 1.0, 1.0);
				break;
			case EAST:
				this.setBlockBounds(0.5, 0.0, 0.0, 1.0, 1.0, 1.0);
				break;
		}
	}

	@Override
	public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean isDoubled = (meta & 0b1000) != 0;
		if (isDoubled){
			return AABB.getBoundingBoxFromPool(x, y, z, x + 1.0f, y + 1.0f, z + 1.0f);
		}

		Side side = Side.getSideById(meta & 0b111);
		switch (side){
			case BOTTOM:
				return AABB.getBoundingBoxFromPool(x, y, z, x + 1.0f, y + 0.5f, z + 1.0f);
			case TOP:
				return AABB.getBoundingBoxFromPool(x, y + 0.5f, z, x + 1.0f, y + 1.0f, z + 1.0f);
			case NORTH:
				return AABB.getBoundingBoxFromPool(x, y, z, x + 1.0f, y + 1f, z + 0.5f);
			case SOUTH:
				return AABB.getBoundingBoxFromPool(x, y, z + 0.5f, x + 1.0f, y + 1.0f, z + 1.0f);
			case WEST:
				return AABB.getBoundingBoxFromPool(x, y, z, x + 0.5f, y + 1.0f, z + 1.0f);
			case EAST:
				return AABB.getBoundingBoxFromPool(x + 0.5f, y, z, x + 1.0f, y + 1.0f, z + 1.0f);
		}
		return AABB.getBoundingBoxFromPool(x, y, z, x + 1.0f, y + 1.0f, z + 1.0f);
	}
}
