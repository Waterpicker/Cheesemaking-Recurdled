package ghana7.cheesemaking.rendering;

import com.mojang.blaze3d.matrix.MatrixStack;
import ghana7.cheesemaking.tileentity.CheeseRackTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CheeseRackTileEntityRenderer extends TileEntityRenderer<CheeseRackTileEntity> {
    public CheeseRackTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(CheeseRackTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        for(int i = 0; i < 8; i++) {
            ItemStack stack = tileEntityIn.itemHandler.getStackInSlot(i);
            matrixStackIn.push();
            RenderHelper.disableStandardItemLighting();
            matrixStackIn.translate(0.25,0.25,0.25);
            if(!stack.isEmpty()) {
                matrixStackIn.push();
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
                Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
                matrixStackIn.pop();
            }

            RenderHelper.enableStandardItemLighting();
            matrixStackIn.pop();
        }
    }
}
