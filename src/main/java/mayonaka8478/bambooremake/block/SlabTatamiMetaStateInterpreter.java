package mayonaka8478.bambooremake.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import org.useless.dragonfly.model.blockstates.processed.MetaStateInterpreter;

import java.util.HashMap;
import java.util.Locale;

public class SlabTatamiMetaStateInterpreter extends MetaStateInterpreter {

	@Override
	public HashMap<String, String> getStateMap(WorldSource worldSource, int x, int y, int z, Block block, int meta) {
		Side side = Side.getSideById(meta & 0b111); // Get the stored axis of the slab
		boolean isDoubled = (meta & 0b1000) != 0;

		HashMap<String, String> result = new HashMap<>();
		result.put("doubled",isDoubled ? "true" : "false");
		result.put("facing", side.name().toLowerCase(Locale.ROOT));

		System.out.println(result);
		return result;
	}
}
