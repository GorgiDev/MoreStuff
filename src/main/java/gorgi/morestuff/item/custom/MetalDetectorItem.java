package gorgi.morestuff.item.custom;

import gorgi.morestuff.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos positionClicked =  context.getBlockPos();
            BlockState clickedState = context.getWorld().getBlockState(positionClicked);
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;
            int limit = 64;

            if (isValuableBlock(clickedState)) {
                if (player != null) {
                    player.sendMessage(Text.literal("You're literally clicking on it..."), true);
                }

                foundBlock = true;
            }

            for (int i = 1; i <= limit && !foundBlock; i++) {
                for (int x = -1; x <= 1 && !foundBlock; x++) {
                    for (int z = -1; z <= 1 && !foundBlock; z++) {

                        BlockPos currentPos = positionClicked.add(x, -i, z);
                        BlockState state = context.getWorld().getBlockState(currentPos);

                        if (isValuableBlock(state)) {
                            if (player != null) {
                                outputValuableCoords(currentPos, positionClicked, player);
                            }
                            foundBlock = true;
                            break;
                        }
                    }
                }
            }

            if (!foundBlock) {
                if (player != null) {
                    player.sendMessage(Text.literal("No valuables found!"), true);
                }
            }
        }

        if (context.getPlayer() != null) {
            context.getStack().damage(1, context.getPlayer(),
                    LivingEntity.getSlotForHand(context.getHand()));
        }

        return ActionResult.SUCCESS;
    }

    private void outputValuableCoords(BlockPos foundPos, BlockPos clickedPos, PlayerEntity player) {
        int distance = clickedPos.getY() - foundPos.getY();
        if (distance <= 1) {
            player.sendMessage(Text.literal("Found valuable " + distance + " block down!"), true);
        } else {
            player.sendMessage(Text.literal("Found valuable " + distance + " blocks down!"), true);
        }
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isIn(ModTags.Blocks.DETECTABLE_BLOCKS);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.empty());
        tooltip.add(Text.literal("Checks for ").append(Text.literal("valuables")
                .formatted(Formatting.AQUA)).append(Text.literal("!")));
        tooltip.add(Text.empty());
        tooltip.add(Text.literal("3x3 radius").formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(Text.literal("64 block search depth").formatted(Formatting.GRAY, Formatting.ITALIC));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
