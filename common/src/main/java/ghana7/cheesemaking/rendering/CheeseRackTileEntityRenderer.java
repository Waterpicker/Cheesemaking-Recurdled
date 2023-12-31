package ghana7.cheesemaking.rendering;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import ghana7.cheesemaking.tileentity.CheeseRackTileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;

import static net.minecraft.world.item.ItemDisplayContext.FIXED;

@Environment(EnvType.CLIENT)
public class CheeseRackTileEntityRenderer implements BlockEntityRenderer<CheeseRackTileEntity> {
    public CheeseRackTileEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(CheeseRackTileEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        for(int i = 0; i < 8; i++) {
            ItemStack stack = tileEntityIn.itemHandler.getItem(i);
            matrixStackIn.pushPose();
            Lighting.setupFor3DItems();
            matrixStackIn.translate(0.25,0.25,0.25);
            if(!stack.isEmpty()) {
                matrixStackIn.pushPose();
                //these should be bitwise but i was tired when writing this
                if(i % 2 == 1) {
                    matrixStackIn.translate(0.5, 0, 0);
                }
                if(i > 3) {
                    matrixStackIn.translate(0, 0.5, 0);
                }
                if(i == 2 || i == 3 || i == 6 || i == 7) {
                    matrixStackIn.translate(0, 0, 0.5);
                }

                matrixStackIn.scale(0.4f,0.4f,0.4f);
                Minecraft.getInstance().getItemRenderer().renderStatic(stack, FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, null, 0);
                matrixStackIn.popPose();
            }

            matrixStackIn.popPose();
        }
    }
}
